package com.accenture.inteview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.inteview.entities.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

	@Modifying
	@Query("DELETE FROM QuestionEntity q WHERE q.id = :id")
	int deleteQuestionById(@Param("id") Long id);

}
