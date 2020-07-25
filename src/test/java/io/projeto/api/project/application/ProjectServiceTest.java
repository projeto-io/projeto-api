package io.projeto.api.project.application;

import io.projeto.api.project.command.ProjectCreate;
import io.projeto.api.project.command.ProjectLogoFilesRequest;
import io.projeto.api.project.domain.ProjectRepository;
import io.projeto.api.project.domain.ProjectStatus;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


@DisplayName("프로젝트 서비스 >")
class ProjectServiceTest {

    @Test
    @DisplayName("프로젝트 생성시 ProjectRepository를 통해 프로젝트를 추가한다.")
    void createProjectTest() {
        ProjectCreate request = new ProjectCreate(
                UUID.randomUUID().toString(),
                "project",
                "description",
                "explanation",
                new ProjectLogoFilesRequest("logoPath"),
                Sets.newHashSet(),
                Sets.newHashSet(),
                Sets.newHashSet(),
                ProjectStatus.DRAFT
        );

        ProjectRepository repository = mock(ProjectRepository.class);
        ProjectCreateService service = new ProjectService(repository);

        service.createProject(request);

        verify(repository, times(1)).save(argThat(project ->
                (request.getId().equals(project.getId()) &&
                        request.getName().equals(project.getName()) &&
                        request.getDescription().equals(project.getDescription()) &&
                        request.getExplanation().equals(project.getExplanation()) &&
                        request.getLogoFiles().getLogoPath().equals(project.getLogoFiles().getLogoPath())
                ))
        );
    }
}