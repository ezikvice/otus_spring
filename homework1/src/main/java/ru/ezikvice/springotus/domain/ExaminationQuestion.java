package ru.ezikvice.springotus.domain;

public class ExaminationQuestion {
    private int questionId;
    private int answerId;
    private boolean correct;

    public ExaminationQuestion(int questionId, int answerId, boolean correct) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.correct = correct;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
