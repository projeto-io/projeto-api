package io.projeto.api.common.token.infra;

import io.jsonwebtoken.*;
import io.projeto.api.common.api.APIException;
import io.projeto.api.common.token.Token;
import io.projeto.api.common.token.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
@EnableConfigurationProperties(JwtProperties.class)
public class JwtTokenUtil implements TokenUtil {
    private final JwtProperties properties;
    private final Key signingKey;

    public JwtTokenUtil(JwtProperties properties) {
        this.properties = properties;
        this.signingKey = createSigningKey();
    }

    public Token generateToken(String userId) {
        return new JwtToken(createAccessToken(userId), createRefreshToken(userId));
    }

    @Override
    public String validate(Token token) {
        return validate(token.getAccessToken());
    }

    @Override
    public String validate(String accessToken) {
        try {
            String userId = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(accessToken)
                    .getBody().getSubject();
            log.info("Token parsed successfully '{}' => '{}'", accessToken, userId);
            return userId;
        } catch (ExpiredJwtException e) {
            log.info("Token expired '{}'", accessToken);
            throw APIException.tokenExpired();
        } catch (MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.info("Token is not valid '{}'", accessToken);
            throw APIException.tokenNotValid();
        }
    }

    private String createAccessToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(new Date().getTime() + properties.getExpiresIn()))
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    private String createRefreshToken(String userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
    }

    private Key createSigningKey() {
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(properties.getSecret());
        return new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());
    }

    @Override
    public String getHeaderPrefix() {
        return this.properties.getPrefix();
    }
}
