package com.attech.service;


import com.attech.dto.ProjectRequest;
import com.attech.dto.ProjectResponse;
import com.attech.entity.Project;
import com.attech.repo.ProjectRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepo projectRepo;

    public ProjectResponse addProject(ProjectRequest projectRequest){
        Project project = Project.builder()
                .name(projectRequest.name())
                .contractor(projectRequest.contractor())
                .supervisor(projectRequest.supervisor())
                .trackingNumber(projectRequest.trackingNumber())
                .build();

        Project save = projectRepo.save(project);
        return new ProjectResponse(save.getId(),
                save.getName(),
                save.getContractor(),
                save.getSupervisor(),
                save.getTrackingNumber());

    }

    public List<ProjectResponse> findProjects() {
        return projectRepo.findAll()
                .stream()
                .map(project -> new ProjectResponse(project.getId(),
                        project.getName(),
                        project.getContractor(),
                        project.getSupervisor(),
                        project.getTrackingNumber()
                )).collect(Collectors.toList());
    }
}
