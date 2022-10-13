package com.cooksys.quiz_api.controllers;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.services.QuizService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

  private final QuizService quizService;

  @GetMapping
  public List<QuizResponseDto> getAllQuizzes() {
    return quizService.getAllQuizzes();
  }

  @PostMapping
  public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDto){
    return quizService.createQuiz(quizRequestDto);
  }

  @DeleteMapping("/{id}")
  public QuizResponseDto deleteQuiz(@PathVariable Long id) throws NotFoundException {
    return quizService.deleteQuiz(id);
  }

  @PatchMapping("/{id}")
  public QuizResponseDto updateQuiz(@PathVariable Long id, @RequestBody QuizRequestDto quizRequestDto) throws NotFoundException {
    return quizService.updateQuiz(id, quizRequestDto);
  }
  @GetMapping("/{id}/random")
  public QuestionResponseDto randomQuizQuestion(@PathVariable Long id) throws NotFoundException {
    return quizService.randomQuestion(id);
  }
  @PatchMapping("/{id}/add")
  public QuizResponseDto addQuizQuestion(@PathVariable Long id, @RequestBody QuestionRequestDto questionRequestDto) throws NotFoundException {
    return quizService.modifiedQuiz(id, questionRequestDto);
  }

  @DeleteMapping("/{id}/delete/{questionId}")
  public QuestionResponseDto deleteQuestion(@PathVariable Long id, @PathVariable Long questionId) throws NotFoundException {
    return quizService.deletedQuestion(id, questionId);
  }

}
