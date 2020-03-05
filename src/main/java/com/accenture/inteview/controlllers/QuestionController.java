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

import com.accenture.inteview.entities.QuestionEntity;
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
	public ResponseEntity<List<QuestionEntity>> listAllQuestions() {
		List<QuestionEntity> questions = this.questionService.getAllQuestions();
		if (questions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

	@GetMapping("/getQuestions/tag/{tag}")
	public ResponseEntity<Set<QuestionEntity>> getQuestionsByTagName(@PathVariable String tagName) {
		Set<QuestionEntity> questions = this.questionService.getQuestionsByTagName(tagName);
		if (questions == null) {
			return new ResponseEntity<Set<QuestionEntity>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<QuestionEntity>>(questions, HttpStatus.OK);
	}

	@GetMapping("/getQuestions/tags/{tagsNames}")
	public ResponseEntity<Set<QuestionEntity>> getQuestionsByTagsNames(@PathVariable String[] tagsNames) {
		Set<QuestionEntity> questions = this.questionService.getQuestionsByTagsNames(tagsNames);
		if (questions == null) {
			return new ResponseEntity<Set<QuestionEntity>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<QuestionEntity>>(questions, HttpStatus.OK);
	}

	@GetMapping("/getQuestion/{id}")
	public ResponseEntity<QuestionEntity> getQuestionById(@PathVariable Long id) {
		QuestionEntity retrievedQuestion = this.questionService.getQuestionById(id);
		if (retrievedQuestion == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(retrievedQuestion, HttpStatus.OK);
	}

	// TODO what if you create a question with a non-existent tag
	@PostMapping("/createQuestion")
	public ResponseEntity<QuestionEntity> createQuestion(@Valid @RequestBody QuestionEntity questionEntity,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<QuestionEntity>(HttpStatus.NOT_ACCEPTABLE);
		}
		QuestionEntity createdQuestion = this.questionService.addQuestion(questionEntity);
		return new ResponseEntity<QuestionEntity>(createdQuestion, HttpStatus.CREATED);
	}

	@PutMapping("/updateQuestion")
	public ResponseEntity<QuestionEntity> updateQuestion(@RequestBody QuestionEntity questionEntity) {
		QuestionEntity updatedQuestion = this.questionService.updateQuestion(questionEntity);
		return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
	}

	@DeleteMapping("/deleteQuestion")
	public ResponseEntity<HttpStatus> deleteQuestion(@RequestBody QuestionEntity questionEntity) {
		int deleted = this.questionService.deleteQuestion(questionEntity);
		if (deleted != 1) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
