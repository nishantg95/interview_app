/**
 *
 */
package com.accenture.inteview.services;

import java.util.List;

import com.accenture.inteview.entities.TagEntity;

/**
 * @author nishant.b.grover
 *
 */
public interface TagService {

	List<TagEntity> getAllTags();

	TagEntity addTag(TagEntity tagEntity);

	TagEntity updateTag(TagEntity tagEntity);

	void delete(TagEntity tagEntity);

}
