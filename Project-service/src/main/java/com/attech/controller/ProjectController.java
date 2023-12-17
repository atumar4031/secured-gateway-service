package com.attech.controller;

import com.attech.dto.ProjectRequest;
import com.attech.dto.ProjectResponse;
import com.attech.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponse> addProject(@RequestBody ProjectRequest projectRequest){
        ProjectResponse response = projectService.addProject(projectRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> findProjects(){
        List<ProjectResponse> response = projectService.findProjects();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
