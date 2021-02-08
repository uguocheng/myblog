package com.gcnbl;

//import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EnrollServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnrollServerApplication.class, args);
    }

    //在启动类上添加bean对象注入给spring容器
    /*@Bean
    public Logger.Level feignLog() {
        return Logger.Level.FULL;
    }*/
}
