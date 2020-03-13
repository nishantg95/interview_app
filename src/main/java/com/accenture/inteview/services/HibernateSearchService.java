package com.accenture.inteview.services;

import java.util.Set;

import com.accenture.inteview.models.Question;

public interface HibernateSearchService {

	Set<Question> searchTitleAndBodyByKeyword(String text);

	Set<Question> getQuestionsByTagsNames(String[] tagsNames);
}
