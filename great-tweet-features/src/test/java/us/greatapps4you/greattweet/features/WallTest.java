package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.Tweet;
import us.greatapps4you.greattweet.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class WallTest {

    private Wall wall;

    @BeforeEach
    void setUp() {
        wall = new Wall() {
            @Override
            public List<Tweet> getPostedTweets(User user) {
                List<Tweet> tweets = new ArrayList<>(4);
                LocalDateTime timePosted = LocalDateTime.of(2020, 10, 31, 15, 0, 0);
                tweets.add(new Tweet("I am starting to like this", timePosted.plusMinutes(45)));
                tweets.add(new Tweet("This is my second Tweet", timePosted.plusMinutes(35)));
                tweets.add(new Tweet("Hey this is my first Great Tweet!", timePosted));
                tweets.add(new Tweet("I would love to start following people", timePosted.plusMinutes(60)));

                Collections.sort(tweets, Comparator.comparing(Tweet::getPublicationTime).reversed());

                return tweets;
            }
        };
    }

    @Test
    void givenUserThenReturnTweet() {
        User jose = new User("josethedeveloper", "Jose Esteves");
        List<Tweet> actual = wall.getPostedTweets(jose);
        LocalDateTime timePosted = LocalDateTime.of(2020, 10, 31, 15, 0, 0);
        Tweet expected = new Tweet("Hey this is my first Great Tweet!", timePosted);
        Assertions.assertEquals(expected, actual.toArray()[3]);
    }

    @Test
    void givenUser_ThenReturnTweetsInReverseChronologicalOrder() {
        User jose = new User("josethedeveloper", "Jose Esteves");
        List<Tweet> actual = wall.getPostedTweets(jose);
        LocalDateTime timePosted = LocalDateTime.of(2020, 10, 31, 15, 0, 0);

        Tweet expectedFirst = new Tweet("I would love to start following people", timePosted.plusMinutes(60));
        Tweet expectedSecond = new Tweet("I am starting to like this", timePosted.plusMinutes(45));
        Tweet expectedThird = new Tweet("This is my second Tweet", timePosted.plusMinutes(35));
        Tweet expectedFourth = new Tweet("Hey this is my first Great Tweet!", timePosted);


        Assertions.assertEquals(expectedFirst, actual.toArray()[0]);
        Assertions.assertEquals(expectedSecond, actual.toArray()[1]);
        Assertions.assertEquals(expectedThird, actual.toArray()[2]);
        Assertions.assertEquals(expectedFourth, actual.toArray()[3]);
    }

}