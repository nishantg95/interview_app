package com.accenture.inteview.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "QUESTION")
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "BODY", nullable = false)
	private String body;

	@Column(name = "COMMENT", nullable = true)
	private String comment;

	@Column(name = "ADDED_BY", nullable = true)
	private String added_by;

	@ManyToMany
	@JsonIgnoreProperties(value = "questions")
	@JoinTable(name = "QUESTIONS_WITH_TAGS", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	Set<TagEntity> tags;

	public QuestionEntity() {

	}

	/**
	 * @param id
	 * @param body
	 * @param comment
	 * @param tags
	 */
	public QuestionEntity(String body, String comment, Set<TagEntity> tags) {
		this.body = body;
		this.comment = comment;
		this.tags = tags;
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

	public Set<TagEntity> getTags() {
		return this.tags;
	}

	public void setTags(Set<TagEntity> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "QuestionEntity [id=" + this.id + ", title=" + this.title + ", body=" + this.body + ", comment="
				+ this.comment + ", added_by=" + this.added_by + ", tags=" + this.tags + "]";
	}

}
