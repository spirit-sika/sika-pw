package cc.sika;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cc.sika.mapper")
@EnableTransactionManagement
public class SikaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SikaApplication.class, args);
    }
}