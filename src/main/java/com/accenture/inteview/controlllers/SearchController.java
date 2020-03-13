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

import com.accenture.inteview.models.Question;
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
	public ResponseEntity<Set<Question>> searchQuestionsByTags(@RequestBody String[] tagsNames) {

		Set<Question> questions = this.searchService.getQuestionsByTagsNames(tagsNames);
		if (questions == null) {
			return new ResponseEntity<Set<Question>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<Question>>(questions, HttpStatus.OK);
	}

	@GetMapping("/searchQuestions/keyword")
	public ResponseEntity<Set<Question>> searchQuestionsBykeyword(@RequestParam String keyword) {
		System.out.println(" controller searchQuestionsBykeyword. Keyword= " + keyword);

		Set<Question> questions = this.searchService.searchTitleAndBodyByKeyword(keyword);
		if (questions == null) {
			return new ResponseEntity<Set<Question>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<Question>>(questions, HttpStatus.OK);
	}
}
