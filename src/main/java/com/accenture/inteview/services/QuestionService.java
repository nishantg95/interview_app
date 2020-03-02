package com.accenture.inteview.services;

import com.accenture.inteview.entities.QuestionEntity;

public interface QuestionService {

	QuestionEntity getQuestion(Long id);

	QuestionEntity addQuestion(QuestionEntity questionEntity);

}
