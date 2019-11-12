package com.msr.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Date;

/**
 * @Description:
 * @Author: maishuren
 * @Date: 2019/11/12 10:39
 */
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    /**
     * 加密的盐值
     */
    private String key;
    /**
     * token过期时间
     */
    private Integer ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成JWT,根据需要，设置需要加密的
     *
     * @param id 用户id
     * @param subject 可以是用户名之类的
     * @return 返回
     */
    public String createJwt(String id, String subject, String role) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, this.key).claim("role", role);
        if (ttl > 0) {
            builder.setExpiration(new Date(nowMillis + this.ttl));
        }
        return builder.compact();
    }

    /**
     * 解析JWT
     *
     * @param jwtStr token
     * @return 返回
     */
    public Claims parseJwt(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(this.key)
                .parseClaimsJws(jwtStr)
                .getBody();
    }
}
