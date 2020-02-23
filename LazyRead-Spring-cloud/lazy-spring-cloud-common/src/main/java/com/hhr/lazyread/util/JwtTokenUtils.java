package com.hhr.lazyread.util;

import com.hhr.lazyread.model.JwtDate;
import com.hhr.lazyread.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Harry
 * @Date: 2019-09-23 00:36
 * @Version 1.0
 */
@Slf4j
public class JwtTokenUtils {

    /**
     *
     */
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "djasid3934*&^^&)7!-dasd2odax_VongHoWing";
    private static final String ISS = "echisan";

    // 时间使用JwtDate管理
    // 过期时间是3600秒，既是1个小时
    private static final int EXPIRATION = 1;
    // 选择了记住我之后的过期时间为7天
    private static final int EXPIRATION_REMEMBER = 7;


    // 默认创建token 可选记住我
    public static String createToken(User jwtUser, boolean isRememberMe) {
        JwtDate jwtDate = new JwtDate();
        if(isRememberMe){
            jwtDate.setDays(EXPIRATION_REMEMBER);
        }
        else{
//            jwtDate.setSeconds(20);
            jwtDate.setHours(EXPIRATION);
        }

        return createTokenImpl(jwtUser,jwtDate);
    }

    //自定义过期时间 创建token
    public static String createToken(User jwtUser,JwtDate jwtDate) {
        return createTokenImpl(jwtUser,jwtDate);
    }

    private static String createTokenImpl(User jwtUser,JwtDate jwtDate){

        HashMap<String, Object> map = new HashMap<>();
        map.put("user",jwtUser);

        Date now = new Date(System.currentTimeMillis());
        Date exp = jwtDate.getExp(now);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)

                .setClaims(map)

                .setIssuer(ISS)
                .setSubject(jwtUser.getUserName())
                .setIssuedAt(now)
                .setExpiration(exp)
                .compact();
    }

    // 是否已过期
    public static boolean isExpiration(String token){
        token = token.replace(TOKEN_PREFIX, "");
        Claims claims = verifyJwt(token);
        if(claims == null){
            return true;
        }

        return claims.getExpiration().before(new Date());
    }

    public static Claims verifyJwt(String token){
        Claims claims;

        try{
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){

            log.warn("token验证过期或者token验证失败!");
            log.warn(e.toString());

            claims = null;
        }

        return claims;
    }
}
