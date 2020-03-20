package com.accenture.inteview.services;

import java.util.Set;

import com.accenture.inteview.models.QuestionView;

public interface HibernateSearchService {

	Set<QuestionView> searchTitleAndBodyByKeyword(String text);

	Set<QuestionView> getQuestionsByTagsNames(String[] tagsNames);
}
