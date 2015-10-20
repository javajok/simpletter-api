package app;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetController {

    @Autowired
    TweetService service;

    @RequestMapping(value = "/timeline", method = RequestMethod.GET)
    public Timeline getTimeline() {
        Timeline timeline = new Timeline();
        timeline.tweets = service.getTimeline().stream()
                .map(TweetView::fromTweet).collect(Collectors.toList());
        return timeline;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public TweetView post(@RequestParam String userId,
            @RequestParam String text) {
        Tweet tweet = service.post(userId, text);
        return TweetView.fromTweet(tweet);
    }
}
