package com.zw.demo;

import com.zw.demo.config.MicroServiceProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties({MicroServiceProperty.class})

public class SpringbootMultithreadpoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMultithreadpoolApplication.class, args);
    }

}
