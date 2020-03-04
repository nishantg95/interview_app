package com.accenture.inteview.controlllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.inteview.entities.TagEntity;
import com.accenture.inteview.services.TagService;

//TODO: Error handling
@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = "api/tags")
public class TagController {

	@Autowired
	private TagService tagService;

	@RequestMapping(name = "/createTag", method = RequestMethod.POST)
	public ResponseEntity<?> createTag(@RequestBody TagEntity tagEntity) {
		// TODO: check if tag exists, since the names should be unique
		TagEntity createdTag = this.tagService.addTag(tagEntity);
		return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/deleteTag", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteItem(@RequestBody TagEntity tagEntity) {
		this.tagService.delete(tagEntity);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateTag", method = RequestMethod.PUT)
	public ResponseEntity<?> updateItem(@RequestBody TagEntity tagEntity) {
		TagEntity updatedTag = this.tagService.updateTag(tagEntity);
		return new ResponseEntity<>(updatedTag, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllTags", method = RequestMethod.GET)
	public ResponseEntity<List<TagEntity>> listAllItems() {
		List<TagEntity> tags = this.tagService.getAllTags();
		return new ResponseEntity<>(tags, HttpStatus.OK);
	}

}
