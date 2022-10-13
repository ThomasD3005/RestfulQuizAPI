package com.cooksys.quiz_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizRequestDto {

    private String name;

    public List<QuestionRequestDto> questions;
}
