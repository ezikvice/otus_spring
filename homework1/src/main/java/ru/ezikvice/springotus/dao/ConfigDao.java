package ru.ezikvice.springotus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class ConfigDao {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    ResourceBundle resourceBundle() {
        Locale loc = new Locale("ru", "RU");
        return ResourceBundle.getBundle("l10n", loc);
    }
}
