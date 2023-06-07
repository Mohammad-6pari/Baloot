package Baloot.Data.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public class Jwt {
    static final String SECRET_KEY = "baloot2023asjiojdfhjkasjdsakldjklasdlajighjigjighjfhfhjgfhjgfhj";

    static Key generateKey() {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public static String generateToken(String username) {

        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setIssuer("Baloot")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .claim("username", username)
                .signWith(generateKey())
                .compact();
    }

    public static Claims parseToken(String jwtString) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(jwtString)
                .getBody();
    }

    public static Boolean validate(Claims claims) {
        return (Objects.equals(claims.getIssuer(), "Baloot"))
                && (claims.getExpiration().after(Date.from(Instant.now())));
    }
}
