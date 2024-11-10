package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

//	@Bean
	public RouteLocator gatewayRoutes(final RouteLocatorBuilder builder) {
		return builder.routes()
			.route(route ->
				route.path("/first-service/**")
					.filters(filter ->
						filter.addRequestHeader("first-request", "first-request-header")
							.addResponseHeader("first-response", "first-response-header")
					)
					.uri("http://localhost:8081")
			)
			.route(route ->
				route.path("/second-service/**")
					.filters(filter ->
						filter.addRequestHeader("second-request", "second-request-header")
							.addResponseHeader("second-response", "second-response-header")
					)
					.uri("http://localhost:8082")
			)
			.build();
	}
}
