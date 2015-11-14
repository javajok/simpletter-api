package sample;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ツイートに関するロジックをまとめたクラスです。
 * 
 * @author backpaper0
 *
 */
@Service
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    AccountRepository accountRepository;

    /**
     * これまで投稿されたすべてのツイートを返します。
     * 
     * @return ツイートのリスト
     */
    public List<Tweet> getTimeline() {
        return tweetRepository.findAll();
    }

    /**
     * ツイートを構築して保存します。
     * 
     * @param userId ユーザーID
     * @param text ツイートの内容
     * @return 構築されたツイート
     */
    // @Transactionalはこのメソッドを1つのトランザクション内で実行する
    // 事を指定しています。
    // メソッドが正常に終了するとトランザクションはコミットされます。
    // 例外が投げられる事によってメソッドが終了するとトランザクションは
    // ロールバックされます。
    @Transactional
    public Tweet tweet(String userId, String text) {
        Account user = accountRepository.find(userId);
        Tweet tweet = new Tweet();
        tweet.id = UUID.randomUUID();
        tweet.text = text;
        tweet.timestamp = LocalDateTime.now();
        tweet.user = user;
        tweetRepository.create(tweet);
        return tweet;
    }
}
