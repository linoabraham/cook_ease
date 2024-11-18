package com.cookease.cook_ease.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Choice {

    private Integer index;
    private Message message;
    private String finishReason;


}
