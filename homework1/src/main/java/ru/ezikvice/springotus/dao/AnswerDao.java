package ru.ezikvice.springotus.dao;

import ru.ezikvice.springotus.domain.Answer;

public interface AnswerDao {
    Answer getByIds(int questionId, int answerId);
}
