
package com.accenture.inteview.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.beans.BeanUtils;

import com.accenture.inteview.models.TagView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Indexed
@Table(name = "TAG")
public class TagEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Field
	@Column(name = "NAME", nullable = false)
	private String name;

	@JsonIgnoreProperties(value = "tags")
	@ContainedIn
	@ManyToMany(mappedBy = "tags")
	Set<QuestionEntity> questions;

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

	public Set<QuestionEntity> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<QuestionEntity> questions) {
		this.questions = questions;
	}

	public TagEntity() {
	}

	public TagEntity(TagView tagView) {
		BeanUtils.copyProperties(tagView, this);
	}
}
