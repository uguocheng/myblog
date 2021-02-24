package com.gcnbl;

//import feign.Logger;
import com.gcnbl.annotation.BlogScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication

// ↓ xyz.erupt必须有
@EntityScan("com.gcnbl")    // ↓ 如果包名com.example.demo有变化
@BlogScan("com.gcnbl")     // → 要修改为变化后的包名
public class EnrollServerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) throws URISyntaxException, IOException {
        SpringApplication.run(EnrollServerApplication.class, args);

        System.setProperty("java.awt.headless", "false");
        Desktop.getDesktop().browse(new URI("http://localhost:18080"));
    }

    //在启动类上添加bean对象注入给spring容器
    /*@Bean
    public Logger.Level feignLog() {
        return Logger.Level.FULL;
    }*/

    //打WAR包的配置
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EnrollServerApplication.class);
    }
}
