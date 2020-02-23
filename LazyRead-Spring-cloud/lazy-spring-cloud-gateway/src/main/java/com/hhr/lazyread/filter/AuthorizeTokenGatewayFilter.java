//package com.hhr.lazyread.filter;
//
//import com.hhr.lazyread.util.RedisUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.core.Ordered;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.util.StringUtils;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @Author: Harry
// * @Date: 2019-12-30 14:46
// * @Version 1.0
// */
//public class AuthorizeTokenGatewayFilter implements GatewayFilter, Ordered {
//    private static final String AUTHORIZE_TOKEN = "token";
//    private static final String AUTHORIZE_UID = "uid";
//
////    @Autowired
////    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        HttpHeaders headers = request.getHeaders();
//        String token = headers.getFirst(AUTHORIZE_TOKEN);
//        String uid = headers.getFirst(AUTHORIZE_UID);
//
//        System.err.println(token + " " + uid);
//
////        if (token == null) {
////            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
////        }
////        if (uid == null) {
////            uid = request.getQueryParams().getFirst(AUTHORIZE_UID);
////        }
////
////        ServerHttpResponse response = exchange.getResponse();
////        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(uid)) {
////            response.setStatusCode(HttpStatus.UNAUTHORIZED);
////            return response.setComplete();
////        }
////        String authToken = stringRedisTemplate.opsForValue().get(uid);
////        if (authToken == null || !authToken.equals(token)) {
////            response.setStatusCode(HttpStatus.UNAUTHORIZED);
////            return response.setComplete();
////        }
//
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}