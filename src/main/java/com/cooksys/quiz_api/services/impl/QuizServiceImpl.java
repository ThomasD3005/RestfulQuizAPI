package com.cooksys.quiz_api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.AnswerRepository;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;
  private final QuestionMapper questionMapper;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  private Quiz getQuiz(Long id) throws NotFoundException {
    Optional<Quiz> optionalQuiz = quizRepository.findById(id);
    if (optionalQuiz.isEmpty()){
      throw new NotFoundException("Quiz " + id + "doesn't exist");
    }
    return optionalQuiz.get();
  }

  private Question getQuestion(Long id) throws NotFoundException{

    Optional<Question> optionalQuestion = questionRepository.findById(id);
    if (optionalQuestion.isEmpty()){
      throw new NotFoundException("Question doesn't exist");
    }
    return optionalQuestion.get();

  }



  @Override
  public List<QuizResponseDto> getAllQuizzes() {
    return quizMapper.entitiesToDtos(quizRepository.findAll());
  }

  @Override
  public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
    Quiz quiz = quizMapper.dtoToEntity(quizRequestDto);
    quizRepository.saveAndFlush(quiz);
    for (Question question : quiz.getQuestions()){
      question.setQuiz(quiz);
      questionRepository.saveAndFlush(question);
      for (Answer answer: question.getAnswers()){
        answer.setQuestion(question);
        answerRepository.saveAndFlush(answer);
      }
    }
    return quizMapper.entityToDto(quiz);
  }

  @Override
  public QuizResponseDto deleteQuiz(Long id) throws NotFoundException {
    Quiz quiz = getQuiz(id);
    quizRepository.delete(quiz);
    return quizMapper.entityToDto(quiz);
  }

  @Override
  public QuizResponseDto updateQuiz(Long id, QuizRequestDto quizRequestDto) throws NotFoundException {
    Quiz updatedQuiz = getQuiz(id);
    updatedQuiz.setName(quizRequestDto.getName());
    return quizMapper.entityToDto(quizRepository.saveAndFlush(updatedQuiz));
    }

  @Override
  public QuestionResponseDto randomQuestion(Long id) throws NotFoundException {
    //random question from specified quiz
    Quiz quiz = getQuiz(id);
    List<Question> questions = quiz.getQuestions();
    Random rand = new Random();
    Question randomQ = questions.get(rand.nextInt(questions.size()));
    return questionMapper.entityToDto(randomQ);

  }

  @Override
  public QuizResponseDto modifiedQuiz(Long id, QuestionRequestDto questionRequestDto) throws NotFoundException {
    Quiz quiz = getQuiz(id);
    List<Question> questions = new ArrayList<>();

    Question newQuestion = questionMapper.dtoToEntity(questionRequestDto);
    questionRepository.saveAndFlush(newQuestion);

    questions.add(newQuestion);
    questions.addAll(quiz.getQuestions());
    quiz.setQuestions(questions);
    return quizMapper.entityToDto(quizRepository.saveAndFlush(quiz));
  }

  @Override
  public QuestionResponseDto deletedQuestion(Long id, Long questionId) throws NotFoundException {
    Quiz quiz = getQuiz(id);
    Question question = getQuestion(questionId);
    questionRepository.delete(question);

    return questionMapper.entityToDto(question);
  }

}
