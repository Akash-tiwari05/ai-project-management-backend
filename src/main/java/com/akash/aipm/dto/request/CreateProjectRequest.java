package com.akash.aipm.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateProjectRequest {

    @NotBlank(message = "Project name is required")
    @Size(min = 1, max = 200, message = "Name must be between 1 and 200 characters")
    private String name;

    private String description;

    @Pattern(regexp = "low|medium|high", message = "Priority must be low, medium or high")
    private String priority = "medium";

    @Pattern(regexp = "^#([A-Fa-f0-9]{6})$", message = "Color must be a valid hex code")
    private String color = "#3B82F6";

    private String githubUrl;

    private LocalDate dueDate;

    private List<String> skillTags;

    private List<String> tags;
}