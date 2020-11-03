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
            public Tweet postMessage(String user, String message) {
                return new Tweet("Hey this is my first Great Tweet!", postingTime);
            }
        };
    }

    @Test
    void givenUserAndMessageThenReturnOk() {
        String givenUser = "josethedeveloper";
        String givenMessage = "Hey this is my first Great Tweet!";

        Tweet expected = new Tweet(givenMessage, postingTime);

        Tweet actual = posting
                .tweet(givenMessage)
                .withUser(givenUser);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }


}