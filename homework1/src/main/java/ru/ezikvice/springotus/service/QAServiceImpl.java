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
        Set<Answer> answers = new HashSet<>();

        List<String[]> lines = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            FileReader f = new FileReader(classLoader.getResource(fileName).getFile());
            CSVReader reader = new CSVReader(f, ';');
            try {
                lines = reader.readAll();

                for (String[] line : lines) {

                    if (line[ANSWER_ID] == null || line[ANSWER_ID].equals("")) {
                        Answer a = new Answer(Integer.getInteger(new String(line[QUESTION_ID])),
                                Integer.getInteger(new String(line[ANSWER_ID])),
                                new String(line[TEXT]),
                                line[CORRECT].equals("1") ? true : false);
                        answers.add(a);
                    } else {
                        String idd = new String(line[QUESTION_ID]);
                        Integer id = Integer.parseInt(idd);
                        questions.add(new Question(id, new String(line[TEXT])));
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

        return null;
    }
}
