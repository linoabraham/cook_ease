package com.cookease.cook_ease.domain.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {

    private LocalDateTime datetime;
    private String message;
    private String path;
}
