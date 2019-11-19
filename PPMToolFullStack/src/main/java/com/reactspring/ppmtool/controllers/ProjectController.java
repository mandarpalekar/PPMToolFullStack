package com.reactspring.ppmtool.controllers;

import com.reactspring.ppmtool.domain.Project;
import com.reactspring.ppmtool.services.MapValidationErrorService;
import com.reactspring.ppmtool.services.ProjectService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService errorService;

    @PostMapping("/create")
    @ApiOperation(value = "Create a new Project", notes = "Provide the Project in the Request Body")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        log.info("Project Object: {}", project);
        log.info("Error Map info: {}", errorService.MapValidationService(bindingResult) == null ?
                null : errorService.MapValidationService(bindingResult).toString());
        return (errorService.MapValidationService(bindingResult) != null) ? errorService.MapValidationService(bindingResult)
                : new ResponseEntity<Project>(projectService.saveOrUpdateProject(project), HttpStatus.CREATED);
    }

    @GetMapping("/read/{projectIdentifier}")
    @ApiOperation(value = "Find Project by ID", notes = "Provide an ID to look up a specific project", response = Project.class)
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIdentifier) {
        log.info("Project ID: {}", projectIdentifier);
        return new ResponseEntity<Project>(projectService.findProjectByIdentifier(projectIdentifier), HttpStatus.OK);
    }

    @GetMapping("/read")
    @ApiOperation(value = "Find All Projects")
    public List<Project> getAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/delete/{projectIdentifier}")
    @ApiOperation(value = "Delete Project by ID", notes = "Provide an ID to look up a specific project", response = Project.class)
    public  ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier) {
        projectService.deleteProjectByIdentifier(projectIdentifier);
        return new ResponseEntity<String>("Project with ID: " + projectIdentifier + " deleted successfully",HttpStatus.OK);
    }

    @PutMapping("/update/{projectIdentifier}")
    @ApiOperation(value = "Update Project by ID", notes = "Provide an ID to look up a specific project", response = Project.class)
    public ResponseEntity<?> updateProjectByIdentifier(@PathVariable String projectIdentifier, @Valid @RequestBody Project project,  BindingResult bindingResult) {
        log.info("Project Object: {}", project);
        log.info("Error Map info: {}", errorService.MapValidationService(bindingResult) == null ?
                null : errorService.MapValidationService(bindingResult).toString());
        Project projectUpdate = projectService.findProjectByIdentifier(projectIdentifier);
        projectUpdate = project;
        return (errorService.MapValidationService(bindingResult) != null) ? errorService.MapValidationService(bindingResult)
                : new ResponseEntity<Project>(projectService.saveOrUpdateProject(projectUpdate), HttpStatus.OK);
    }
}
