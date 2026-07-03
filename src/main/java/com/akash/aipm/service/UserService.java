package com.akash.aipm.service;

import com.akash.aipm.dto.request.UpdateUserRequest;
import com.akash.aipm.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse getUserById(UUID userId);

    UserResponse updateUser(UUID userId, UpdateUserRequest request);

    void deleteUser(UUID userId);

    List<UserResponse> searchUsers(String query);

    void updateStatus(UUID userId, String status);

    void updatePlan(UUID userId, String planId);
}