package com.akash.aipm.service;

import com.akash.aipm.dto.request.CreateProjectRequest;
import com.akash.aipm.dto.request.UpdateProjectRequest;
import com.akash.aipm.dto.response.ProjectResponse;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    List<ProjectResponse> getAllProjects(UUID userId);

    ProjectResponse getProjectById(UUID projectId, UUID userId);

    ProjectResponse createProject(CreateProjectRequest request, UUID ownerId);

    ProjectResponse updateProject(UUID projectId, UpdateProjectRequest request, UUID userId);

    void deleteProject(UUID projectId, UUID userId);

    void archiveProject(UUID projectId, UUID userId);

    List<ProjectResponse> getProjectsByStatus(UUID userId, String status);

    ProjectResponse updateProgress(UUID projectId, UUID userId);
}