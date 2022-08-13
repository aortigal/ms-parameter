package com.bank.msparameter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsParameterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsParameterApplication.class, args);
    }

}
