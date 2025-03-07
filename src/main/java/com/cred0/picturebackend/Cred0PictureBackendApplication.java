package com.cred0.picturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.cred0.picturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class Cred0PictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cred0PictureBackendApplication.class, args);
    }

}
