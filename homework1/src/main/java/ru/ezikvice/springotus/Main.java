package ru.ezikvice.springotus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ru.ezikvice.springotus.dao.ConfigDao;
import ru.ezikvice.springotus.domain.Answer;
import ru.ezikvice.springotus.domain.ExaminationQuestion;
import ru.ezikvice.springotus.domain.Question;
import ru.ezikvice.springotus.service.QAService;

import java.util.*;

@Configuration
@ComponentScan
public class Main {


    public static void main(String[] args) {
        System.out.println("TEST!");
        Locale rus = new Locale("ru", "RU");
        ResourceBundle rb = ResourceBundle.getBundle("l10n", rus);

        ConfigDao configDao = new ConfigDao();
        MessageSource ms = configDao.messageSource();
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
                    rb.getString("answer.right") :
                    rb.getString("answer.wrong"));
        }

    }
}
