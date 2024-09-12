package com.nagarro.apiGatewayService;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

		http.csrf(csrf -> csrf.disable()).authorizeExchange(exchanges -> exchanges.anyExchange().permitAll());

		return http.build();
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/PRODUCTSERVICE/**")
						.filters(f -> f.addRequestHeader("Authorization", "Bearer {jwt-token}"))
						.uri("http://localhost:8081"))
				.route(r -> r.path("/PRODUCTDETAILSERVICE/**")
						.filters(f -> f.addRequestHeader("Authorization", "Bearer {jwt-token}"))
						.uri("http://localhost:8086"))
				.route(r -> r.path("/PRICESERVICE/**")
						.filters(f -> f.addRequestHeader("Authorization", "Bearer {jwt-token}"))
						.uri("http://localhost:8085"))
				.route(r -> r.path("/CHECKOUTSERVICE/**")
						.filters(f -> f.addRequestHeader("Authorization", "Bearer {jwt-token}"))
						.uri("http://localhost:8082"))
				.route(r -> r.path("/CARTSERVICE/**")
						.filters(f -> f.addRequestHeader("Authorization", "Bearer {jwt-token}"))
						.uri("http://localhost:8082"))
				.build();
	}
}
