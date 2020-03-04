/**
 *
 */
package com.accenture.inteview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.inteview.entities.TagEntity;

/**
 * @author nishant.b.grover
 *
 */
@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

	@Modifying
	@Query("DELETE FROM TagEntity t WHERE t.id = :id")
	int deleteTagById(@Param("id") Long id);

	Optional<TagEntity> findByNameIgnoreCase(String name);

}
