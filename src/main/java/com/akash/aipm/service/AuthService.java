package com.akash.aipm.service;

import com.akash.aipm.dto.request.RegisterRequest;
import com.akash.aipm.dto.request.LoginRequest;
import com.akash.aipm.dto.request.ResetPasswordRequest;
import com.akash.aipm.dto.response.AuthResponse;
import com.akash.aipm.dto.response.UserResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    void logout(String refreshToken);

    AuthResponse refresh(String refreshToken);

    UserResponse getCurrentUser(String userId);

    void sendPasswordResetEmail(String email);

    void resetPassword(ResetPasswordRequest request);

    void verifyEmail(String token);
}
