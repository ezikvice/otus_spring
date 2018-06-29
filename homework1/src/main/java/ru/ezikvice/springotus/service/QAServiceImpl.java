package ru.ezikvice.springotus.service;

import au.com.bytecode.opencsv.CSVReader;
import ru.ezikvice.springotus.domain.Answer;
import ru.ezikvice.springotus.domain.Question;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QAServiceImpl implements QAService {
    private static final int QUESTION_ID = 0;
    private static final int ANSWER_ID = 1;
    private static final int TEXT = 2;
    private static final int CORRECT = 3;

    @Override
    public List<Question> loadQuestions(String fileName) {

        List<Question> questions = new ArrayList<>();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader f = new FileReader(classLoader.getResource("questions.csv").getFile());
            CSVReader reader = new CSVReader(f, ';');
            try {
                List<String[]> lines = reader.readAll();

                Set<Answer> answers = new HashSet<>();
                Question question = new Question();
                for (String[] line : lines) {

                    if (line[ANSWER_ID] == null) {
                        if(question != null) {

                        }
                    } else {
                        question = new Question(Integer.getInteger(line[QUESTION_ID]),
                                line[TEXT],
                                new HashSet<Answer>());
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
