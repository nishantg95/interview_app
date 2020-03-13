package com.accenture.inteview.models;

import java.util.Set;

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

	Set<? extends Tag> getTags();

	void setTags(Set<? extends Tag> tags);

}
