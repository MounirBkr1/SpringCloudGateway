package com.ensa.cloudgateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="gateway",version="1.0",description="description of my open api for gateway"))
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


    @Bean
    CorsWebFilter corsFilter() {
        return new CorsWebFilter(corsConfigurationSource());
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
