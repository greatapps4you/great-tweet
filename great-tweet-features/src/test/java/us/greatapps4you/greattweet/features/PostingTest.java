package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.User;

class PostingTest {

    @Test
    void givenUserAndMessagethenReturnOk(){
        Posting mockPosting = new Posting() {
            @Override
            public Response postMessage(User user, String message) {
                return Response.OK;
            }
        };
        User jose = new User("Jose Esteves", "josethedeveloper");
        Response response = mockPosting.message("Hey this is my first Great Tweet!").withUser(jose);
        Assertions.assertEquals(Response.OK, response);
    }


}