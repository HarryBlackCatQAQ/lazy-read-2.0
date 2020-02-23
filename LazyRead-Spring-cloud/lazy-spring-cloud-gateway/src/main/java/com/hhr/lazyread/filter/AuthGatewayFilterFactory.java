package com.hhr.lazyread.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-30 15:01
 * @Version 1.0
 */

@Component
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config> {

    public AuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            System.err.println("Welcome to AuthFilter.");
            String token = exchange.getRequest().getHeaders().getFirst("sign");
            System.err.println(exchange.getRequest().getPath());
            System.err.println(token);
            System.err.println(Config.secret.equals(token));

            String url = exchange.getRequest().getPath().toString();
            for(String listUrl :Config.noTokenUrlList){
                if(url.equals(listUrl)){
                    return chain.filter(exchange);
                }
            }

            if (Config.secret.equals(token)) {
                return chain.filter(exchange);
            }
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        };
    }

    static class Config {
        static String secret = "1234";

        static List<String> noTokenUrlList = new ArrayList<>();

        Config(){
            noTokenUrlList.add("/user/login");
        }
    }
}