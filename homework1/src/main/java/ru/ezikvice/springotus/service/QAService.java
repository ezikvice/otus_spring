package ru.ezikvice.springotus.service;

import ru.ezikvice.springotus.domain.Answer;
import ru.ezikvice.springotus.domain.Question;
import ru.ezikvice.springotus.domain.UserExamination;

import java.util.Map;

public interface QAService {
    Answer askQuestion(Question question);

    UserExamination examine(Map<Integer, Question> questionMap);

    void printResult(UserExamination exam);
}
