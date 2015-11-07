package sample;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ツイートを取得したり投稿するAPIを提供するコントローラーです。
 * 
 * @author backpaper0
 *
 */
@RestController
public class TweetController {

    @Autowired
    TweetService service;

    /**
     * タイムライン(投稿されたツイートの一覧)を取得するAPIです。
     * 
     * @return タイムライン
     */
    @RequestMapping(value = "/timeline", method = RequestMethod.GET)
    public Timeline getTimeline() {
        Timeline timeline = new Timeline();
        timeline.tweets = service.getTimeline().stream()
                .map(TweetView::fromTweet).collect(Collectors.toList());
        return timeline;
    }

    /**
     * ツイートを投稿するAPIです。
     * 
     * @param userId ユーザーID
     * @param text ツイートの内容
     * @return 投稿された内容(サーバー側で振られたツイートの主キーを含む)
     */
    @RequestMapping(value = "/tweet", method = RequestMethod.POST)
    public TweetView tweet(@RequestParam String userId,
            @RequestParam String text) {
        Tweet tweet = service.tweet(userId, text);
        return TweetView.fromTweet(tweet);
    }
}
