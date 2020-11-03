package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.Tweet;
import us.greatapps4you.greattweet.entities.User;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

class TimelineTest {

    public static final Clock DEFAULT_CLOCK = Clock.system(ZoneId.of("Europe/Paris"));
    private Timeline timeline;
    private Following following;
    private User jose;
    private User emma;
    private User james;
    private Posting posting;
    private Map<String, Tweet> dataStore;

    @BeforeEach
    void setUp() {

        dataStore = new HashMap<>();
        following = new Following() {
            @Override
            public String followUser(User me, User followed) {
                if (me.getFollowing() == null) {
                    me.setFollowing(new ArrayList<>());
                }
                me.getFollowing().add(followed);
                return followed.getUniqueName();
            }
        };
        posting = new Posting() {
            @Override
            public Tweet postTweet(String user, String tweet) {
                Tweet postedTweet = new Tweet(tweet, LocalDateTime.now(DEFAULT_CLOCK));
                String key = user + "_" + postedTweet.getPublicationTime();
                dataStore.put(key, postedTweet);
                return dataStore.get(key);
            }
        };

        jose = new User("josethedeveloper", "Jose Esteves");
        emma = new User("emmagic", "Emma Watson");
        james = new User("jdoe", "James Doe");

        following.follow(emma).withUser(jose);
        following.follow(james).withUser(jose);

        posting.tweet("I am happy with my new Movie").withUser(emma.getUniqueName());
        posting.tweet("Thanks for your support").withUser(emma.getUniqueName());

        posting.tweet("Hey guys I am doeing good LOL").withUser(james.getUniqueName());
        posting.tweet("Just came to say hello").withUser(james.getUniqueName());

        timeline = new Timeline() {
            @Override
            public List<Tweet> getFollowingTweets(User jose) {
                List<Tweet> followingTweets = new ArrayList<>();

                jose.getFollowing().stream().forEach(u -> dataStore.entrySet()
                        .stream()
                        .forEach(e -> {
                            if (e.getKey().startsWith(u.getUniqueName())) {
                                followingTweets.add(e.getValue());
                            }
                        }));
                Collections.sort(followingTweets, Comparator.comparing(Tweet::getPublicationTime).reversed());
                return followingTweets;
            }
        };

    }

    @Test
    void givenUserThenReturnFollowingTweetsInReverseChronologicalOrder() {
        List<Tweet> actual = timeline.getFollowingTweets(jose);

        String expectedFirst = "Just came to say hello";
        String expectedSecond = "Hey guys I am doeing good LOL";
        String expectedThird = "Thanks for your support";
        String expectedFourth = "I am happy with my new Movie";

        Assertions.assertEquals(expectedFirst, ((Tweet)actual.toArray()[0]).getContent());
        Assertions.assertEquals(expectedSecond, ((Tweet)actual.toArray()[1]).getContent());
        Assertions.assertEquals(expectedThird, ((Tweet)actual.toArray()[2]).getContent());
        Assertions.assertEquals(expectedFourth, ((Tweet)actual.toArray()[3]).getContent());

    }

}