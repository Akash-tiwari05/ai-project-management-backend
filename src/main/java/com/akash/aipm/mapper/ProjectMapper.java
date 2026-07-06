package com.akash.aipm.mapper;

import com.akash.aipm.dto.request.CreateProjectRequest;
import com.akash.aipm.dto.response.ProjectResponse;
import com.akash.aipm.entity.Project;
import com.akash.aipm.enums.Priority;
import com.akash.aipm.enums.ProjectStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final UserMapper userMapper;
    private final ProjectMemberMapper memberMapper;

    public ProjectResponse toResponse(Project project) {
        if (project == null) return null;
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .status(project.getStatus())
                .progress(project.getProgress())
                .priority(project.getPriority())
                .color(project.getColor())
                .githubUrl(project.getGithubUrl())
                .dueDate(project.getDueDate())
                .tasksTotal(project.getTasksTotal())
                .tasksDone(project.getTasksDone())
                .skillTags(project.getSkillTags())
                .tags(project.getTags())
                .owner(userMapper.toResponse(project.getOwner()))
                .members(project.getMembers() == null ? Collections.emptyList()
                        : project.getMembers().stream()
                        .map(memberMapper::toResponse)
                        .collect(Collectors.toList()))
                .memberCount(project.getMembers() == null ? 0 : project.getMembers().size())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }

    public Project toEntity(CreateProjectRequest request) {
        return Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .priority(request.getPriority() != null
                        ? Priority.from(request.getPriority())
                        : Priority.MEDIUM)
                .color(request.getColor() != null
                        ? request.getColor()
                        : "#3B82F6")
                .githubUrl(request.getGithubUrl())
                .dueDate(request.getDueDate())
                .status(ProjectStatus.PLANNING)
                .progress(0)
                .tasksTotal(0)
                .tasksDone(0)
                .skillTags(request.getSkillTags() != null
                        ? request.getSkillTags()
                        : Collections.emptyList())
                .tags(request.getTags() != null
                        ? request.getTags()
                        : Collections.emptyList())
                .build();
    }
}