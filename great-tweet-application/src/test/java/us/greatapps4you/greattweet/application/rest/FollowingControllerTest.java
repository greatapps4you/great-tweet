/*
 * GreatApps4you LLC Copyright (c) 2020
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.application.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import us.greatapps4you.greattweet.entities.User;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class FollowingControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private User followed;
    private User me;

    @BeforeEach
    void setUp() throws Exception {
        followed = new User("emma", "Emma Watson");
        TweetPostingTO emmaFirstTweet = new TweetPostingTO("emma", "I am Emma Watson, but you can call me beauty");
        String emmaFirstTweetJson = objectToJson(emmaFirstTweet);
        // Currently the only way to creat an user is when he posts a tweet
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posting")
                .content(emmaFirstTweetJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());

        me = new User("josethedeveloper", "Jose Esteves");
        TweetPostingTO joseFirstTweet =
                new TweetPostingTO("josethedeveloper",
                        "I have developed this system and now I follow Emma Watson");
        String joseFirstTweetJson = objectToJson(joseFirstTweet);
        // Currently the only way to creat an user is when he posts a tweet
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posting")
                .content(joseFirstTweetJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void whenFollowUser_ThenReturnFollowed() throws Exception {
        // I go on the payload and the user I followed goes on the path
        String meJson = objectToJson(me);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/following/" + followed.getUniqueName())
                .content(meJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
        .andExpect(status().isOk());
    }

    private String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(object);
    }

}