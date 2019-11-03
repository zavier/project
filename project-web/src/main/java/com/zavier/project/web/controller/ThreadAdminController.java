package com.zavier.project.web.controller;

import com.zavier.project.web.res.Result;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class ThreadAdminController {

    private final ThreadPoolTaskExecutor coreThreadPoolTaskExecutor;

    public ThreadAdminController(ThreadPoolTaskExecutor coreThreadPoolTaskExecutor) {
        this.coreThreadPoolTaskExecutor = coreThreadPoolTaskExecutor;
    }


    @GetMapping("threadStatus")
    public Result<Integer> threadStatus() {
        final ThreadPoolExecutor coreThreadPoolExecutor = coreThreadPoolTaskExecutor.getThreadPoolExecutor();
        final int poolSize = coreThreadPoolExecutor.getPoolSize();
        return Result.wrapSuccessResult(poolSize);
    }

    @GetMapping("run")
    public Result<Boolean> run() {
        coreThreadPoolTaskExecutor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return Result.wrapSuccessResult(true);
    }
}
