package de.sp.superBnB_backend_18_9_2024;

import de.sp.superBnB_backend_18_9_2024.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SuperBnBBackend1892024Application {

    public static void main(String[] args) {
        SpringApplication.run(SuperBnBBackend1892024Application.class, args);
    }

}
