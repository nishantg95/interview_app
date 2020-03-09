/**
 *
 */
package com.accenture.inteview.services;

import java.util.List;

import com.accenture.inteview.models.Tag;

/**
 * @author nishant.b.grover
 *
 */
public interface TagService {

	List<Tag> getAllTags();

	Tag getTagById(Long id);

	Tag getTagByName(String name);

	Tag addTag(Tag tag);

//	Tag updateTag(Tag tag);

	int deleteTag(Tag tag);
}
