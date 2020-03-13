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

import com.accenture.inteview.models.Question;
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
	public ResponseEntity<List<Question>> listAllQuestions() {
		List<Question> questions = this.questionService.getAllQuestions();
		if (questions.isEmpty()) {
			return new ResponseEntity<List<Question>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
	}

	@GetMapping("/getQuestions/tag/{tagName}")
	public ResponseEntity<Set<Question>> getQuestionsByTagName(@PathVariable String tagName) {
		Set<Question> questions = this.questionService.getQuestionsByTagName(tagName);
		if (questions == null) {
			return new ResponseEntity<Set<Question>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<Question>>(questions, HttpStatus.OK);
	}

	@GetMapping("/getQuestions/tags/{tagsNames}")
	public ResponseEntity<Set<Question>> getQuestionsByTagsNames(@PathVariable String[] tagsNames) {
		Set<Question> questions = this.questionService.getQuestionsByTagsNames(tagsNames);
		if (questions == null) {
			return new ResponseEntity<Set<Question>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Set<Question>>(questions, HttpStatus.OK);
	}

	@GetMapping("/getQuestion/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
		Question retrievedQuestion = this.questionService.getQuestionById(id);
		if (retrievedQuestion == null) {
			return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Question>(retrievedQuestion, HttpStatus.OK);
	}

	@PostMapping("/createQuestion")
	public ResponseEntity<Question> createQuestion(@Valid @RequestBody QuestionView questionView,
			BindingResult result) {
		System.out.println("Inside createQuestion controller question.getTags() = " + questionView.getTags());
		if (result.hasErrors()) {
			return new ResponseEntity<Question>(HttpStatus.NOT_ACCEPTABLE);
		}
		Question savedQuestion = this.questionService.addQuestion(questionView);
		return new ResponseEntity<Question>(savedQuestion, HttpStatus.CREATED);
	}

	@PutMapping("/updateQuestion")
	public ResponseEntity<Question> updateQuestion(@RequestBody QuestionView questionView) {
		Question updatedQuestion = this.questionService.updateQuestion(questionView);
		return new ResponseEntity<Question>(updatedQuestion, HttpStatus.OK);
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
