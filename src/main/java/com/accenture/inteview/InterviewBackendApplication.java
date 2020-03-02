package com.accenture.inteview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accenture.inteview.services.QuestionService;
import com.accenture.inteview.services.TagService;

@SpringBootApplication
public class InterviewBackendApplication implements CommandLineRunner {

	@Autowired
	TagService tagService;

	@Autowired
	QuestionService questionService;

	public static void main(String[] args) {
		SpringApplication.run(InterviewBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		QuestionEntity questionEntity = new QuestionEntity("What is an access modifier?",
//				"HINT: Private/public/protected", new HashSet<TagEntity>());
//		Set<QuestionEntity> questions = new HashSet<>();
//		questions.add(questionEntity);
//		TagEntity newTagEntity = new TagEntity("Java", questions);
//		Set<TagEntity> tags = new HashSet<>();
//		tags.add(newTagEntity);
//
//		this.tagService.addTag(newTagEntity);
//
//		questionEntity.setTags(tags);
//
//		this.questionService.addQuestion(questionEntity);

	}

}
