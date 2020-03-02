/**
 *
 */
package com.accenture.inteview.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.inteview.entities.TagEntity;
import com.accenture.inteview.repository.TagRepository;

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
	public List<TagEntity> getAllTags() {
		return this.tagRepository.findAll();
	}

	@Override
	public TagEntity addTag(TagEntity tagEntity) {
		return this.tagRepository.save(tagEntity);
	}

	@Override
	public TagEntity updateTag(TagEntity tagEntity) {
		return this.tagRepository.save(tagEntity);
	}

	@Override
	public void delete(TagEntity tagEntity) {
		this.tagRepository.delete(tagEntity);
	}

}
