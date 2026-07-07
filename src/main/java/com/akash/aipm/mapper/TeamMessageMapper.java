package com.akash.aipm.mapper;

import com.akash.aipm.dto.request.SendMessageRequest;
import com.akash.aipm.dto.response.TeamMessageResponse;
import com.akash.aipm.entity.TeamMessage;
import com.akash.aipm.enums.MessageType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TeamMessageMapper {

    private final UserMapper userMapper;

    public TeamMessageResponse toResponse(TeamMessage message) {
        return toResponse(message, false, 0L);
    }

    public TeamMessageResponse toResponse(TeamMessage message,
                                          boolean readByCurrentUser,
                                          long readCount) {
        if (message == null) return null;

        boolean isDeleted = message.isDeleted();

        return TeamMessageResponse.builder()
                .id(message.getId())
                .text(isDeleted ? "This message was removed" : message.getText())
                .type(message.getType().getValue())
                .metadata(isDeleted ? null : message.getMetadata())
                .edited(message.isEdited())
                .editedAt(message.getEditedAt())
                .deleted(isDeleted)
                .replyTo(message.getReplyTo() != null
                        ? toResponse(message.getReplyTo())
                        : null)
                .sender(userMapper.toResponse(message.getSender()))
                .projectId(message.getProject().getId())
                .readByCurrentUser(readByCurrentUser)
                .readCount(readCount)
                .sentAt(message.getCreatedAt())
                .createdAt(message.getCreatedAt())
                .build();
    }

    public TeamMessage toEntity(SendMessageRequest request) {
        return TeamMessage.builder()
                .text(request.text())
                .type(request.messageType() != null
                        ? request.messageType()
                        : MessageType.TEXT)
                .build();
    }
}