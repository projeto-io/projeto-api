package io.projeto.api.auth.presentation;

import io.projeto.api.auth.domain.User;
import lombok.Getter;

@Getter
public class UserPresentation {
    private String id;
    private String name;
    private String nickname;
    private String email;
    private String telephone;


    private UserPresentation(String id, String name, String nickname, String email, String telephone) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.telephone = telephone;
    }

    public static UserPresentation of(User user) {
        return new UserPresentation(
                user.getId(),
                user.getName(),
                user.getNickname(),
                user.getEmail(),
                user.getTelephone()
        );
    }
}
