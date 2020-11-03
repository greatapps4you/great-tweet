package us.greatapps4you.greattweet.application.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
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
        String user = "josethedeveloper";
        this.mockMvc.perform(get("/api/wall/" + user))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void givenUser_ThenReturnTweets() throws Exception {
        String user = "josethedeveloper";
        this.mockMvc.perform(get("/api/wall/" + user))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.tweetList").exists())
                .andExpect(jsonPath("$._embedded.tweetList").isArray());
    }

}