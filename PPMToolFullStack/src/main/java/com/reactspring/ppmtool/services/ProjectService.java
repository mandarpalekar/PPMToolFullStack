package com.reactspring.ppmtool.services;

import com.reactspring.ppmtool.domain.Project;
import com.reactspring.ppmtool.exceptions.ProjectIdException;
import com.reactspring.ppmtool.repositories.ProjectRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.ec.point.ProjectivePoint;

import java.util.List;

@Service
@Data
@Slf4j
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    
    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            log.error("Binding Result: {}", e);
            throw new ProjectIdException("Project id: " + project.getProjectIdentifier().toUpperCase() + " already exists");
        }

    }

    public Project findProjectByIdentifier(String projectIdentifier){
        log.error("Project Identifier: {}", projectIdentifier);
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Project id: " + projectIdentifier.toUpperCase() + " does not exists");
        }
        return project;
    }

    public List<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if(project == null){
            throw new ProjectIdException("Unable to delete project with ID : " + projectIdentifier.toUpperCase() + " . Project does not exists");
        }
        projectRepository.delete(project);
    }
}
