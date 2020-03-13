
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

import com.accenture.inteview.models.Question;
import com.accenture.inteview.models.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Indexed
@Table(name = "TAG")
public class TagEntity implements Tag {

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

	public TagEntity() {
	}

	public TagEntity(Tag tag) {
		BeanUtils.copyProperties(tag, this, Tag.class);
	}

	/**
	 * @param id
	 * @param name
	 * @param questions
	 */
	public TagEntity(String name, Set<QuestionEntity> questions) {
		this.name = name;
		this.questions = questions;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Set<QuestionEntity> getQuestions() {
		return this.questions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setQuestions(Set<? extends Question> questions) {
		this.questions = (Set<QuestionEntity>) questions;

	}
}
