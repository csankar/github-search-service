package com.informatica.github.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class GithubSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubSearchServiceApplication.class, args);
    }

}
