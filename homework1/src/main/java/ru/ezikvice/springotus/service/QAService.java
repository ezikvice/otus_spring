package ru.ezikvice.springotus.service;

import ru.ezikvice.springotus.domain.Question;

import java.util.List;

public interface QAService {
    List<Question> loadQuestions(String fileName);
}
