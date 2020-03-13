package com.accenture.inteview.configuration;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;

public class HibernateSearchConfig {

	@Bean
	public HibernateSearchInitializer luceneIndexServiceBean(EntityManagerFactory EntityManagerFactory) {
		HibernateSearchInitializer luceneIndexServiceBean = new HibernateSearchInitializer(EntityManagerFactory);
		luceneIndexServiceBean.triggerIndexing();
		return luceneIndexServiceBean;
	}
}