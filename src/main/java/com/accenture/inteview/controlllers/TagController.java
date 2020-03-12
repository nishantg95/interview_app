package com.accenture.inteview.controlllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.inteview.models.Tag;
import com.accenture.inteview.models.TagView;
import com.accenture.inteview.services.TagService;

//TODO: Error handling
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = "api/tags")
public class TagController {

	@Autowired
	private TagService tagService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/getAllTags")
	public ResponseEntity<List<Tag>> listAllTags() {
		List<Tag> tags = this.tagService.getAllTags();
		if (tags.isEmpty()) {
			return new ResponseEntity<List<Tag>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
	}

	@GetMapping("/getTag/{id}")
	public ResponseEntity<Tag> getTagById(@PathVariable Long id) {

		Tag retrievedTag = tagService.getTagById(id);
		if (retrievedTag == null) {
			return new ResponseEntity<Tag>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Tag>(retrievedTag, HttpStatus.OK);
	}

	@GetMapping("/getTag/name/{name}")
	public ResponseEntity<Tag> getTagByName(@PathVariable String name) {
		Tag retrievedTag = tagService.getTagByName(name);
		if (retrievedTag == null) {
			return new ResponseEntity<Tag>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Tag>(retrievedTag, HttpStatus.OK);
	}

	@PostMapping("/createTag")
	public ResponseEntity<Tag> createTag(@Valid @RequestBody TagView tagview, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Tag>(HttpStatus.NOT_ACCEPTABLE);
		}
		// TODO How do we guarantee that every tag has a unique name.
		// the set does not guarantee that.
		Tag retrievedTag = tagService.getTagByName(tagview.getName());
		if (retrievedTag != null && retrievedTag.getName().equalsIgnoreCase(tagview.getName())) {
			return new ResponseEntity<Tag>(HttpStatus.IM_USED);
		}
		Tag savedTag = this.tagService.addTag(tagview);
		return new ResponseEntity<>(savedTag, HttpStatus.CREATED);
	}

	@PostMapping("/createTags")
	public ResponseEntity<Collection<TagView>> createTags(@Valid @RequestBody Collection<TagView> tags, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Collection<TagView>>(HttpStatus.NOT_ACCEPTABLE);
		}
		Collection<TagView> tagsToCreate = tags.stream().filter(tag -> tag.getId() == null)
				.collect(Collectors.toCollection(ArrayList::new));
		Collection<TagView> createdTags = tagsToCreate.stream().map(tag -> this.tagService.addTagView(tag))
				.collect(Collectors.toCollection(ArrayList::new));
		tags.removeIf(tag -> tag.getId() == null);
		
		tags.addAll(createdTags);
		return new ResponseEntity<Collection<TagView>>(tags, HttpStatus.CREATED);
	}
 

//	@PutMapping("/updateTag")
//	public ResponseEntity<Tag> updateTag(@RequestBody TagView tagview) {
//		Tag updatedTag = this.tagService.updateTag(tagview);
//		return new ResponseEntity<Tag>(updatedTag, HttpStatus.OK);

	@DeleteMapping("/deleteTag")
	public ResponseEntity<HttpStatus> deleteTag(@RequestBody TagView tagView) {
		int deleted = this.tagService.deleteTag(tagView);
		if (deleted != 1) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
