package com.cooksys.quiz_api.services;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import javassist.NotFoundException;

public interface QuizService {

  List<QuizResponseDto> getAllQuizzes();

  QuizResponseDto createQuiz(QuizRequestDto quizRequestDto);

  QuizResponseDto deleteQuiz(Long id) throws NotFoundException;

  QuizResponseDto updateQuiz(Long id, QuizRequestDto quizRequestDto) throws NotFoundException;

  QuestionResponseDto randomQuestion(Long id) throws NotFoundException;

  QuizResponseDto modifiedQuiz(Long id, QuestionRequestDto questionRequestDto) throws NotFoundException;

  QuestionResponseDto deletedQuestion(Long id, Long questionId) throws NotFoundException;
}
