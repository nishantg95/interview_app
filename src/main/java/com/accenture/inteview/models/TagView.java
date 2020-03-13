package com.accenture.inteview.models;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import com.accenture.inteview.util.ToLowerCaseDeserializer;
import com.accenture.inteview.util.ToLowerCaseSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TagView implements Tag {

	private Long id;

	@NotBlank
	@JsonSerialize(using = ToLowerCaseSerializer.class)
	@JsonDeserialize(using = ToLowerCaseDeserializer.class)
	private String name;

	Set<QuestionView> questions;

	public TagView() {
	}

	public TagView(Tag tag) {
		BeanUtils.copyProperties(tag, this, Tag.class);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Set<QuestionView> getQuestions() {
		return this.questions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setQuestions(Set<? extends Question> questions) {
		this.questions = (Set<QuestionView>) questions;

	}
}
