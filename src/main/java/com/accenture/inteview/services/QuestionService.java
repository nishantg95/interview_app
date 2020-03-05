package com.accenture.inteview.services;

import java.util.List;
import java.util.Set;

import com.accenture.inteview.entities.QuestionEntity;

public interface QuestionService {

	List<QuestionEntity> getAllQuestions();

	Set<QuestionEntity> getQuestionsByTagsNames(String[] tagsNames);

	Set<QuestionEntity> getQuestionsByTagName(String tag);

	QuestionEntity getQuestionById(Long id);

	QuestionEntity addQuestion(QuestionEntity questionEntity);

	QuestionEntity updateQuestion(QuestionEntity questionEntity);

	int deleteQuestion(QuestionEntity questionEntity);
}
