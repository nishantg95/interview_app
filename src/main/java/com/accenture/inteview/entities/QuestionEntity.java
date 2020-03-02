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

@Entity
@Table(name = "QUESTION")
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "COMMENT", nullable = true)
	private String comment;

	@ManyToMany
	@JoinTable(name = "QUESTIONS_WITH_TAGS", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	Set<TagEntity> tags;

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

	public String getcomment() {
		return this.comment;
	}

	public void setcomment(String comment) {
		this.comment = comment;
	}

	public Set<TagEntity> getTags() {
		return this.tags;
	}

	public void setTags(Set<TagEntity> tags) {
		this.tags = tags;

	}

	@Override
	public String toString() {
		return "QuestionEntity [id=" + this.id + ", name=" + this.name + ", comment=" + this.comment + "]";
	}

}
