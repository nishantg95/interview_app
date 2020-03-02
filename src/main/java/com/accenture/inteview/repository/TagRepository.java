/**
 *
 */
package com.accenture.inteview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.inteview.entities.TagEntity;

/**
 * @author nishant.b.grover
 *
 */
@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

}
