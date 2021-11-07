package com.joker.file;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@Slf4j
public class TwoChannelDemo {
    public static final String FROM = "netty-simple-test/src/main/resources/data.txt";
    public static final String TO = "netty-simple-test/src/main/resources/to.txt";

    public static void main(String[] args) {
        long start = System.nanoTime();
        try (FileChannel from = new FileInputStream(FROM).getChannel();
             FileChannel to = new FileOutputStream(TO).getChannel()) {
            // get 到源文件字节数后直接传即可
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        log.info("send cost time: {} ", (end - start) / 1000_000.0);
    }
}