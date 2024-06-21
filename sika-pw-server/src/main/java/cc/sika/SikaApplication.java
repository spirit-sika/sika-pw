package cc.sika;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cc.sika.mapper")
public class SikaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SikaApplication.class, args);
    }
}