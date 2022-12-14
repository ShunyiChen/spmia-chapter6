package com.thoughtmechanix.organization;

import com.thoughtmechanix.organization.utils.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class Application {

//    @Bean
//    public Filter userContextFilter() {
//        UserContextFilter userContextFilter = new UserContextFilter();
//        return userContextFilter;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
