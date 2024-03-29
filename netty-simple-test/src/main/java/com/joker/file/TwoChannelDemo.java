package com.joker.file;

import com.joker.constant.FilePathConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@Slf4j
public class TwoChannelDemo {
    public static void main(String[] args) {
        long start = System.nanoTime();
        try (FileChannel from = new FileInputStream(FilePathConstant.FROM).getChannel();
             FileChannel to = new FileOutputStream(FilePathConstant.TO).getChannel()) {
            // get 到源文件字节数后直接传即可
            from.transferTo(0, from.size(), to);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        log.info("send cost time: {} ", (end - start) / 1000_000.0);
    }
}