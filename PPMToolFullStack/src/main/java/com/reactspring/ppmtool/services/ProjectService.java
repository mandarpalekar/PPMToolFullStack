package com.reactspring.ppmtool.services;

import com.reactspring.ppmtool.domain.Project;
import com.reactspring.ppmtool.repositories.ProjectRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    
    public Project saveOrUpdateProject(Project project){
        return projectRepository.save(project);
    }
}
