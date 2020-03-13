package com.accenture.inteview.models;

import java.util.Set;

public interface Tag {

	Long getId();

	void setId(Long id);

	String getName();

	void setName(String name);

	Set<? extends Question> getQuestions();

	void setQuestions(Set<? extends Question> questions);
}
