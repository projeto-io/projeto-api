package io.projeto.api.project.application;

import io.projeto.api.project.command.ProjectCreate;
import io.projeto.api.project.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectCreateService {
    private final ProjectRepository projectRepository;

    @Transactional
    public void createProject(ProjectCreate request) {
        Set<ProjectMember> projectMembers = Optional.ofNullable(request.getMembers()).orElse(new HashSet<>()).stream()
                .map(member -> new ProjectMember(request.getId(), member.getUserId(), member.getRole(), member.getIsLeader())).collect(Collectors.toSet());

        Set<ProjectPlanFile> projectPlanFiles = Optional.ofNullable(request.getPlanFiles()).orElse(new HashSet<>()).stream()
                .map(planFile -> new ProjectPlanFile(request.getId(), planFile.getPath())).collect(Collectors.toSet());

        Project project = new Project(
                request.getId(),
                request.getName(),
                request.getDescription(),
                request.getExplanation(),
                new ProjectLogoFiles(request.getLogoFiles().getLogoPath()),
                request.getHashtags(),
                projectMembers,
                projectPlanFiles,
                request.getStatus()
        );

        projectRepository.save(project);
    }
}
