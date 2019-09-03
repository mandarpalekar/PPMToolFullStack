package com.reactspring.ppmtool.controllers;

import com.reactspring.ppmtool.domain.Project;
import com.reactspring.ppmtool.services.MapValidationErrorService;
import com.reactspring.ppmtool.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService errorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        log.info("Project Object: {}", project);
        log.info("Error Map info: {}", errorService.MapValidationService(bindingResult) == null ?
                null : errorService.MapValidationService(bindingResult).toString());
        return (errorService.MapValidationService(bindingResult) != null) ? errorService.MapValidationService(bindingResult)
                : new ResponseEntity<Project>(projectService.saveOrUpdateProject(project), HttpStatus.CREATED);
    }
}
