package io.projeto.api.project.command;

import lombok.Getter;

@Getter
public class ProjectMemberRequest {
    private String userId;
    private String role;
    private Boolean isLeader;
}