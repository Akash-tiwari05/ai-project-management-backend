package com.akash.aipm.dto.request;


import com.akash.aipm.enums.Priority;
import com.akash.aipm.enums.ProjectStatus;
import jakarta.validation.constraints.Size;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

public record UpdateProjectRequest(

        @Size(min = 1, max = 200, message = "Name must be between 1 and 200 characters")
        String name,

        @Size(max = 5000, message = "Description cannot exceed 5000 characters")
        String description,

        ProjectStatus status,

       Priority priority,

        @Size(max = 7, message = "Color must be a valid hex code")
        String color,

        URI githubUrl,

        LocalDate dueDate,

        List<String> skillTags,

        List<String> tags

) {}