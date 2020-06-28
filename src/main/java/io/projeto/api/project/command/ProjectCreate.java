package io.projeto.api.project.command;

import io.projeto.api.project.domain.ProjectStatus;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Getter
public class ProjectCreate {
    private final String id = UUID.randomUUID().toString();

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

}
