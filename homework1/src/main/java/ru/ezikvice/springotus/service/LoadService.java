package ru.ezikvice.springotus.service;

import ru.ezikvice.springotus.domain.Question;

import java.util.Map;

public interface LoadService {
    Map<Integer, Question> loadQuestions();
}
