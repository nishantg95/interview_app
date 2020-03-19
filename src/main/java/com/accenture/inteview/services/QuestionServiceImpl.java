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
import com.accenture.inteview.models.QuestionView;
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
	public List<QuestionView> getAllQuestions() {
		List<QuestionView> questionViews = new ArrayList<>();
		List<QuestionEntity> questionEntities = questionRepository.findAll();
		questionEntities.stream().forEach(question -> questionViews.add(new QuestionView(question)));
		return questionViews;
	}

	@Override
	public Set<QuestionView> getQuestionsByTagNameList(String[] tagsNames) {
		Set<QuestionEntity> questionEntities = new HashSet<>();
		Set<QuestionView> questionViews = new HashSet<>();
		for (String tagName : tagsNames) {
			Optional<TagEntity> tagOptional = tagRepository.findByNameIgnoreCase(tagName);
			if (tagOptional.isPresent()) {
				TagEntity tagEntity = tagOptional.get();
				questionEntities.addAll(tagEntity.getQuestions());
			}
		}
		questionEntities.stream().forEach(question -> questionViews.add(new QuestionView(question)));
		return questionViews;
	}

	@Override
	public Set<QuestionView> getQuestionsByTagName(String tagName) {
		Optional<TagEntity> tagOptional = tagRepository.findByNameIgnoreCase(tagName);
		Set<QuestionEntity> questionEntities = new HashSet<>();
		Set<QuestionView> questionViews = new HashSet<>();
		if (tagOptional.isPresent()) {
			questionEntities = tagOptional.get().getQuestions();
			questionEntities.stream().forEach(question -> questionViews.add(new QuestionView(question)));
		}
		return questionViews;
	}

	@Override
	public QuestionView getQuestionById(Long id) {
		Optional<QuestionEntity> questionOptional = questionRepository.findById(id);
		return !questionOptional.isPresent() ? null : new QuestionView(questionOptional.get());
	}

	@Override
	public QuestionView saveQuestion(QuestionView questionView) {
		QuestionEntity questionEntity = new QuestionEntity(questionView);
		QuestionEntity savedQuestion = questionRepository.save(questionEntity);
		return new QuestionView(savedQuestion);
	}

	@Override
	public QuestionView updateQuestion(QuestionView questionView) {
		QuestionEntity questionEntity = new QuestionEntity(questionView);
		QuestionEntity updatedQuestion = questionRepository.save(questionEntity);
		return new QuestionView(updatedQuestion);

	}

	@Override
	public int deleteQuestion(QuestionView questionView) {
		return questionRepository.deleteQuestionById(questionView.getId());
	}
}
