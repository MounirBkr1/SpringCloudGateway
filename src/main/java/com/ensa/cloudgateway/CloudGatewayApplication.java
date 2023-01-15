package com.ensa.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }


    //configure route via class java
//
//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("1",r->r.path("/customers/**").uri("http://localhost:8081/"))
//                .route("2",r->r.path("/products/**").uri("http://localhost:8082/"))
//                .build();
//    }
}
