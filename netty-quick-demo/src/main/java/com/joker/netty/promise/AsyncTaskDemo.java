package com.joker.netty.promise;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsyncTaskDemo {
    public static void main(String[] args) {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

        // async callback
        promise.addListener(future -> {
            log.debug("get future now, {}", future.getNow());
        });

        // wait 1000ms, then set success
        eventExecutors.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("set success, {}", 123);
            promise.setSuccess(123);
        });

        log.debug("start~~~");
    }
}
