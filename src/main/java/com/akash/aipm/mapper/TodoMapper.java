package com.akash.aipm.mapper;

import com.akash.aipm.dto.request.CreateTodoRequest;
import com.akash.aipm.dto.response.TodoResponse;
import com.akash.aipm.entity.Todo;
import com.akash.aipm.enums.Priority;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TodoMapper {

    private final UserMapper userMapper;

    public TodoResponse toResponse(Todo todo) {
        if (todo == null) return null;

        return TodoResponse.builder()
                .id(todo.getId())
                .text(todo.getText())
                .done(todo.isDone())
                .priority(todo.getPriority())
                .dueDate(todo.getDueDate())
                .position(todo.getPosition())
                .completedAt(todo.getCompletedAt())
                .assignee(userMapper.toResponse(todo.getAssignee()))
                .createdBy(userMapper.toResponse(todo.getCreatedBy()))
                .projectId(todo.getProject().getId())
                .overdue(todo.isOverdue())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }

    public Todo toEntity(CreateTodoRequest request) {
        return Todo.builder()
                .text(request.text())
                .done(false)
                .priority(request.priority() != null
                        ? request.priority()
                        : Priority.MEDIUM)
                .dueDate(request.dueDate())
                .position(request.position() != null
                        ? request.position()
                        : 0)
                .build();
    }
}
