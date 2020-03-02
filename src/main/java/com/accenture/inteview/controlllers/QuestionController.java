package com.accenture.inteview.controlllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.inteview.entities.QuestionEntity;
import com.accenture.inteview.services.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/question")
	public QuestionEntity getQuestion(@RequestParam("id") Long id) {

		return questionService.getQuestion(id);
	}

	@PostMapping("/postQuestion")
	public QuestionEntity addQuestion(@RequestBody QuestionEntity questionEntity) {
		return questionService.addQuestion(questionEntity);
	}

}
