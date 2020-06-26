package io.projeto.api.domain.project;

import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class Project {
    private Long id;

    private String name;

    private String description;

    private String explanation;

    private ProjectLogoFiles logoFiles;

    private Set<ProjectHashtag> hashtags;

    private Set<ProjectMember> projectMembers;

    private Set<ProjectPlanFile> projectPlanFiles;

    private ProjectStatus status;


    Project(String name, String description, String explanation, ProjectLogoFiles logoFiles, Set<ProjectHashtag> hashtags, Set<ProjectMember> projectMembers, Set<ProjectPlanFile> projectPlanFiles, ProjectStatus status) {
        setName(name);
        setDescription(description);
        setExplanation(explanation);
        this.logoFiles = logoFiles;
        this.hashtags = hashtags;
        this.projectMembers = projectMembers;
        this.projectPlanFiles = projectPlanFiles;
        this.status = status;
    }


    private void setName(String name) {
        int length = StringUtils.length(name);
        if (length < 3 || length > 20) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    private void setDescription(String description) {
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException();
        }
        this.description = description;
    }

    private void setExplanation(String explanation) {
        this.explanation = StringUtils.defaultString(explanation);
    }
}
