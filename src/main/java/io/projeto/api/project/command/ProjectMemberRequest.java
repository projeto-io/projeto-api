package io.projeto.api.project.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectMemberRequest {
    private String userId;
    private String role;
    private Boolean isLeader;

    public ProjectMemberRequest(String userId, String role, Boolean isLeader) {
        this.userId = userId;
        this.role = role;
        this.isLeader = isLeader;
    }
}