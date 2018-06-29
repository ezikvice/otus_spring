package ru.ezikvice.springotus.domain;

public class Answer {
    private int id;
    private int questionId;
    private String text;
    private boolean correct;

    public Answer() {
    }

    public Answer(int id, int questionId, String text, boolean correct) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
