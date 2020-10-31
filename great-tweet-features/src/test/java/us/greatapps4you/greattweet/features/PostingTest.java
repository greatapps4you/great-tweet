package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.User;

class PostingTest {

    private Posting mockPosting;

    @BeforeEach
    void setUp() {
        mockPosting = new Posting() {
            @Override
            public Response postMessage(User user, String message) {
                return Response.OK;
            }
        };
    }

    @Test
    void givenUserAndMessageThenReturnOk() {
        User jose = new User("Jose Esteves", "josethedeveloper");
        Response response = mockPosting
                .tweet("Hey this is my first Great Tweet!")
                .withUser(jose);
        Assertions.assertEquals(Response.OK, response);
    }


}