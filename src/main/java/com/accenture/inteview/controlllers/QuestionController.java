package com.accenture.inteview.controlllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/questionsApi")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/getQuestion/{id}")
	public ResponseEntity<?> getQuestion(@PathVariable Long id) {
		Object RetrievedQuestion = this.questionService.getQuestion(id);
		return new ResponseEntity<>(RetrievedQuestion, HttpStatus.OK);
	}

	// TODO what if you create a question with a non-existent tag
	@PostMapping("/createQuestion")
	public ResponseEntity<QuestionEntity> createTag(@RequestBody QuestionEntity questionEntity) {
		QuestionEntity createdQuestion = this.questionService.addQuestion(questionEntity);
		return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteQuestion")
	public ResponseEntity<HttpStatus> questionService(@RequestBody QuestionEntity questionEntity) {
		int deleted = this.questionService.deleteQuestion(questionEntity);
		if (deleted != 1) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/updateQuestion")
	public ResponseEntity<QuestionEntity> updateQuestion(@RequestBody QuestionEntity questionEntity) {
		QuestionEntity updatedQuestion = this.questionService.updateQuestion(questionEntity);
		return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
	}

	@GetMapping("/getAllQuestions")
	public ResponseEntity<List<QuestionEntity>> listAllQuestions() {

		List<QuestionEntity> questions = this.questionService.getAllQuestions();
		if (questions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
}
