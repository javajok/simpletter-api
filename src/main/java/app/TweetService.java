package app;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TweetService {

    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Tweet> getTimeline() {
        return tweetRepository.findAll();
    }

    @Transactional
    public void post(String userId, String text) {
        Account user = accountRepository.find(userId);
        Tweet tweet = new Tweet();
        tweet.id = UUID.randomUUID();
        tweet.text = text;
        tweet.timestamp = LocalDateTime.now();
        tweet.user = user;
        tweetRepository.create(tweet);
    }

    @Transactional
    public void delete(String userId, UUID id) {
        Tweet tweet = tweetRepository.find(id);

        //TODO ユーザーの確認

        tweetRepository.remove(tweet);
    }
}
