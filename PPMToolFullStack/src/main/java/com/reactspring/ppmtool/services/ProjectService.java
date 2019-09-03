package com.reactspring.ppmtool.services;

import com.reactspring.ppmtool.domain.Project;
import com.reactspring.ppmtool.exceptions.ProjectIdException;
import com.reactspring.ppmtool.repositories.ProjectRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.ec.point.ProjectivePoint;

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
}
