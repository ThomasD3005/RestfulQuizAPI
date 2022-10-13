package com.cooksys.quiz_api.dtos;

import com.cooksys.quiz_api.entities.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionRequestDto {

    private String text;

    public List<AnswerRequestDto> answers;
}
