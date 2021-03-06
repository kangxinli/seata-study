package com.sample.xa.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BusinessXAApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessXAApplication.class, args);
    }

}
