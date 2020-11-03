package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.Tweet;

import java.time.LocalDateTime;

class PostingTest {

    private Posting posting;
    private LocalDateTime postingTime = LocalDateTime.of(2020, 11, 03, 9, 9, 0);

    @BeforeEach
    void setUp() {
        posting = new Posting() {
            @Override
            public Tweet postTweet(String user, String tweet) {
                return new Tweet("Hey this is my first Great Tweet!", postingTime);
            }
        };
    }

    @Test
    void givenUserAndTweetThenReturnOk() {
        String givenUser = "josethedeveloper";
        String givenTweet = "Hey this is my first Great Tweet!";

        Tweet expected = new Tweet(givenTweet, postingTime);

        Tweet actual = posting
                .tweet(givenTweet)
                .withUser(givenUser);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }


}