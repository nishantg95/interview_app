package com.accenture.inteview.services;

import java.util.List;
import java.util.Set;

import com.accenture.inteview.models.Question;

public interface QuestionService {

	List<Question> getAllQuestions();

	Set<Question> getQuestionsByTagsNames(String[] tagsNames);

	Set<Question> getQuestionsByTagName(String tag);

	Question getQuestionById(Long id);

	Question addQuestion(Question question);

	Question updateQuestion(Question question);

	int deleteQuestion(Question question);
}
