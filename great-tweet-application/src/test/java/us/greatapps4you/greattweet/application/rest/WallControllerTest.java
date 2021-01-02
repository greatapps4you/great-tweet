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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class WallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenUser_ThenReturnOk() throws Exception {
        String user = "wallcontroller";
        this.mockMvc.perform(get("/api/wall/" + user))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void givenUser_ThenReturnTweets() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();

        TweetPostingTO tweetPostingTO = new TweetPostingTO("wallcontroller", "WallControllerTest");
        String requestJson = objectWriter.writeValueAsString(tweetPostingTO);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posting")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE));

        String user = "wallcontroller";
        this.mockMvc.perform(get("/api/wall/" + user))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.tweetList").exists())
                .andExpect(jsonPath("$._embedded.tweetList").isArray());
    }

}