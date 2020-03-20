package com.accenture.inteview.models;

import java.util.Collections;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import com.accenture.inteview.entities.TagEntity;
import com.accenture.inteview.util.ToLowerCaseDeserializer;
import com.accenture.inteview.util.ToLowerCaseSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TagView {

	public static TagView NotFound = new TagView(new Long(0), "Tag Not Found", Collections.emptySet());

	private Long id;

	@NotBlank
	@JsonSerialize(using = ToLowerCaseSerializer.class)
	@JsonDeserialize(using = ToLowerCaseDeserializer.class)
	private String name;

	Set<QuestionView> questions;

	public TagView() {
	}

	public TagView(TagEntity tagEntity) {
		BeanUtils.copyProperties(tagEntity, this);
	}

	public TagView(Long id, String name, Set<QuestionView> questions) {
		this.id = id;
		this.name = name;
		this.questions = questions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<QuestionView> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<QuestionView> questions) {
		this.questions = questions;
	}
}
