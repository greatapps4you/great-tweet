package us.greatapps4you.greattweet.application.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import us.greatapps4you.greattweet.application.persistence.FollowingServiceWithJPA;
import us.greatapps4you.greattweet.entities.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TimelineControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FollowingServiceWithJPA followingService;
    private User me;

    @BeforeEach
    void setUp() throws Exception {
        //People I follow
        User emma = new User("emma", "Emma Watson");
        TweetPostingTO emmaFirstTweet = new TweetPostingTO(emma.getUniqueName(), "I am Emma Watson, but you can call me beauty");
        String emmaFirstTweetJson = objectToJson(emmaFirstTweet);
        // Currently the only way to creat an user is when he posts a tweet
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posting")
                .content(emmaFirstTweetJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());

        User john = new User("john", "John Wayne");
        TweetPostingTO johnFirstTweet = new TweetPostingTO(john.getUniqueName(),
                "I feel like bang today");
        String johnFirstTweetJson = objectToJson(johnFirstTweet);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posting")
                .content(johnFirstTweetJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());

        User steve = new User("steve", "Steve Wonder");
        TweetPostingTO steveFirstTweet = new TweetPostingTO(steve.getUniqueName(),
                "I wonder how...");
        String steveFirstTweetJson = objectToJson(steveFirstTweet);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posting")
                .content(steveFirstTweetJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());

        // Me
        me = new User("josethedeveloper", "Jose Esteves");
        TweetPostingTO joseFirstTweet =
                new TweetPostingTO("josethedeveloper",
                        "I like these great tweets");
        String joseFirstTweetJson = objectToJson(joseFirstTweet);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posting")
                .content(joseFirstTweetJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());

        //Follow those people
        followingService.followUser(me, emma);
        followingService.followUser(me, john);
        followingService.followUser(me, steve);

    }

    @Test
    void givenUser_ThenReturnTimeline() throws Exception {
        this.mockMvc.perform(get("/api/timeline/" + me.getUniqueName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.timelineTOList").exists())
               .andExpect(jsonPath("$._embedded.timelineTOList").isArray())
        ;
    }

    private String objectToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(object);
    }

}