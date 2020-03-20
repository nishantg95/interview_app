package com.accenture.inteview.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.accenture.inteview.models.QuestionView;

@Service
@Transactional
public class HibernateSearchServiceImpl implements HibernateSearchService {

	@Autowired
	private EntityManager entityManager;

	public Set<QuestionView> searchTitleAndBodyByKeyword(String text) {
		Set<QuestionView> questionViews = new HashSet<>();
		Query keywordQuery = getQueryBuilderForQuestion().keyword().onFields("title", "body").matching(text)
				.createQuery();
		@SuppressWarnings("unchecked")
		List<QuestionEntity> questionEntities = getJpaQueryForQuestion(keywordQuery).getResultList();
		questionEntities.stream().forEach(question -> questionViews.add(new QuestionView(question)));
		return questionViews;
	}

	public Set<QuestionView> getQuestionsByTagsNames(String[] tagsNames) {
		Set<QuestionEntity> questionEntities = new HashSet<>();
		Set<QuestionView> questionViews = new HashSet<>();
		for (String tagName : tagsNames) {
			Query tagQuery = getQueryBuilderForTag().keyword().onFields("name").matching(tagName).createQuery();
			TagEntity retrievedTag = (TagEntity) getJpaQueryForTag(tagQuery).getResultList().get(0);
			if (retrievedTag != null) {
				questionEntities.addAll(retrievedTag.getQuestions());
			}
		}
		questionEntities.stream().forEach(question -> questionViews.add(new QuestionView(question)));
		return questionViews;
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