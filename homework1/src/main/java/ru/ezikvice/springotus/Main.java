package ru.ezikvice.springotus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.ezikvice.springotus.domain.Question;
import ru.ezikvice.springotus.domain.UserExamination;
import ru.ezikvice.springotus.service.LoadService;
import ru.ezikvice.springotus.service.QAService;

import java.util.Map;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QAService service = context.getBean(QAService.class);
        LoadService loadService = context.getBean(LoadService.class);

        Map<Integer, Question> questionMap = loadService.loadQuestions();
        UserExamination exam = service.examine(questionMap);
        service.printResult(exam);
    }
}
