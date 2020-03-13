package com.accenture.inteview.configuration;

import javax.persistence.EntityManagerFactory;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

public class HibernateSearchInitializer {

	private FullTextEntityManager fullTextEntityManager;

	public HibernateSearchInitializer(EntityManagerFactory entityManagerFactory) {
		fullTextEntityManager = Search.getFullTextEntityManager(entityManagerFactory.createEntityManager());
	}

	public void triggerIndexing() {
		try {
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
