package io.projeto.api.common.security;

import io.projeto.api.auth.domain.User;
import io.projeto.api.auth.domain.UserRepository;
import io.projeto.api.common.api.APIException;
import io.projeto.api.common.token.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class AuthRequiredArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserRepository userRepository;
    private final TokenUtil tokenUtil;

    public AuthRequiredArgumentResolver(UserRepository userRepository, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.hasMethodAnnotation(AuthRequired.class) || parameter.hasParameterAnnotation(AuthRequired.class)) && parameter.getParameterType().equals(ProjetoAuthentication.class);
    }

    @Override
    public ProjetoAuthentication resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        AuthRequired authRequired = extractAnnotation(parameter);
        String authorization = request.getHeader("Authorization");

        if (StringUtils.isBlank(authorization)) {
            if (authRequired.allowGuest()) {
                return ProjetoAuthentication.GUEST;
            }
            throw APIException.unauthenticated();
        }


        String accessToken = authorization.replace(tokenUtil.getHeaderPrefix() + " ", "");

        String userId = tokenUtil.validate(accessToken);

        User user = userRepository.findById(userId).orElse(null);

        if (Objects.nonNull(user)) {
            return ProjetoAuthentication.of(user.getId());
        }

        if (authRequired.allowGuest()) {
            return ProjetoAuthentication.GUEST;
        }

        throw APIException.unauthenticated();
    }

    protected AuthRequired extractAnnotation(MethodParameter parameter) {
        AuthRequired methodAnnotation = extractMethodAnnotation(parameter);
        AuthRequired parameterAnnotation = extractParameterAnnotation(parameter);

        return Objects.nonNull(parameterAnnotation) ? parameterAnnotation : methodAnnotation;
    }

    protected AuthRequired extractParameterAnnotation(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthRequired.class);
    }

    protected AuthRequired extractMethodAnnotation(MethodParameter parameter) {
        return parameter.getMethodAnnotation(AuthRequired.class);
    }
}