package ru.ezikvice.springotus.domain;

import java.util.List;

public class UserExamination {
    private String userName;
    private List<ExaminationQuestion> userQuestions;

    public UserExamination() {
    }

    public UserExamination(String userName, List<ExaminationQuestion> userQuestions) {
        this.userName = userName;
        this.userQuestions = userQuestions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ExaminationQuestion> getUserQuestions() {
        return userQuestions;
    }

    public void setUserQuestions(List<ExaminationQuestion> userQuestions) {
        this.userQuestions = userQuestions;
    }
}
