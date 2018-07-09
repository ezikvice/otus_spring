package ru.ezikvice.springotus.service;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ezikvice.springotus.domain.Answer;
import ru.ezikvice.springotus.domain.ExaminationQuestion;
import ru.ezikvice.springotus.domain.Question;
import ru.ezikvice.springotus.domain.UserExamination;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class QAServiceImpl implements QAService {
    private static final int QUESTION_ID = 0;
    private static final int ANSWER_ID = 1;
    private static final int TEXT = 2;
    private static final int CORRECT = 3;

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    ResourceBundle rb;

    @Override
    public Map<Integer, Question> loadQuestions() {
        Map<Integer, Question> questions = new HashMap<>();

        List<String[]> lines;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader f = new FileReader(classLoader.getResource(rb.getString("path.to.file.questions")).getFile());
            CSVReader reader = new CSVReader(f, ';');
            try {
                lines = reader.readAll();

                // TODO: to see how to make it simpler
                for (String[] line : lines) {
                    String qIdStr = new String(line[QUESTION_ID]);
                    Integer questionId = Integer.parseInt(qIdStr);

                    String txt = new String(line[TEXT]);

                    Boolean correct = line[CORRECT].equals("1");

                    if (line[ANSWER_ID] == null || line[ANSWER_ID].equals("")) {
                        questions.put(questionId, new Question(questionId, txt));
                    } else {
                        String aIdStr = new String(line[ANSWER_ID]);
                        Integer answerId = Integer.parseInt(aIdStr);
                        questions.get(questionId).setAnswer(new Answer(answerId, questionId, txt, correct));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return questions;
    }

    @Override
    public Answer askQuestion(Question question) {
        System.out.printf("%d. %s%n", question.getId(), question.getText());
        for (Answer answer : question.getAnswers().values()) {
            System.out.printf("%d. %s %n", answer.getId(), answer.getText());
        }
        System.out.printf("%s", rb.getString("student.answer"));
        Integer userAnswerId = null;
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
        System.out.printf("Отлично, %s ! А вот и Ваши результаты:%n", exam.getUserName());
        for (ExaminationQuestion userAnswer : exam.getUserQuestions()) {
            System.out.printf("%s %d: %s%n",
                    rb.getString("question"),
                    userAnswer.getQuestionId(),
                    userAnswer.isCorrect() ? rb.getString("answer.right") : rb.getString("answer.wrong"));
        }
    }
}
