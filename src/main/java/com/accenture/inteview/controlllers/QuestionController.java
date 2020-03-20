package com.accenture.inteview.controlllers;

import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.inteview.models.QuestionView;
import com.accenture.inteview.services.QuestionService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("api/questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/getAllQuestions")
	public ResponseEntity<List<QuestionView>> listAllQuestions() {
		List<QuestionView> questionViews = this.questionService.getAllQuestions();
		if (questionViews.isEmpty()) {
			return new ResponseEntity<List<QuestionView>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<QuestionView>>(questionViews, HttpStatus.OK);
	}

	@GetMapping("/getQuestions/tag/{tagName}")
	public ResponseEntity<Set<QuestionView>> getQuestionsByTagName(@PathVariable String tagName) {
		Set<QuestionView> questionViews = this.questionService.getQuestionsByTagName(tagName);
		if (questionViews.isEmpty()) {
			return new ResponseEntity<Set<QuestionView>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<QuestionView>>(questionViews, HttpStatus.OK);
	}

	@GetMapping("/getQuestions/tags/{tagsNames}")
	public ResponseEntity<Set<QuestionView>> getQuestionsByTagNameList(@PathVariable String[] tagsNames) {
		Set<QuestionView> questionViews = this.questionService.getQuestionsByTagNameList(tagsNames);
		if (questionViews.isEmpty()) {
			return new ResponseEntity<Set<QuestionView>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<QuestionView>>(questionViews, HttpStatus.OK);
	}

	@GetMapping("/getQuestion/{id}")
	public ResponseEntity<QuestionView> getQuestionById(@PathVariable Long id) {
		QuestionView retrievedQuestion = this.questionService.getQuestionById(id);
		if (retrievedQuestion == QuestionView.NotFound) {
			return new ResponseEntity<QuestionView>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<QuestionView>(retrievedQuestion, HttpStatus.OK);
	}

	@PostMapping("/createQuestion")
	public ResponseEntity<QuestionView> createQuestion(@Valid @RequestBody QuestionView questionView,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<QuestionView>(HttpStatus.NOT_ACCEPTABLE);
		}
		QuestionView savedQuestion = this.questionService.saveQuestion(questionView);
		return new ResponseEntity<QuestionView>(savedQuestion, HttpStatus.CREATED);
	}

	@PutMapping("/updateQuestion")
	public ResponseEntity<QuestionView> updateQuestion(@RequestBody QuestionView questionView) {
		QuestionView updatedQuestion = this.questionService.updateQuestion(questionView);
		return new ResponseEntity<QuestionView>(updatedQuestion, HttpStatus.OK);
	}

	@DeleteMapping("/deleteQuestion")
	public ResponseEntity<HttpStatus> deleteQuestion(@RequestBody QuestionView questionView) {
		int deleted = this.questionService.deleteQuestion(questionView);
		if (deleted != 1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
