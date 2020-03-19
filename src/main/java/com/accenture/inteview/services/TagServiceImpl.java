
package com.accenture.inteview.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.inteview.entities.TagEntity;
import com.accenture.inteview.models.TagView;
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
	public List<TagView> getAllTags() {
		List<TagView> tagViews = new ArrayList<>();
		this.tagRepository.findAll().stream().map(tagEntity -> tagViews.add(new TagView(tagEntity)));
		return tagViews;
	}

	@Override
	public TagView getTagById(Long id) {
		Optional<TagEntity> tagOptional = tagRepository.findById(id);
		return !tagOptional.isPresent() ? null : new TagView(tagOptional.get());
	}

	@Override
	public TagView getTagByName(String name) {
		Optional<TagEntity> tagOptional = tagRepository.findByNameIgnoreCase(name);
		return !tagOptional.isPresent() ? null : new TagView(tagOptional.get());
	}

	@Override
	public TagView saveTag(TagView tagView) {
		TagEntity tagEntity = new TagEntity(tagView);
		TagEntity savedTag = this.tagRepository.save(tagEntity);
		return new TagView(savedTag);
	}

	@Override
	public List<TagView> saveTagList(List<TagView> tagViews) {
		List<TagView> tagsToCreate = tagViews.stream().filter(tagView -> tagView.getId() == null)
				.collect(Collectors.toList());
		// remove pre-existent tags to create so that they can be replaced
		tagViews.removeAll(tagsToCreate);
		tagsToCreate.stream().map(tagView -> saveTag(tagView)).forEach(tagView -> tagViews.add(tagView));
		return tagViews;
	}

	@Override
	public int deleteTag(TagView tagView) {
		return this.tagRepository.deleteTagById(tagView.getId());
	}
}
