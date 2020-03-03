package com.accenture.inteview.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.inteview.entities.QuestionEntity;
import com.accenture.inteview.repository.QuestionRepository;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public Object getQuestion(Long id) {

		Optional<?> questionOptional = questionRepository.findById(id);

		if (!questionOptional.isPresent()) {
			return null;
		}
		return questionOptional.get();
	}

	@Override
	public QuestionEntity addQuestion(QuestionEntity questionEntity) {
		return questionRepository.save(questionEntity);
	}

	@Override
	public List<QuestionEntity> getAllQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public int deleteQuestion(QuestionEntity questionEntity) {

		return questionRepository.deleteQuestionById(questionEntity.getId());
	}

	@Override
	public QuestionEntity updateQuestion(QuestionEntity questionEntity) {
		return questionRepository.save(questionEntity);
	}

}
