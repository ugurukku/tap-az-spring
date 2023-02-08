package com.ugurukku.tapazspring;

import com.ugurukku.tapazspring.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class TapAzSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TapAzSpringApplication.class, args);
    }


}
