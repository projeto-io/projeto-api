package io.projeto.api.project.command;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectLogoFilesRequest {
    private String logoPath;

    public ProjectLogoFilesRequest(String logoPath) {
        this.logoPath = logoPath;
    }
}