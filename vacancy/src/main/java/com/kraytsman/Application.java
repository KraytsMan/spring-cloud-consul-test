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
    public List<Vacancy> vacancies() {
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
        this.vacancies().add(new Vacancy(1,
                "Java Developer", "Some data", new Random().nextInt(5000)));
        this.vacancies().add(new Vacancy(2,
                "FullStack Developer", "Some data", new Random().nextInt(5000)));
        this.vacancies().add(new Vacancy(3,
                "Angular Developer", "Some data", new Random().nextInt(5000)));
        this.vacancies().add(new Vacancy(4,
                "Project Manager", "Some data", new Random().nextInt(1000)));
    }

}
