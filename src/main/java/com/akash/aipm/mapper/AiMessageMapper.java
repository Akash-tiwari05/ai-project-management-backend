package com.akash.aipm.mapper;

import com.akash.aipm.dto.response.AiMessageResponse;
import com.akash.aipm.entity.AiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiMessageMapper {

    private final UserMapper userMapper;

    public AiMessageResponse toResponse(AiMessage message) {
        if (message == null) return null;
        return AiMessageResponse.builder()
                .id(message.getId())
                .text(message.getText())
                .role(message.getRole())
                .model(message.getModel())
                .promptTokens(message.getPromptTokens())
                .completionTokens(message.getCompletionTokens())
                .totalTokens(message.getTotalTokens())
                .latencyMs(message.getLatencyMs())
                .sender(message.getSender() != null
                        ? userMapper.toResponse(message.getSender())
                        : null)
                .projectId(message.getProject().getId())
                .sentAt(message.getCreatedAt())
                .createdAt(message.getCreatedAt())
                .build();
    }
}