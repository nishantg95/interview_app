package com.accenture.inteview.models;

import java.util.Set;

import com.accenture.inteview.entities.TagEntity;

public interface Question {

	Long getId();

	void setId(Long id);

	String getTitle();

	void setTitle(String title);

	String getBody();

	void setBody(String body);

	String getComment();

	void setComment(String comment);

	String getAdded_by();

	void setAdded_by(String added_by);

	Set<TagEntity> getTags();

	void setTags(Set<TagEntity> tags);
}
