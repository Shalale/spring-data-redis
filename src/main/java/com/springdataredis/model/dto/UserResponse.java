package com.springdataredis.model.dto;

import com.springdataredis.model.constants.Status;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserResponse implements Serializable {
    private String username;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Status status;
}
