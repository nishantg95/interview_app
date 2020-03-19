/**
 *
 */
package com.accenture.inteview.services;

import java.util.List;

import com.accenture.inteview.models.TagView;

/**
 * @author nishant.b.grover
 *
 */
public interface TagService {

	List<TagView> getAllTags();

	TagView getTagById(Long id);

	TagView getTagByName(String name);

	List<TagView> saveTagList(List<TagView> tagViews);

	TagView saveTag(TagView tagView);

	int deleteTag(TagView tagView);

}
