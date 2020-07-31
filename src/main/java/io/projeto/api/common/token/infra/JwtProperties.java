package io.projeto.api.common.token.infra;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@NoArgsConstructor
@ConfigurationProperties(prefix = "projeto.jwt")
public class JwtProperties {
    private String prefix;
    private String secret;
    private Long expiresIn;

    public JwtProperties(String prefix, String secret, Long expiresIn) {
        this.prefix = prefix;
        this.secret = secret;
        this.expiresIn = expiresIn;
    }
}
