package io.projeto.api.auth.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.projeto.api.common.util.UUIDGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjetoSignup {
    @JsonIgnore
    private String id = UUIDGenerator.nextUUID();

    private String email;
    private String password;
    private String name;
    private String nickname;
    private String telephone;

    public ProjetoSignup(String id, String email, String password, String name, String nickname, String telephone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.telephone = telephone;
    }
}
