package com.accenture.inteview.controlllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.inteview.entities.QuestionEntity;
import com.accenture.inteview.services.QuestionService;

@RestController
@RequestMapping("/questionsApi")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/getQuestion/{id}")
	public ResponseEntity<QuestionEntity> getQuestion(@PathVariable Long id) {
		QuestionEntity RetrievedQuestion = questionService.getQuestion(id);
		return new ResponseEntity<>(RetrievedQuestion, HttpStatus.OK);
	}

	@PostMapping("/createQuestion")
	public ResponseEntity<?> createTag(@RequestBody QuestionEntity questionEntity) {
		QuestionEntity createdQuestion = this.questionService.addQuestion(questionEntity);
		return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/deleteQuestion")
	public ResponseEntity<?> questionService(@RequestBody QuestionEntity questionEntity) {
		this.questionService.deleteQuestion(questionEntity);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PutMapping(value = "/updateQuestion")
	public ResponseEntity<?> updateQuestion(@RequestBody QuestionEntity questionEntity) {
		QuestionEntity updatedQuestion = this.questionService.updateQuestion(questionEntity);
		return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
	}

	@GetMapping("/getAllQuestions")
	public ResponseEntity<List<QuestionEntity>> listAllQuestions() {
		List<QuestionEntity> questions = this.questionService.getAllQuestions();
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}

}
