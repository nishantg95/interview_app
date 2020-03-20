package com.accenture.inteview.controlllers;

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
	public ResponseEntity<List<TagView>> listAllTags() {

		List<TagView> tagViews = this.tagService.getAllTags();
		if (tagViews.isEmpty()) {
			return new ResponseEntity<List<TagView>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TagView>>(tagViews, HttpStatus.OK);
	}

	@GetMapping("/getTag/{id}")
	public ResponseEntity<TagView> getTagById(@PathVariable Long id) {

		TagView retrievedTag = tagService.getTagById(id);
		if (retrievedTag == TagView.NotFound) {
			return new ResponseEntity<TagView>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TagView>(retrievedTag, HttpStatus.OK);
	}

	@GetMapping("/getTag/name/{name}")
	public ResponseEntity<TagView> getTagByName(@PathVariable String name) {
		TagView retrievedTag = tagService.getTagByName(name);
		if (retrievedTag == TagView.NotFound) {
			return new ResponseEntity<TagView>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<TagView>(retrievedTag, HttpStatus.OK);
	}

	@PostMapping("/createTag")
	public ResponseEntity<TagView> createTag(@Valid @RequestBody TagView tagView, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<TagView>(HttpStatus.NOT_ACCEPTABLE);
		}
		TagView retrievedTag = tagService.getTagByName(tagView.getName());
		if (retrievedTag.getName().equalsIgnoreCase(tagView.getName())) {
			return new ResponseEntity<TagView>(HttpStatus.IM_USED);
		}
		TagView savedTag = this.tagService.saveTag(tagView);
		return new ResponseEntity<>(savedTag, HttpStatus.CREATED);
	}

	@PostMapping("/createTagList")
	public ResponseEntity<List<TagView>> saveTagList(@Valid @RequestBody List<TagView> tagViews, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<List<TagView>>(HttpStatus.NOT_ACCEPTABLE);
		}
		List<TagView> tagViewsToSave = tagViews.stream().filter(tagView -> tagView.getId() == null)
				.collect(Collectors.toList());
		// remove from the list tags that need to be saved so they be replaced with
		// created ones
		tagViews.removeAll(tagViewsToSave);
		for (TagView tagView : tagViewsToSave) {
			TagView retrievedTag = tagService.getTagByName(tagView.getName());
			if (retrievedTag.getName().equalsIgnoreCase(tagView.getName())) {
				return new ResponseEntity<List<TagView>>(HttpStatus.IM_USED);
			}
		}
		List<TagView> savedTags = tagService.saveTagList(tagViewsToSave);
		tagViews.addAll(savedTags);
		return new ResponseEntity<List<TagView>>(tagViews, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteTag")
	public ResponseEntity<HttpStatus> deleteTag(@RequestBody TagView tagView) {
		int deleted = this.tagService.deleteTag(tagView);
		if (deleted != 1) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
