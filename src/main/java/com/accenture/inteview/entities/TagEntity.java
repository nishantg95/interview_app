/**
 *
 */
package com.accenture.inteview.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.accenture.inteview.entities.QuestionEntity;

/**
 * @author nishant.b.grover
 *
 */
@Entity
@Table(name = "TAG")
public class TagEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToMany
	Set<QuestionEntity> questions;

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

	@Override
	public String toString() {
		return "TagEntity [id=" + this.id + ", name=" + this.name + "]";
	}

}
