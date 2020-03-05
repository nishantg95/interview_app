/**
 *
 */
package com.accenture.inteview.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.inteview.entities.TagEntity;
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
	@JsonIgnoreProperties(value = "questions")
	public List<TagEntity> getAllTags() {
		return this.tagRepository.findAll();
	}

	@Override
	public TagEntity getTagById(Long id) {
		Optional<TagEntity> tagOptional = tagRepository.findById(id);
		return !tagOptional.isPresent() ? null : tagOptional.get();
	}

	@Override
	public TagEntity getTagByName(String name) {
		Optional<TagEntity> tagOptional = tagRepository.findByNameIgnoreCase(name);
		return !tagOptional.isPresent() ? null : tagOptional.get();
	}

	@Override
	public TagEntity addTag(TagEntity tagEntity) {
		return this.tagRepository.save(tagEntity);
	}

//	@Override
//	public TagEntity updateTag(TagEntity tagEntity) {
//		return this.tagRepository.save(tagEntity);
//	}

	@Override
	public int deleteTag(TagEntity tagEntity) {
		return this.tagRepository.deleteTagById(tagEntity.getId());
	}
}
