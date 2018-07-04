package ru.ezikvice.springotus.service;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import ru.ezikvice.springotus.domain.Answer;
import ru.ezikvice.springotus.domain.Question;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class QAServiceImpl implements QAService {
    private static final int QUESTION_ID = 0;
    private static final int ANSWER_ID = 1;
    private static final int TEXT = 2;
    private static final int CORRECT = 3;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Map<Integer, Question> loadQuestions(String fileName) {
        Map<Integer, Question> questions = new HashMap<>();

        List<String[]> lines;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader f = new FileReader(classLoader.getResource(fileName).getFile());
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
        System.out.println("Ваш ответ:");
        Integer userAnswerId = null;
        Answer userAnswer;
        try {
            userAnswerId = Integer.parseInt(scanner.next());
            userAnswer = question.getAnswerById(userAnswerId);
            if (userAnswer == null) {
                System.out.println("Ответ с таким номером не найден. Попробуйте ввести номер ответа ещё раз");
                userAnswer = askQuestion(question);
            }
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели не числовое значение. Пожалуйста, введите число");
            userAnswer = askQuestion(question);
        }
        return userAnswer;
    }
}
