package com.gcnbl.controller;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ReadMp3 {
    private String _songName;


    ReadMp3(String songName) throws IOException, Exception {

        _songName = songName;
        URL url = new URL("http://guocwu.xyz:8080/blogResources/"+_songName);
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
    }

    public static void main(String[] args) throws Exception {
        ReadMp3 mp3 = new ReadMp3("2021040616385492bcd216bffe42b2ac1b8cf863151567.mp3");
    }
}
