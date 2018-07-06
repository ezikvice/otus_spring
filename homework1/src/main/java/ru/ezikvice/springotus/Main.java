package ru.ezikvice.springotus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.ezikvice.springotus.domain.Answer;
import ru.ezikvice.springotus.domain.ExaminationQuestion;
import ru.ezikvice.springotus.domain.Question;
import ru.ezikvice.springotus.service.QAService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Configuration
@ComponentScan
public class Main {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        System.out.println("TEST!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QAService service = context.getBean(QAService.class);

        Map<Integer, Question> questionMap = service.loadQuestions("questions.csv");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Здравствуйте! Представьтесь, пожалуйста:");
        String userName = scanner.next();

        System.out.printf("%s, предлагаем Вам пройти тест.%n", userName);

        List<ExaminationQuestion> userAnswers = new ArrayList<>();
        for (Question question : questionMap.values()) {
            Answer userAnswer = service.askQuestion(question);
            userAnswers.add(new ExaminationQuestion(question.getId(), userAnswer.getId(), userAnswer.isCorrect()));
        }

        System.out.println("----");
        System.out.printf("Отлично, %s ! А вот и Ваши результаты:%n", userName);
        for (ExaminationQuestion userAnswer : userAnswers) {
            System.out.printf("Вопрос %d: %s%n", userAnswer.getQuestionId(), userAnswer.isCorrect() ?
                    "${answer.right}" :
                    "${answer.wrong}");
        }

    }
}
