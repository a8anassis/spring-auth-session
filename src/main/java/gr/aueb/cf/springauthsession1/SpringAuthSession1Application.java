package gr.aueb.cf.springauthsession1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@EnableJpaAuditing
public class SpringAuthSession1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringAuthSession1Application.class, args);
    }
}


