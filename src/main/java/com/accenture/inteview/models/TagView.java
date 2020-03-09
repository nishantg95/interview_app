package com.accenture.inteview.models;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import com.accenture.inteview.entities.QuestionEntity;
import com.accenture.inteview.util.ToLowerCaseDeserializer;
import com.accenture.inteview.util.ToLowerCaseSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TagView implements Tag {

	private Long id;

	@NotBlank
	@JsonSerialize(using = ToLowerCaseSerializer.class)
	@JsonDeserialize(using = ToLowerCaseDeserializer.class)
	private String name;

	@JsonIgnoreProperties(value = "tags")
	Set<QuestionEntity> questions;

	public TagView() {
	}

	public TagView(Tag tag) {
		BeanUtils.copyProperties(tag, this, Tag.class);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<QuestionEntity> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<QuestionEntity> questions) {
		this.questions = questions;
	}
}
