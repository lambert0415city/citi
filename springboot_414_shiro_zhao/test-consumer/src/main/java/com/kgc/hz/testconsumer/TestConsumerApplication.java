package com.kgc.hz.testconsumer;

import com.alibaba.boot.dubbo.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class TestConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestConsumerApplication.class, args);
    }

}
