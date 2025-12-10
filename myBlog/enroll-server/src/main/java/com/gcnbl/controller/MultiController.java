package com.gcnbl.controller;

import com.gcnbl.bean.BlogUser;
import com.gcnbl.bean.MultiFile;
import com.gcnbl.service.MultiFileService;
import com.gcnbl.util.PSFTPUtil;
import com.jcraft.jsch.SftpException;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.player.Player;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/multi")
public class MultiController {


    @RequestMapping("/upload")
    public String fileUpload() {
        return "showAll";
    }

    @RequestMapping("/download")
    public String fileDownload() {
        return "fileDownload";
    }


    @Autowired
    private MultiFileService multiFileService;

    @Value("${upload.dir}")
    private String upload;

    @Value("${download.subURL}")
    private String downSubURL;

    /**
     * 上传文件（且保存到i数据库中）
     *
     * @param aaa
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile aaa, HttpSession session) throws IOException {
        //获取上传文件用户id
        BlogUser blogUser = (BlogUser) session.getAttribute("blogUser");
        //获取文件原始名称
        String oldFileName = aaa.getOriginalFilename();
        //获取文件扩展名
        String extension = "." + FilenameUtils.getExtension(aaa.getOriginalFilename());
        //生成新的文件名称
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "") + extension;
        //文件大小
        Long size = aaa.getSize();
        //文件类型
        String type = aaa.getContentType();
        System.out.println("文件类型:" + type);
        System.out.println(File.separator);


        PSFTPUtil sftp = new PSFTPUtil("root", "v_guocwu123", "182.254.227.12", 22);
        //PSFTPUtil sftp = new PSFTPUtil("root", "182.254.227.12", 22,pKey);
        sftp.login();

        InputStream is = aaa.getInputStream();
        try {
            sftp.upload(upload, "", newFileName, is);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        sftp.logout();

        //将文件信息放入数据库保存
        MultiFile multiFile = new MultiFile();
        multiFile.setOldFileName(oldFileName).setNewFileName(newFileName).setExt(extension).setSize(String.valueOf(size))
                .setType(type).setPath(upload).setUserId(blogUser.getId()).setUrl(upload + File.separator + newFileName);
        multiFileService.addFile(multiFile);
        return "redirect:/multi/showAll";
    }

    @RequestMapping("/play")
    public void play(String songName) {
        System.out.println(downSubURL);

        try {
            System.out.println(downSubURL + songName);
            URL url = new URL(
                    downSubURL + songName);
            URLConnection con = null;
            try {
                con = url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
            Bitstream bt = new Bitstream(bis);

            //获取mp3时间长度
            Header header = bt.readFrame();
            int mp3Length = con.getContentLength();
            int time = (int) header.total_ms(mp3Length);
            System.out.println(time / 1000);

            Player player = new Player(bis);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * 下载文件（在线预览）
     *
     * @param openStyle
     * @param id
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void download(String openStyle, String id, HttpServletResponse response) throws IOException {
        //获取打开方式
        openStyle = openStyle == null ? "attachment" : openStyle;
        //获取文件信息
        MultiFile multiFile = multiFileService.findById(id);
        //点击下载链接更新下载次数
        if ("attachment".equals(openStyle)) {
            multiFile.setDowncounts(multiFile.getDowncounts() + 1);
            multiFileService.update(multiFile);
        }

        //附件下载
        if ("inline".equals(openStyle)) {
            play(multiFile.getNewFileName());
        } else {
//根据文件信息中文件名字 和 文件存储路径获取文件输入流
            String realpath = multiFile.getPath();
            //获取文件输入流
            FileInputStream is = new FileInputStream(new File(realpath, multiFile.getNewFileName()));

            response.setHeader("content-disposition", openStyle + ";fileName=" + URLEncoder.encode(multiFile.getOldFileName(), "UTF-8"));
            //获取响应输出流
            ServletOutputStream os = response.getOutputStream();
            //文件拷贝
            IOUtils.copy(is, os);
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }

    }

    /**
     * 展示所有文件信息
     *
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/showAll")
    public String findAll(HttpSession session, Model model) {
        System.out.println("我没被过滤...");
        //在登录的session中获取用户的id
        BlogUser blogUser = (BlogUser) session.getAttribute("blogUser");

        //根据用户id查询有的文件信息
        List<MultiFile> multiFiles = multiFileService.findByUserId(blogUser.getId());
        //存入作用域中
        model.addAttribute("files", multiFiles);
        return "showAll";
    }

    /**
     * 删除文件（及数据库中的）
     *
     * @param id
     * @return
     * @throws FileNotFoundException
     */
    @GetMapping("/delete")
    public String delete(String id) throws FileNotFoundException {

        //根据id查询信息
        MultiFile multiFile = multiFileService.findById(id);

        //删除文件
        String realPath = multiFile.getPath();
        File file = new File(realPath, multiFile.getNewFileName());
        if (file.exists()) file.delete();//立即删除

        //删除数据库中记录
        multiFileService.delete(id);

        return "redirect:/multi/showAll";
    }

    /**
     * 返回当前的文件列表（json格式数据）
     *
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/findAllJSON")
    @ResponseBody
    public List<MultiFile> findAllJSON(HttpSession session, Model model) {

        System.out.println("定时更新啦.........");
        //在登录的session中获取用户的id
        BlogUser blogUser = (BlogUser) session.getAttribute("blogUser");
        //根据用户id查询有的文件信息
        List<MultiFile> multiFiles = multiFileService.findByUserId(blogUser.getId());

        return multiFiles;
    }
}
