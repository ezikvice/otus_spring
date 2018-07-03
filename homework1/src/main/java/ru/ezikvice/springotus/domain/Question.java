package ru.ezikvice.springotus.domain;

import java.util.HashMap;
import java.util.Map;

public class Question {

    public Question() {
    }

    public Question(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Question(int id, String text, Map<Integer, Answer> answers) {
        this(id, text);
        this.answers = answers;
    }

    private int id;
    private String text;
    private Map<Integer, Answer> answers = new HashMap<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<Integer, Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Integer, Answer> answers) {
        this.answers = answers;
    }

    public void setAnswer(Answer answer) {
        this.answers.put(answer.getId(), answer);
    }

    public Answer getAnswerById(Integer answerId) {
        return answers.get(answerId);
    }
}
