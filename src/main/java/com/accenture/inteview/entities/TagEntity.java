
package com.accenture.inteview.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.accenture.inteview.models.Tag;

@Entity
@Table(name = "TAG")
public class TagEntity implements Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToMany(mappedBy = "tags")
	Set<QuestionEntity> questions;
	// Set<QuestionEntity> questions = new HashSet<>();

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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<QuestionEntity> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<QuestionEntity> questions) {
		this.questions = questions;
	}
}
