package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.Message;
import us.greatapps4you.greattweet.entities.User;

import java.time.LocalDateTime;

class PostingTest {

    private Posting posting;
    private LocalDateTime postingTime = LocalDateTime.of(2020, 11, 03, 9, 9, 0);

    @BeforeEach
    void setUp() {
        posting = new Posting() {
            @Override
            public Message postMessage(String user, String message) {
                return new Message("Hey this is my first Great Tweet!", postingTime);
            }
        };
    }

    @Test
    void givenUserAndMessageThenReturnOk() {
        String givenUser = "josethedeveloper";
        String givenMessage = "Hey this is my first Great Tweet!";

        Message expected = new Message(givenMessage, postingTime);

        Message actual = posting
                .tweet(givenMessage)
                .withUser(givenUser);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected, actual);
    }


}