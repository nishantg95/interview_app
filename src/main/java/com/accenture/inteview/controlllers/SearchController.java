package com.accenture.inteview.controlllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.inteview.models.QuestionView;
import com.accenture.inteview.services.HibernateSearchService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/search")
public class SearchController {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	private HibernateSearchService searchService;

	@GetMapping("/searchQuestions/tags")
	public ResponseEntity<Set<QuestionView>> searchQuestionsByTags(@RequestBody String[] tagsNames) {
		Set<QuestionView> questionViews = this.searchService.getQuestionsByTagsNames(tagsNames);
		if (questionViews.isEmpty()) {
			return new ResponseEntity<Set<QuestionView>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<QuestionView>>(questionViews, HttpStatus.OK);
	}

	@GetMapping("/searchQuestions/keyword")
	public ResponseEntity<Set<QuestionView>> searchQuestionsBykeyword(@RequestParam String keyword) {
		Set<QuestionView> questionViews = this.searchService.searchTitleAndBodyByKeyword(keyword);
		if (questionViews.isEmpty()) {
			return new ResponseEntity<Set<QuestionView>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<QuestionView>>(questionViews, HttpStatus.OK);
	}
}
