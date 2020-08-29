package io.projeto.api.common.security;

import io.projeto.api.auth.domain.UserRepository;
import io.projeto.api.common.token.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("AuthRequiredArgumentResolver 테스트 >")
class AuthRequiredArgumentResolverTest {
    private UserRepository userRepository;
    private TokenUtil tokenUtil;
    private AuthRequiredArgumentResolver resolver;

    @BeforeEach
    public void setup() {
        this.userRepository = mock(UserRepository.class);
        this.tokenUtil = mock(TokenUtil.class);
        this.resolver = spy(new AuthRequiredArgumentResolver(userRepository, tokenUtil));
    }


//    @Test
//    @DisplayName("allowGuest = false, GUEST인 경우 unauthenticated를 반환한다.")
//    public void throwsExceptionWhenDisallowGuestAndAuthorizationHeaderIsEmpty() {
//        new MockHttpServletRequest().addHeader("Authorization", "validToken");
//
//        AuthRequired disallowGuest = createAuthRequired(false);
//
//        assertThatThrownBy(() -> resolver.resolveArgument()).hasMessage("")
//    }

    @Test
    @DisplayName("extractAnnotation은 parameter annotation을 method annotation보다 우선한다.")
    public void extractAnnotation() {
        final AuthRequired parameterAnnotation = createAuthRequired(true);
        final AuthRequired methodAnnotation = createAuthRequired(false);

        doReturn(methodAnnotation).when(this.resolver).extractMethodAnnotation(null);
        doReturn(parameterAnnotation).when(this.resolver).extractParameterAnnotation(null);

        assertThat(this.resolver.extractAnnotation(null)).isEqualTo(parameterAnnotation);
    }


    private AuthRequired createAuthRequired(boolean allowGuest) {
        return new AuthRequired() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return AuthRequired.class;
            }

            @Override
            public boolean allowGuest() {
                return allowGuest;
            }
        };
    }
}