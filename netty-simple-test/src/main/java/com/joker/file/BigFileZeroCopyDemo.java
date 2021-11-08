package com.joker.file;

import com.joker.constant.FilePathConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@Slf4j
public class BigFileZeroCopyDemo {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream(FilePathConstant.FROM).getChannel();
             FileChannel to = new FileOutputStream(FilePathConstant.TO).getChannel()) {
            final long size = from.size();
            // 利用os零拷贝进行优化
            for (long left = size; left > 0; ) {
                log.info("position: {}, left: {}", (size - left), left);
                left -= from.transferTo((size - left), left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
