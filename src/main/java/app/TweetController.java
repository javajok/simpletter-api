package app;

import java.util.List;
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
    public List<TweetView> getTimeline() {
        return service.getTimeline().stream().map(TweetView::fromTweet)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void post(@RequestParam String userId, @RequestParam String text) {
        service.post(userId, text);
    }
}
