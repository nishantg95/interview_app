package com.accenture.inteview.services;

import java.util.List;

import com.accenture.inteview.entities.QuestionEntity;

public interface QuestionService {

	Object getQuestion(Long id);

	QuestionEntity addQuestion(QuestionEntity questionEntity);

	List<QuestionEntity> getAllQuestions();

	QuestionEntity updateQuestion(QuestionEntity questionEntity);

	int deleteQuestion(QuestionEntity questionEntity);

}
