package com.accenture.inteview.models;

import java.util.Set;

import com.accenture.inteview.entities.QuestionEntity;

public interface Tag {

	Long getId();

	void setId(Long id);

	String getName();

	void setName(String name);

	Set<QuestionEntity> getQuestions();

	void setQuestions(Set<QuestionEntity> questions);
}
