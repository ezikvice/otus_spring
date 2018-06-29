package ru.ezikvice.springotus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.ezikvice.springotus.service.QAService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QAService service = context.getBean(QAService.class);
        service.loadQuestions("questions.csv");
        System.out.println("main end");

    }
}
