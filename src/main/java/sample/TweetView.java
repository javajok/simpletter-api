package sample;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * ひとつのツイートとツイートした人をまとめたクラスです。
 * 
 * @author backpaper0
 *
 */
public class TweetView {
    public UUID id;
    public String text;
    public LocalDateTime timestamp;
    public String userId;

    /**
     * TweetからTweetViewを構築するファクトリーメソッドです。
     * 
     * @param tweet
     * @return
     */
    public static TweetView fromTweet(Tweet tweet) {
        TweetView tweetView = new TweetView();
        tweetView.id = tweet.id;
        tweetView.text = tweet.text;
        tweetView.timestamp = tweet.timestamp;
        tweetView.userId = tweet.user.userId;
        return tweetView;
    }
}
