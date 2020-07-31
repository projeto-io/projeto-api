package io.projeto.api.auth.domain;

import javax.persistence.*;

@Entity(name = "user_social_login")
@Table(name = "user_social_login")
public class UserSocialLogin {
    @Id
    private String id;
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    private SocialPlatform platform;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "refresh_token", nullable = true)
    private String refreshToken;


}
