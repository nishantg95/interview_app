package com.accenture.inteview.services;

import java.util.List;
import java.util.Set;

import com.accenture.inteview.models.QuestionView;

public interface QuestionService {

	List<QuestionView> getAllQuestions();

	Set<QuestionView> getQuestionsByTagNameList(String[] tagsNames);

	Set<QuestionView> getQuestionsByTagName(String tagName);

	QuestionView getQuestionById(Long id);

	QuestionView saveQuestion(QuestionView questionView);

	QuestionView updateQuestion(QuestionView questionView);

	int deleteQuestion(QuestionView questionView);
}
