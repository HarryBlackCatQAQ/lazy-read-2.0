package com.hhr.lazyread.filter;

import com.hhr.lazyread.model.User;
import com.hhr.lazyread.redis.TokenRedis;
import com.hhr.lazyread.util.JsonXMLUtils;
import com.hhr.lazyread.util.JwtTokenUtils;
import com.hhr.lazyread.util.RedisUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-30 16:11
 * @Version 1.0
 */
@Component
@Slf4j
public class AuthorizeGlobalFilter implements GlobalFilter, Ordered, TokenRedis {
    private static final String AUTHORIZE_TOKEN = "token";
    private static final String AUTHORIZE_UID = "uid";

    private static final List<String> noTokenUrlList = new ArrayList<>();

    static{
        noTokenUrlList.add("/user/login");
        noTokenUrlList.add("/user/registeredUser");
        noTokenUrlList.add("/user/logout");
        noTokenUrlList.add("/user/checkUserName");
    }

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JsonXMLUtils jsonXMLUtils;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(AUTHORIZE_TOKEN);
        String uid = headers.getFirst(AUTHORIZE_UID);


        System.err.println("Welcome to Global AuthFilter.");
        System.err.println(exchange.getRequest().getPath());
        System.err.println(token + " " + uid);

        /**
         * noTokenUrlList 中的路径不需要token验证直接放行
         */
        String url = exchange.getRequest().getPath().toString();
        for(String listUrl : noTokenUrlList){
            if(listUrl.equals(url)){
                log.info(url + " 不需要token验证");
                return chain.filter(exchange);
            }
        }

        /**
         * 没有token无法分发服务
         */
        if(token == null){
            log.info("headers 中没有token");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        System.err.println(JwtTokenUtils.verifyJwt(token));
        Claims claims = JwtTokenUtils.verifyJwt(token);

        /**
         * 如果过期claims 为null表示过期 token失效
         */
        if(claims == null){
            log.info("token过期");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        else{
            /**
             * 从token中获取User信息
             */
            User user;
            try {
                user = jsonXMLUtils.map2obj(claims.get("user"),User.class);
            } catch (Exception e) {
                log.error("解析错误");
                log.error(e.toString());
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            /**
             * 如果redis中没有这个token，即使token没有过期也视为失效
             */
            String key = TOKENKEY_PREFIX + user.getUserName();
            if(!redisUtil.hasKey(key)){
                log.info("redis中没有token token过期");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            else if(!token.equals(redisUtil.get(key))){
                log.info("redis中有token 但是与redis中的token不一样！");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }


        }


//        if (token == null) {
//            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
//        }
//        if (uid == null) {
//            uid = request.getQueryParams().getFirst(AUTHORIZE_UID);
//        }
//
//        ServerHttpResponse response = exchange.getResponse();
//        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(uid)) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
//        }
//        String authToken = stringRedisTemplate.opsForValue().get(uid);
//        if (authToken == null || !authToken.equals(token)) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}