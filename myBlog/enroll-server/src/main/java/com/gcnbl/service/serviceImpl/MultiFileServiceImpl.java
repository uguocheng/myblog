package com.gcnbl.service.serviceImpl;

import com.gcnbl.bean.MultiFile;
import com.gcnbl.dao.MultiFileDao;
import com.gcnbl.service.MultiFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MultiFileServiceImpl implements MultiFileService {
    @Autowired
    private MultiFileDao multiFileDao;

    @Override
    public List<MultiFile> findAllFiles() {
        List<MultiFile> fileList = multiFileDao.findAll();

        return fileList;
    }

    @Override
    public MultiFile addFile(MultiFile multiFile) {
        //multiFile.setIsImg()?  //是否是图片 解决方案: 当类型中含有image时说明当前类型一定为图片类型
        String isImg = multiFile.getType().startsWith("image")?"是":"否";
        multiFile.setIsImg(isImg).setDowncounts(0).setUploadTime(new Date());
        MultiFile file = multiFileDao.save(multiFile);
        return file;
    }

    @Override
    public MultiFile findById(String id) {
        MultiFile file = multiFileDao.findById(Long.parseLong(id)).get();
        return file;
    }

    @Override
    public void update(MultiFile multiFile) {
        multiFileDao.save(multiFile);
    }

    @Override
    public List<MultiFile> findByUserId(Long userId) {
        return multiFileDao.findByUserId(userId);
    }

    @Override
    public void delete(String id) {
        multiFileDao.deleteById(Long.parseLong(id));
    }
}
