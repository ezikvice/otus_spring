package ru.ezikvice.springotus.service;

import ru.ezikvice.springotus.domain.Question;

import java.util.Map;

public interface QAService {
    Map<Integer, Question> loadQuestions(String fileName);
}
