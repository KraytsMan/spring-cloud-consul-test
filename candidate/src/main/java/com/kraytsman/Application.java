package com.kraytsman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.kraytsman")
public class Application {

    @Bean
    public List<Candidate> candidates() {
        return new ArrayList<>();
    }

    @Bean
    public List<Skill> skills() {
        return new ArrayList<>();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    private void fillVacancies() {
        this.candidates().add(new Candidate(1, "Volodymyr", "Kraietskyi", 24, "0502220390", "somewhere", "appropriate"));
        this.candidates().add(new Candidate(2, "Andriy", "Pavliv", 25, "0502220391", "somewhere", "inappropriate"));
        this.candidates().add(new Candidate(3, "Oleg", "Ivaniv", 25, "0502220392", "somewhere", "inappropriate"));
        this.skills().add(new Skill(1, "Java 8"));
        this.skills().add(new Skill(2, "Hibernate"));
        this.skills().add(new Skill(3, "Spring Boot"));
        this.skills().add(new Skill(4, "JS ES5"));
        this.skills().add(new Skill(5, "Angular"));
        for (int i = 0; i < 3; i++) {
            candidates().forEach(candidate -> {
                candidate.getSkills().add(skills().get(new Random().nextInt(5)));
            });
        }
    }

}
