package io.projeto.api.project.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "project_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectMember {

    @Id
    @Column(name = "project_member_id")
    private String id;

    @Column(name = "project_id", nullable = false, updatable = false)
    private String projectId;

    @Column(name = "member_id", nullable = false, updatable = false)
    private String memberId;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "is_leader", nullable = false)
    private Boolean isLeader;

    public ProjectMember(String projectId, String memberId, String role, Boolean isLeader) {
        this.id = UUID.randomUUID().toString();
        this.projectId = projectId;
        this.memberId = memberId;
        this.role = role;
        this.isLeader = isLeader;
    }
}
