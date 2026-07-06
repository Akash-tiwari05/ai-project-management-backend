package com.akash.aipm.mapper;

import com.akash.aipm.dto.response.ProjectMemberResponse;
import com.akash.aipm.entity.ProjectMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectMemberMapper {

    private final UserMapper userMapper;

    public ProjectMemberResponse toResponse(ProjectMember member) {
        if (member == null) return null;
        return ProjectMemberResponse.builder()
                .id(member.getId())
                .user(userMapper.toResponse(member.getUser()))
                .role(member.getRole().getValue())
                .joinedAt(member.getCreatedAt())
                .build();
    }
}