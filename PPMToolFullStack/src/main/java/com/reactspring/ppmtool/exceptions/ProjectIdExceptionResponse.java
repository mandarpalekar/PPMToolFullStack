package com.reactspring.ppmtool.exceptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class ProjectIdExceptionResponse {
    private String projectIdentifier;
}
