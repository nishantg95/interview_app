package com.accenture.inteview.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.inteview.entities.QuestionEntity;
import com.accenture.inteview.entities.TagEntity;
import com.accenture.inteview.models.Question;
import com.accenture.inteview.models.QuestionView;
import com.accenture.inteview.models.Tag;

@Service
@Transactional
public class HibernateSearchServiceImpl implements HibernateSearchService {

	@Autowired
	private EntityManager entityManager;

	public Set<Question> searchTitleAndBodyByKeyword(String text) {
		System.out.println(" HibernateSearchServiceImpl searchQuestionsBykeyword. text= " + text);
		Set<Question> questionEntities = new HashSet<>();
		Set<Question> questionViews = new HashSet<>();
		Query keywordQuery = getQueryBuilderForQuestion().keyword().onFields("title", "body").matching(text)
				.createQuery();
		@SuppressWarnings("unchecked")
		List<QuestionEntity> results = getJpaQueryForQuestion(keywordQuery).getResultList();
		System.out.println(" HibernateSearchServiceImpl searchQuestionsBykeyword. results= " + results);
		results.forEach(System.out::println);
		System.out.println("result" + Arrays.toString(results.toArray()));

		questionEntities = results.stream().collect(Collectors.toSet());
		for (Question question : questionEntities) {
			Question questionView = new QuestionView(question);
			questionViews.add(questionView);
		}
		return questionViews.isEmpty() ? null : questionViews;
	}

	public Set<Question> getQuestionsByTagsNames(String[] tagsNames) {
		System.out.println(" HibernateSearchServiceImpl getQuestionsByTagsNames. tagsNames= " + tagsNames);
		Set<Question> questionEntities = new HashSet<>();
		Set<Question> questionViews = new HashSet<>();
		for (String tagName : tagsNames) {
			Query tagQuery = getQueryBuilderForTag().keyword().onFields("name").matching(tagName).createQuery();
			Tag retrievedTag = (Tag) getJpaQueryForTag(tagQuery).getResultList().get(0);
			if (retrievedTag != null) {
				questionEntities.addAll(retrievedTag.getQuestions());
			}
		}
		System.out
				.println(" HibernateSearchServiceImpl getQuestionsByTagsNames. questionEntities= " + questionEntities);
		for (Question question : questionEntities) {
			Question questionView = new QuestionView(question);
			questionViews.add(questionView);
		}
		return questionViews.isEmpty() ? null : questionViews;
	}

	private FullTextQuery getJpaQueryForQuestion(org.apache.lucene.search.Query luceneQuery) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		return fullTextEntityManager.createFullTextQuery(luceneQuery, QuestionEntity.class);
	}

	private QueryBuilder getQueryBuilderForQuestion() {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		return fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(QuestionEntity.class).get();
	}

	private FullTextQuery getJpaQueryForTag(org.apache.lucene.search.Query luceneQuery) {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		return fullTextEntityManager.createFullTextQuery(luceneQuery, TagEntity.class);
	}

	private QueryBuilder getQueryBuilderForTag() {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		return fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(TagEntity.class).get();
	}
}