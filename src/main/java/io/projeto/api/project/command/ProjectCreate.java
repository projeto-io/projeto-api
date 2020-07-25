package io.projeto.api.project.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.projeto.api.common.util.UUIDGenerator;
import io.projeto.api.project.domain.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ProjectCreate {
    @JsonIgnore
    private String id = UUIDGenerator.nextUUID();

    @NotBlank(message = "프로젝트명을 입력해주세요.")
    private String name;

    private String description;

    @NotBlank(message = "프로젝트 설명을 입력해주세요.")
    private String explanation;

    private ProjectLogoFilesRequest logoFiles;

    private Set<String> hashtags;

    private Set<ProjectPlanFileRequest> planFiles;

    private Set<ProjectMemberRequest> members;

    private ProjectStatus status;

    public ProjectCreate(String id,
                         String name,
                         String description,
                         String explanation,
                         ProjectLogoFilesRequest logoFiles,
                         Set<String> hashtags,
                         Set<ProjectPlanFileRequest> planFiles,
                         Set<ProjectMemberRequest> members,
                         ProjectStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.explanation = explanation;
        this.logoFiles = logoFiles;
        this.hashtags = hashtags;
        this.planFiles = planFiles;
        this.members = members;
        this.status = status;
    }
}
