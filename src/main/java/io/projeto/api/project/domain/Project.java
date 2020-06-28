package io.projeto.api.project.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Entity(name = "project")
@Table(name = "project")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project implements Persistable<String> {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "explanation", nullable = false)
    private String explanation;

    @Embedded
    private ProjectLogoFiles logoFiles;

    @OneToMany(mappedBy = "projectId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ProjectHashtag> hashtags;

    @OneToMany(mappedBy = "projectId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ProjectMember> projectMembers;

    @OneToMany(mappedBy = "projectId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ProjectPlanFile> projectPlanFiles;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;


    public Project(String id,
                   String name,
                   String description,
                   String explanation,
                   ProjectLogoFiles logoFiles,
                   Set<String> hashtags,
                   Set<ProjectMember> projectMembers,
                   Set<ProjectPlanFile> projectPlanFiles,
                   ProjectStatus status) {
        this.id = id;
        setName(name);
        setDescription(description);
        setExplanation(explanation);
        setLogoFiles(logoFiles);
        setHastags(hashtags);
        setProjectMembers(projectMembers);
        setProjectPlanFiles(projectPlanFiles);
        initStatus(status);
    }

    private void setProjectPlanFiles(Set<ProjectPlanFile> projectPlanFiles) {
        if (Objects.isNull(projectPlanFiles)) {
            this.projectPlanFiles = new HashSet<>();
            return;
        }
        this.projectPlanFiles = projectPlanFiles;
    }

    private void setLogoFiles(ProjectLogoFiles logoFiles) {
        this.logoFiles = logoFiles;
    }

    private void setProjectMembers(Set<ProjectMember> projectMembers) {
        if (Objects.isNull(projectMembers)) {
            this.projectMembers = new HashSet<>();
            return;
        }
        this.projectMembers = projectMembers;
    }

    private void setHastags(Set<String> hashtags) {
        if (Objects.isNull(hashtags)) {
            this.hashtags = new HashSet<>();
            return;
        }
        this.hashtags = hashtags.stream().map(hashtag -> new ProjectHashtag(id, hashtag)).collect(Collectors.toSet());
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

    private void initStatus(ProjectStatus status) {
        if (!ProjectStatus.isValidInitialStatus(status)) {
            throw new IllegalArgumentException();
        }

        this.status = status;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
