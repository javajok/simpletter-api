package app;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json().serializerByType(
                LocalDateTime.class, new LocalDateTimeJsonSerializer()).build();
    }
}
