package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.User;

class PostingTest {

    private Posting posting;

    @BeforeEach
    void setUp() {
        posting = new Posting() {
            @Override
            public String postMessage(User user, String message) {
                return "Hey this is my first Great Tweet!";
            }
        };
    }

    @Test
    void givenUserAndMessageThenReturnOk() {
        User jose = new User("josethedeveloper", "Jose Esteves");
        String message = "Hey this is my first Great Tweet!";
        String result = posting
                .tweet(message)
                .withUser(jose);
        Assertions.assertEquals(message, result);
    }


}