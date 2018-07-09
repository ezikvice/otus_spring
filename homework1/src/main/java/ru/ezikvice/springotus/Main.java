package ru.ezikvice.springotus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.ezikvice.springotus.domain.Question;
import ru.ezikvice.springotus.domain.UserExamination;
import ru.ezikvice.springotus.service.QAService;

import java.util.Map;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QAService service = context.getBean(QAService.class);

        Map<Integer, Question> questionMap = service.loadQuestions();
        UserExamination exam = service.examine(questionMap);
        service.printResult(exam);
    }
}
