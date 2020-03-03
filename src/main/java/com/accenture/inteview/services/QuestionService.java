package com.accenture.inteview.services;

import java.util.List;

import com.accenture.inteview.entities.QuestionEntity;

public interface QuestionService {

	List<QuestionEntity> getAllQuestions();

	QuestionEntity getQuestionById(Long id);

	QuestionEntity addQuestion(QuestionEntity questionEntity);

	QuestionEntity updateQuestion(QuestionEntity questionEntity);

	int deleteQuestion(QuestionEntity questionEntity);
}
