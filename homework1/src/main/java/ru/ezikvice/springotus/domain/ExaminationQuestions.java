package ru.ezikvice.springotus.domain;

import java.util.Set;

public class ExaminationQuestions {
    private Set<Question> questions;

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
