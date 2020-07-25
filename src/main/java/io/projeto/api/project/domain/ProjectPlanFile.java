package io.projeto.api.project.domain;

import io.projeto.api.common.util.UUIDGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "project_plan_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectPlanFile {

    @Id
    @Column(name = "project_plan_file_id")
    private String id;

    @Column(name = "project_id", nullable = false, updatable = false)
    private String projectId;

    @Column(name = "path", nullable = false, updatable = false)
    private String path;

    public ProjectPlanFile(String projectId, String path) {
        this.id = UUIDGenerator.nextUUID();
        this.projectId = projectId;
        this.path = path;
    }
}
