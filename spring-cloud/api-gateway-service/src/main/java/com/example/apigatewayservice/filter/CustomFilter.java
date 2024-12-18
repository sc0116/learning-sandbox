package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

	public static class Config {
		// Put the configuration properties
	}

	public CustomFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(final Config config) {
		// Custom Pre Filter
		return (exchange, chain) -> {
			final ServerHttpRequest request = exchange.getRequest();
			final ServerHttpResponse response = exchange.getResponse();

			log.info("Custom Pre filter: request id -> {}", request.getId());

			// Custom Post Filter
			return chain.filter(exchange)
				.then(Mono.fromRunnable(() ->
					log.info("Custom Post filter: response code -> {}", response.getStatusCode())
			));
		};
	}
}
