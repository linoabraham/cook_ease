package com.cookease.cook_ease.exception;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime datetime;
    private int status;
    private String error;
    private String message;
    private String path;
}