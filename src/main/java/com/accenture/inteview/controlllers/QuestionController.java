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
@RequestMapping("api/questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/getAllQuestions")
	public ResponseEntity<List<QuestionEntity>> listAllQuestions() {
		List<QuestionEntity> questions = this.questionService.getAllQuestions();
		if (questions.isEmpty()) {
			return new ResponseEntity<List<QuestionEntity>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<QuestionEntity>>(questions, HttpStatus.OK);
	}

	@GetMapping("/getQuestion/{id}")
	public ResponseEntity<QuestionEntity> getQuestionById(@PathVariable Long id) {
		QuestionEntity RetrievedQuestion = questionService.getQuestionById(id);
		return new ResponseEntity<QuestionEntity>(RetrievedQuestion, HttpStatus.OK);
	}

	// TODO what if you create a question with a non-existent tag
	@PostMapping("/createQuestion")
	public ResponseEntity<QuestionEntity> createQuestion(@RequestBody QuestionEntity questionEntity) {
		QuestionEntity createdQuestion = this.questionService.addQuestion(questionEntity);
		return new ResponseEntity<QuestionEntity>(createdQuestion, HttpStatus.CREATED);
//	return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
	}


	@PutMapping("/updateQuestion")
	public ResponseEntity<QuestionEntity> updateQuestion(@RequestBody QuestionEntity questionEntity) {
		QuestionEntity updatedQuestion = this.questionService.updateQuestion(questionEntity);
		return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
	}


	@DeleteMapping("/deleteQuestion")
	public ResponseEntity<HttpStatus> deleteQuestion(@RequestBody QuestionEntity questionEntity) {
		int deleted = questionService.deleteQuestion(questionEntity);
		if (deleted != 1) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
