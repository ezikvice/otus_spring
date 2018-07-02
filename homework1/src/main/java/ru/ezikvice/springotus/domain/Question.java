package ru.ezikvice.springotus.domain;

import java.util.HashSet;
import java.util.Set;

public class Question {

    public Question() {
    }

    public Question(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Question(int id, String text, Set<Answer> answers) {
        this(id, text);
        this.answers = answers;
    }

    private int id;
    private String text;
    private Set<Answer> answers;

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

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public void setAnswer(Answer answer) {
        if (this.answers == null) {
            this.answers = new HashSet<>();
        }
        this.answers.add(answer);
    }
}
