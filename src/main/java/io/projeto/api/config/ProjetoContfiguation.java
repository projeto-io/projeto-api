package io.projeto.api.config;

import io.projeto.api.common.security.AuthRequiredArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ProjetoContfiguation implements WebMvcConfigurer {
    private final AuthRequiredArgumentResolver authRequiredArgumentResolver;

    public ProjetoContfiguation(AuthRequiredArgumentResolver authRequiredArgumentResolver) {
        this.authRequiredArgumentResolver = authRequiredArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authRequiredArgumentResolver);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
