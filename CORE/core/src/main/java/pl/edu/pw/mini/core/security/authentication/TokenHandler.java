package pl.edu.pw.mini.core.security.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pl.edu.pw.mini.model.UserRole;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class TokenHandler {
    private static final String USER_ID = "userId";
    private static final String ROLE = "role";
    private static final String IP = "ip";
    private static final String FORENAME = "forename";
    private static final String SURNAME = "surname";

    private final SecurityProperties properties;
    private final Key signingKey;

    public TokenHandler(SecurityProperties properties) {
        this.properties = properties;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(properties.getSecretKey());
        signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public String getTestToken(String userId, UserRole role) {
        return getTokenWithUser(userId, role, "TEST", role.name(), "*");
    }

    public String getTokenWithUser(String userId, UserRole role, String forename, String surname, HttpServletRequest request) {
        return getTokenWithUser(userId, role, forename, surname, request.getRemoteAddr());
    }

    private String getTokenWithUser(String userId, UserRole role, String forename, String surname, String ip) {
        Date expires = new Date(System.currentTimeMillis() + properties.getTokenValidity());

        return Jwts.builder()
                .claim(USER_ID, userId)
                .claim(ROLE, role.name())
                .claim(IP, ip)
                .claim(FORENAME, forename)
                .claim(SURNAME, surname)
                .setExpiration(expires)
                .setIssuer(properties.getIssuer())
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                .signWith(signingKey)
                .compact();
    }
    public Token parseToken(String rawToken, HttpServletRequest request) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(rawToken).getBody();
        if(!claims.get(IP).equals("*") && !claims.get(IP).equals(request.getRemoteAddr())) {
            throw new JwtException("Different user IP!");
        }

        return Token.builder()
                .userId(claims.get(USER_ID, String.class))
                .role(claims.get(ROLE, String.class))
                .forename(claims.get(FORENAME, String.class))
                .surname(claims.get(SURNAME, String.class))
                .ip(claims.get(IP, String.class))
                .expiration(claims.getExpiration())
                .issuer(claims.getIssuer())
                .issuedAt(claims.getIssuedAt())
                .id(claims.getId())
                .tokenString(rawToken)
                .build();
    }
}
