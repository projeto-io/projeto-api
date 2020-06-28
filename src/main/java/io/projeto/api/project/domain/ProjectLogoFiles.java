package io.projeto.api.project.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectLogoFiles {

    @Column(name = "logoPath")
    private String logoPath;

    public ProjectLogoFiles(String logoPath) {
        this.logoPath = logoPath;
    }
}
