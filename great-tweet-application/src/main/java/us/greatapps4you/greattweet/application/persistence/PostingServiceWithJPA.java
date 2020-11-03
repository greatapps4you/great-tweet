package us.greatapps4you.greattweet.application.persistence;

import org.springframework.stereotype.Service;
import us.greatapps4you.greattweet.application.utils.ClockService;
import us.greatapps4you.greattweet.entities.Tweet;
import us.greatapps4you.greattweet.features.Posting;

import java.time.LocalDateTime;

@Service
public class PostingServiceWithJPA extends Posting {

    private final ClockService clockService;
    private final TweetsRepository tweetsRepository;

    public PostingServiceWithJPA(ClockService clockService, TweetsRepository tweetsRepository) {
        this.clockService = clockService;
        this.tweetsRepository = tweetsRepository;
    }

    @Override
    public Tweet postMessage(String user, String message) {
        //TODO: Find or create user and append the posted Message to him
        Tweet postedTweet = new Tweet(message, LocalDateTime.now(clockService.CENTRAL_EUROPE()));
        return tweetsRepository.save(postedTweet);
    }
}
