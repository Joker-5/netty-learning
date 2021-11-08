package com.joker.netty.eventloopdemo;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;

public class EventLoopDemo {
    public static void main(String[] args) {
        DefaultEventLoopGroup group = new DefaultEventLoopGroup(3);
        for (EventExecutor eventExecutor : group) {
            System.out.println(eventExecutor);
        }
        group.shutdownGracefully();
    }
}
