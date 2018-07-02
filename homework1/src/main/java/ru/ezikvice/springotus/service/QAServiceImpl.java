package ru.ezikvice.springotus.service;

import au.com.bytecode.opencsv.CSVReader;
import ru.ezikvice.springotus.domain.Answer;
import ru.ezikvice.springotus.domain.Question;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QAServiceImpl implements QAService {
    private static final int QUESTION_ID = 0;
    private static final int ANSWER_ID = 1;
    private static final int TEXT = 2;
    private static final int CORRECT = 3;

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
                        questions.get(questionId).setAnswer(new Answer(questionId, answerId, txt, correct));
                    }
                }

                System.out.println("ok");

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return questions;
    }
}
