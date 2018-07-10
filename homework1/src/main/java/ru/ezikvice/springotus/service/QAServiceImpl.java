package ru.ezikvice.springotus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ezikvice.springotus.domain.Answer;
import ru.ezikvice.springotus.domain.ExaminationQuestion;
import ru.ezikvice.springotus.domain.Question;
import ru.ezikvice.springotus.domain.UserExamination;

import java.util.*;

@Service
public class QAServiceImpl implements QAService {

    private final Scanner scanner = new Scanner(System.in);

    private final ResourceBundle rb;

    @Autowired
    public QAServiceImpl(ResourceBundle rb) {
        this.rb = rb;
    }

    @Override
    public Answer askQuestion(Question question) {
        System.out.printf("%d. %s%n", question.getId(), question.getText());
        for (Answer answer : question.getAnswers().values()) {
            System.out.printf("%d. %s %n", answer.getId(), answer.getText());
        }
        System.out.printf("%s", rb.getString("student.answer"));
        Integer userAnswerId;
        Answer userAnswer;
        try {
            userAnswerId = Integer.parseInt(scanner.next());
            userAnswer = question.getAnswerById(userAnswerId);
            if (userAnswer == null) {
                System.out.printf("%s", rb.getString("error.answer.not.found"));
                userAnswer = askQuestion(question);
            }
        } catch (NumberFormatException e) {
            System.out.printf("%s", rb.getString("error.not.a.number"));
            userAnswer = askQuestion(question);
        }
        return userAnswer;
    }

    @Override
    public UserExamination examine(Map<Integer, Question> questionMap) {
        System.out.printf("%s", rb.getString("greetings"));
        String userName = scanner.next();

        System.out.printf("%s, %s", userName, rb.getString("test.suggesting"));

        List<ExaminationQuestion> userAnswers = new ArrayList<>();
        for (Question question : questionMap.values()) {
            Answer userAnswer = askQuestion(question);
            userAnswers.add(new ExaminationQuestion(question.getId(), userAnswer.getId(), userAnswer.isCorrect()));
        }

        return new UserExamination(userName, userAnswers);
    }

    @Override
    public void printResult(UserExamination exam) {
        System.out.println("----");
        System.out.printf(rb.getString("test.results"), exam.getUserName());
        for (ExaminationQuestion userAnswer : exam.getUserQuestions()) {
            System.out.printf("%s %d: %s%n",
                    rb.getString("question"),
                    userAnswer.getQuestionId(),
                    userAnswer.isCorrect() ? rb.getString("answer.right") : rb.getString("answer.wrong"));
        }
    }
}
