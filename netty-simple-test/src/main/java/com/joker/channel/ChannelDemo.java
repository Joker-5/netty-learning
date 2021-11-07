package com.joker.channel;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class ChannelDemo {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("netty-simple-test/src/main/resources/data.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            do {
                int len = channel.read(buffer);
                log.error("读到byte 数: {}", len);
                if (len == -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    log.error("{}", (char) buffer.get());
                }
                buffer.clear();
                buffer.compact();
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
