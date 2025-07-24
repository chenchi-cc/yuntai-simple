package com.darrenchan;

import com.darrenchan.scheduler.bean.QuartzScheduler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YuntaiApplication {
    public static void main(String[] args) throws Exception {
        // 启动Spring应用
        SpringApplication.run(YuntaiApplication.class, args);

        // 启动定时任务调度器
        QuartzScheduler.getInstance().start();
    }
}
