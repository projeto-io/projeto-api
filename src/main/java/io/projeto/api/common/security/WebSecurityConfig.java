package io.projeto.api.common.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Value("${spring.profiles.active:NONE}")
    private String activeProfile;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (StringUtils.equals(activeProfile, "beta")) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowCredentials(true)
                    .maxAge(3600);
        }
    }
}
