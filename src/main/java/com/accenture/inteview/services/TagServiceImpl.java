/**
 *
 */
package com.accenture.inteview.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.inteview.entities.TagEntity;
import com.accenture.inteview.models.Tag;
import com.accenture.inteview.models.TagView;
import com.accenture.inteview.repository.TagRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author nishant.b.grover
 *
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;

	@Override
	// TODO weird : what is this annotation doing here
	@JsonIgnoreProperties(value = "questions")
	public List<Tag> getAllTags() {
		List<Tag> tags = new ArrayList<>();
		List<TagEntity> tagEntities = this.tagRepository.findAll();
		for (Tag tag : tagEntities) {
			Tag tagView = new TagView(tag);
			tags.add(tagView);
		}
		return tags;
	}

	@Override
	public Tag getTagById(Long id) {
		Optional<TagEntity> tagOptional = tagRepository.findById(id);
		return !tagOptional.isPresent() ? null : new TagView(tagOptional.get());
	}

	@Override
	public Tag getTagByName(String name) {
		Optional<TagEntity> tagOptional = tagRepository.findByNameIgnoreCase(name);
		return !tagOptional.isPresent() ? null : new TagView(tagOptional.get());
	}

	@Override
	public Tag addTag(Tag tag) {
		TagEntity tagEntity = new TagEntity(tag);
		Tag savedTag = this.tagRepository.save(tagEntity);
		return new TagView(savedTag);
	}

//	@Override
//	public Tag updateTag(TagEntity tagEntity) {
//	TagEntity tagEntity = new TagEntity(tag);
//	Tag updatedTag this.tagRepository.save(tagEntity);
//  return this.tagRepository.save(updatedTag);
//	}

	@Override
	public int deleteTag(Tag tag) {
		return this.tagRepository.deleteTagById(tag.getId());
	}
}
