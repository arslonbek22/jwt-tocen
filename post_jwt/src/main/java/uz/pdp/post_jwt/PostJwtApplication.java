package uz.pdp.post_jwt;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@MultipartConfig
@SpringBootApplication
public class PostJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostJwtApplication.class, args);
    }

}
