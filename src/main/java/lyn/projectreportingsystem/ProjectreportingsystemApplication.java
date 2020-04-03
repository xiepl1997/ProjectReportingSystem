package lyn.projectreportingsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("lyn.projectreportingsystem.mapper")
public class ProjectreportingsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectreportingsystemApplication.class, args);
    }

}
