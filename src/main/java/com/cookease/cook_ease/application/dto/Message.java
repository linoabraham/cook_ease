package com.cookease.cook_ease.application.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Serializable {


    private String role;
    private String content;

}
