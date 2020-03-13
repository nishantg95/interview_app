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
import javax.validation.constraints.NotBlank;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.beans.BeanUtils;

import com.accenture.inteview.models.Question;
import com.accenture.inteview.models.Tag;

@Indexed
@Entity
@Table(name = "QUESTION")
public class QuestionEntity implements Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Field
	@Column(name = "TITLE", nullable = false)
	private String title;

	@Field
	@NotBlank
	@Column(name = "BODY", nullable = false)
	private String body;

	@Column(name = "COMMENT", nullable = true)
	private String comment;

	@Column(name = "ADDED_BY", nullable = true)
	private String added_by;

	@IndexedEmbedded
	@ManyToMany
	@JoinTable(name = "QUESTIONS_WITH_TAGS", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	Set<TagEntity> tags;

	public QuestionEntity() {
	}

	public QuestionEntity(Question question) {
		BeanUtils.copyProperties(question, this, Question.class);
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

	@SuppressWarnings("unchecked")
	@Override
	public void setTags(Set<? extends Tag> tags) {
		this.tags = (Set<TagEntity>) tags;
	}
}
