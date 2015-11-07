package sample;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * アプリケーションのエントリーポイントとなるクラスです。
 * 
 * このクラスを実行する事で用意されているAPIがすべて使用可能になります。
 * 
 * @author backpaper0
 *
 */
@SpringBootApplication
public class Application {

    @Autowired
    TweetService tweetService;
    @Autowired
    AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * LocalDateTimeをJSONに変換するためカスタマイズしたObjectMapperを返します。
     * 
     * @return
     */
    @Bean
    ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder.json().serializerByType(
                LocalDateTime.class, new LocalDateTimeJsonSerializer()).build();
    }

    /**
     * 動作確認のためのテストデータを作成します。
     * 
     * @return
     */
    @Bean
    ApplicationListener<ContextRefreshedEvent> listener() {
        return new ApplicationListener<ContextRefreshedEvent>() {

            @Override
            public void onApplicationEvent(ContextRefreshedEvent event) {
                accountService.signUp("hogekun");
                tweetService.tweet("hogekun", "こんにちは！");
            }
        };
    }
}
