package hyu.erica.v1.capstone.security;

import hyu.erica.v1.capstone.api.code.status.ErrorStatus;
import hyu.erica.v1.capstone.api.exception.GeneralException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${jwt.expiration-time}")
    private Integer EXPIRATION_TIME; // 24시간



    public String createToken(Long userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public ErrorStatus validateToken(String token, boolean checkExpirationOnly) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return null;
        } catch (ExpiredJwtException e) {
            if (checkExpirationOnly) return ErrorStatus._EXPIRED_JWT;
            throw new GeneralException(ErrorStatus._EXPIRED_JWT);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new GeneralException(ErrorStatus._INVALID_JWT);
        } catch (UnsupportedJwtException e) {
            throw new GeneralException(ErrorStatus._UNSUPPORTED_JWT);
        } catch (IllegalArgumentException e) {
            throw new GeneralException(ErrorStatus._EMPTY_JWT);
        }
    }



    public String resolveToken(HttpServletRequest request) {
        return resolveHeaderToken(request, "Authorization", "Bearer ");
    }

    public Authentication getAuthentication(String token, UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Long getUserIdByToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userId", Long.class);
    }


    private String resolveHeaderToken(HttpServletRequest request, String headerName, String prefix) {
        String headerValue = request.getHeader(headerName);
        if (headerValue == null || !headerValue.startsWith(prefix)) {
            return null;
        }
        return headerValue.substring(prefix.length()).trim();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }


}
