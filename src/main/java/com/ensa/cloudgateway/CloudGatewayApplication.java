package com.ensa.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }


    //1st way: static: configure route via class java

//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder){
//        return builder.routes()
//                //.route("1",r->r.path("/customers/**").uri("http://localhost:8081/"))
//                .route("1",r->r.path("/customers/**").uri("lb://MS-CUSTOMER"))
//                //.route("2",r->r.path("/products/**").uri("http://localhost:8082/"))
//                .route("2",r->r.path("/products/**").uri("lb://MS-INVENTORY"))
//                .build();
//    }

    //2nd way: dynamic
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        //rdc:reactive discovery client, dlp: discovery locator properties
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }
}
