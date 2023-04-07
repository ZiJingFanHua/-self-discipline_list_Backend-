package com.ljh.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.ljh.project.mapper")
@EnableSwagger2
public class SelfDisciplineListApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfDisciplineListApplication.class, args);
    }

}
