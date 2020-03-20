package com.accenture.inteview.entities;

import java.util.HashSet;
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

import com.accenture.inteview.models.QuestionView;
import com.accenture.inteview.models.TagView;

@Indexed
@Entity
@Table(name = "QUESTION")
public class QuestionEntity {

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

	public QuestionEntity(QuestionView questionView) {
		this.id = questionView.getId();
		this.title = questionView.getTitle();
		this.body = questionView.getBody();
		this.comment = questionView.getComment();
		this.added_by = questionView.getAdded_by();
		Set<TagView> tagViews = questionView.getTags();
		Set<TagEntity> tagEntities = new HashSet<TagEntity>();
		for (TagView tagView : tagViews) {
			tagEntities.add(new TagEntity(tagView));
		}
		this.tags = tagEntities;
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

	public Set<TagEntity> getTags() {
		return tags;
	}

	public void setTags(Set<TagEntity> tags) {
		this.tags = tags;
	}

}
