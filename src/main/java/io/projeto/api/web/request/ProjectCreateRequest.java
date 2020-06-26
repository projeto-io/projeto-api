package io.projeto.api.web.request;

import io.projeto.api.domain.project.ProjectHashtag;
import io.projeto.api.domain.project.ProjectLogoFiles;
import io.projeto.api.domain.project.ProjectMember;
import io.projeto.api.domain.project.ProjectPlanFile;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class ProjectCreateRequest {

    @NotBlank(message = "프로젝트명을 입력해주세요.")
    private String name;

    private String description;

    @NotBlank(message = "프로젝트 설명을 입력해주세요.")
    private String explanation;

    private ProjectLogoFiles logoFiles;

    private Set<ProjectHashtag> hashtags;

    private Set<ProjectMember> projectMembers;

    private Set<ProjectPlanFile> projectPlanFiles;
}
