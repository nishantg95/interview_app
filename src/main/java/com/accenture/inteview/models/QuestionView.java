package com.accenture.inteview.models;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;

import com.accenture.inteview.util.ToLowerCaseDeserializer;
import com.accenture.inteview.util.ToLowerCaseSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class QuestionView implements Question {

	private Long id;

	@JsonSerialize(using = ToLowerCaseSerializer.class)
	@JsonDeserialize(using = ToLowerCaseDeserializer.class)
	private String title;

	@NotBlank
	private String body;

	private String comment;

	@JsonSerialize(using = ToLowerCaseSerializer.class)
	@JsonDeserialize(using = ToLowerCaseDeserializer.class)
	private String added_by;

	Set<TagView> tags;

	public QuestionView() {

	}

	public QuestionView(Question question) {
		BeanUtils.copyProperties(question, this, Question.class);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAdded_by() {
		return this.added_by;
	}

	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}

	public Set<TagView> getTags() {
		return this.tags;
	}

	@SuppressWarnings("unchecked")
	public void setTags(Set<? extends Tag> tags) {
		this.tags = (Set<TagView>) tags;
	}

	@Override
	public String toString() {
		return "QuestionView [id=" + id + ", title=" + title + ", body=" + body + ", comment=" + comment + ", added_by="
				+ added_by + ", tags=" + tags + "]";
	}
}
