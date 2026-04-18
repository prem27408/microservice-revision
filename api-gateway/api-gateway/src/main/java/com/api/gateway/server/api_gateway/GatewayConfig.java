package com.api.gateway.server.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){

        //id,predicate,filter, uri->we have to pass in route
        return builder.routes()
                 .route("category-service",route->route.path("/category/**")
                         .filters(f->f.rewritePath("/category/?(?<remaining>.*)","/${remaining}"))
                         .uri("lb://CATEGORY-SERVICE")
                 )
                .route("quiz-service",route->route.path("/quiz/**")
                        .filters(f->f.rewritePath("/quiz/?(?<remaining>.*)","/${remaining}"))
                        .uri("lb://QUIZ-SERVICE"))
                .build();
    }
}
