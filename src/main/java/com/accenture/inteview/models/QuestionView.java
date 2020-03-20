package com.accenture.inteview.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.accenture.inteview.entities.QuestionEntity;
import com.accenture.inteview.entities.TagEntity;
import com.accenture.inteview.util.ToLowerCaseDeserializer;
import com.accenture.inteview.util.ToLowerCaseSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class QuestionView {

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

	public QuestionView(QuestionEntity questionEntity) {
		this.id = questionEntity.getId();
		this.title = questionEntity.getTitle();
		this.body = questionEntity.getBody();
		this.comment = questionEntity.getComment();
		this.added_by = questionEntity.getAdded_by();
		Set<TagEntity> tagEntities = questionEntity.getTags();
		Set<TagView> tagViews = new HashSet<TagView>();
		for (TagEntity tagEntity : tagEntities) {
			tagViews.add(new TagView(tagEntity));
		}
		this.tags = tagViews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAdded_by() {
		return added_by;
	}

	public void setAdded_by(String added_by) {
		this.added_by = added_by;
	}

	public Set<TagView> getTags() {
		return tags;
	}

	public void setTags(Set<TagView> tags) {
		this.tags = tags;
	}

}
