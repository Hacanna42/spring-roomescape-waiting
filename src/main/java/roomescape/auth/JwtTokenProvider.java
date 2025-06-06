package roomescape.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import roomescape.domain.MemberRole;
import roomescape.exception.UnauthorizedException;
import roomescape.service.result.MemberResult;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private int validityInMilliseconds;

    public String createToken(final MemberResult member) {
        Date expirationDate = new Date(System.currentTimeMillis() + validityInMilliseconds);

        return Jwts.builder()
                .subject(member.id().toString())
                .claim("name", member.name())
                .claim("role", member.role())
                .expiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public Long extractIdFromToken(final String token) {
        if (token == null || token.isBlank()) {
            throw new UnauthorizedException();
        }

        Claims claims = extractAllClaimsFromToken(token);
        return Long.valueOf(claims.getSubject());
    }

    public MemberRole extractMemberRoleFromToken(final String token) {
        if (token == null || token.isBlank()) {
            throw new UnauthorizedException();
        }

        Claims claims = extractAllClaimsFromToken(token);
        String role = (String) claims.get("role");
        return MemberRole.of(role);
    }

    public String  extractMemberNameFromToken(final String token) {
        if (token == null || token.isBlank()) {
            throw new UnauthorizedException();
        }

        Claims claims = extractAllClaimsFromToken(token);
        return (String) claims.get("name");
    }

    private Claims extractAllClaimsFromToken(final String token) {
        try {
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new UnauthorizedException();
        }
    }
}
