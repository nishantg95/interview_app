package com.accenture.inteview.controlllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.inteview.entities.TagEntity;
import com.accenture.inteview.services.TagService;

//TODO: Error handling
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = "api/tags")
public class TagController {

	@Autowired
	private TagService tagService;

	@GetMapping("/getAllTags")
	public ResponseEntity<List<TagEntity>> listAllTags() {
		List<TagEntity> tags = this.tagService.getAllTags();
		if (tags.isEmpty()) {
			return new ResponseEntity<List<TagEntity>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TagEntity>>(tags, HttpStatus.OK);
	}

	@GetMapping("/getTag/{id}")
	public ResponseEntity<TagEntity> getTagById(@PathVariable Long id) {
		TagEntity RetrievedTag = tagService.getTagById(id);
		return new ResponseEntity<TagEntity>(RetrievedTag, HttpStatus.OK);
	}

	@GetMapping("/getTag/name/{name}")
	public ResponseEntity<TagEntity> getTagByName(@PathVariable String name) {
		TagEntity RetrievedTag = tagService.getTagByName(name);
		return new ResponseEntity<>(RetrievedTag, HttpStatus.OK);
	}

	@PostMapping("/createTag")
	public ResponseEntity<TagEntity> createTag(@RequestBody TagEntity tagEntity) {
		// TODO: check if tag exists, since the names should be unique
		TagEntity createdTag = this.tagService.addTag(tagEntity);
		return new ResponseEntity<TagEntity>(createdTag, HttpStatus.CREATED);
	}

//	@PutMapping("/updateTag")
//	public ResponseEntity<TagEntity> updateTag(@RequestBody TagEntity tagEntity) {
//		TagEntity updatedTag = this.tagService.updateTag(tagEntity);
//		return new ResponseEntity<TagEntity>(updatedTag, HttpStatus.OK);
//	}

	@DeleteMapping("/deleteTag")
	public ResponseEntity<HttpStatus> deleteTag(@RequestBody TagEntity tagEntity) {
		int deleted = this.tagService.deleteTag(tagEntity);
		if (deleted != 1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
