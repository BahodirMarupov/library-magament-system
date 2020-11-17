package uz.librarysystem.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import uz.librarysystem.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationProvider {

    @Value(value = "jwt.secret")
    private String JwtKey;

    public String generateToken(Authentication authentication){
        User user=(User) authentication.getPrincipal();
        Date now=new Date(System.currentTimeMillis());

        Date expireDate=new Date(now.getTime()+3*24*60*60*1000);  // 3 days
        String userId= String.valueOf(user.getId());
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",userId);
        claims.put("username",user.getUsername());
        claims.put("role",user.getRoles());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,JwtKey)
                .compact();
    }
}
