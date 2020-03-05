package com.accenture.inteview.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.inteview.entities.QuestionEntity;
import com.accenture.inteview.entities.TagEntity;
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
	public List<QuestionEntity> getAllQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public Set<QuestionEntity> getQuestionsByTagName(String tagName) {
		Optional<TagEntity> questionOptional = tagRepository.findByNameIgnoreCase(tagName);
		return !questionOptional.isPresent() ? null : questionOptional.get().getQuestions();
	}

	@Override
	public QuestionEntity getQuestionById(Long id) {
		Optional<QuestionEntity> questionOptional = questionRepository.findById(id);
		return !questionOptional.isPresent() ? null : questionOptional.get();
	}

	@Override
	public QuestionEntity addQuestion(QuestionEntity questionEntity) {
		return questionRepository.save(questionEntity);
	}

	@Override
	public QuestionEntity updateQuestion(QuestionEntity questionEntity) {
		return questionRepository.save(questionEntity);
	}

	@Override
	public int deleteQuestion(QuestionEntity questionEntity) {
		return questionRepository.deleteQuestionById(questionEntity.getId());
	}
}
