package com.cooksys.quiz_api.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerRequestDto {

    private Long id;

    private String text;

    private boolean correct;
}
