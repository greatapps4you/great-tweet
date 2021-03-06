/*
 * GreatApps4you LLC Copyright (c) 2021
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenPostTweet_ThenReturnCreated() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();

        TweetPostingTO tweetPostingTO = new TweetPostingTO("josethedeveloper", "Hi Great Tweet!");
        String requestJson = objectWriter.writeValueAsString(tweetPostingTO);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posting")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("content").value(tweetPostingTO.getTweet()))
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(greaterThan(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("publicationTime").exists());

    }


}