package com.accenture.inteview.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.inteview.entities.QuestionEntity;
import com.accenture.inteview.entities.TagEntity;
import com.accenture.inteview.models.Question;
import com.accenture.inteview.models.QuestionView;
import com.accenture.inteview.models.Tag;
import com.accenture.inteview.repository.QuestionRepository;
import com.accenture.inteview.repository.TagRepository;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	TagRepository tagRepository;

	@Override
	public List<Question> getAllQuestions() {
		List<Question> questions = new ArrayList<>();
		List<QuestionEntity> questionEntities = questionRepository.findAll();
		for (Question question : questionEntities) {
			Question questionView = new QuestionView(question);
			questions.add(questionView);
		}
		return questions;
	}

	@Override
	public Set<Question> getQuestionsByTagsNames(String[] tagsNames) {
		Set<Question> questionEntities = new HashSet<>();
		Set<Question> questionViews = new HashSet<>();
		for (String tagName : tagsNames) {
			Optional<TagEntity> tagOptional = tagRepository.findByNameIgnoreCase(tagName);
			if (tagOptional.isPresent()) {
				Tag tag = tagOptional.get();
				questionEntities.addAll(tag.getQuestions());
			}
		}
		for (Question question : questionEntities) {
			Question questionView = new QuestionView(question);
			questionViews.add(questionView);
		}
		return questionViews.isEmpty() ? null : questionViews;
	}

	@Override
	public Set<Question> getQuestionsByTagName(String tagName) {
		Optional<TagEntity> tagOptional = tagRepository.findByNameIgnoreCase(tagName);
		Set<QuestionEntity> questionEntities = new HashSet<>();
		Set<Question> questionViews = new HashSet<>();
		if (tagOptional.isPresent()) {
			questionEntities = tagOptional.get().getQuestions();
			for (Question question : questionEntities) {
				Question questionView = new QuestionView(question);
				questionViews.add(questionView);
			}
		}
		return questionViews.isEmpty() ? null : questionViews;
	}

	@Override
	public Question getQuestionById(Long id) {
		Optional<QuestionEntity> questionOptional = questionRepository.findById(id);
		return !questionOptional.isPresent() ? null : new QuestionView(questionOptional.get());
	}

	@Override
	public Question addQuestion(Question question) {
		System.out.println("Inside addQuestion serviceImpl question.getTags() = " + question.getTags());
		QuestionEntity questionEntity = new QuestionEntity(question);
		Question savedQuestion = questionRepository.save(questionEntity);
		return new QuestionView(savedQuestion);
	}

	@Override
	public Question updateQuestion(Question question) {
		QuestionEntity questionEntity = new QuestionEntity(question);
		Question updatedQuestion = questionRepository.save(questionEntity);
		return new QuestionView(updatedQuestion);
	}

	@Override
	public int deleteQuestion(Question question) {
		return questionRepository.deleteQuestionById(question.getId());
	}
}
