package com.akash.aipm.dto.response;

import com.akash.aipm.enums.Priority;
import com.akash.aipm.enums.ProjectStatus;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Builder
public record ProjectResponse(

        UUID id,
        String name,
        String description,
        ProjectStatus status,
        int progress,
        Priority priority,
        String color,
        String githubUrl,
        LocalDate dueDate,
        int tasksTotal,
        int tasksDone,
        List<String> skillTags,
        List<String> tags,
        UserResponse owner,
        List<ProjectMemberResponse> members,
        int memberCount,
        Instant createdAt,
        Instant updatedAt
) {
}
