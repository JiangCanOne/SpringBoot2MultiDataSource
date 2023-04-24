package com.jc.springboot2multidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 此处不需要指定MapperScan,因为在配置数据源类中已经指定，如果此处指定则会启动报错
//@MapperScan("com.diary.it.multi.datasource.mapper")
public class SpringBoot2MultiDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2MultiDataSourceApplication.class, args);
    }

}
