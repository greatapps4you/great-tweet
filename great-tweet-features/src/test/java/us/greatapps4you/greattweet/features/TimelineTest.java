package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.Message;
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
    private Map<String, Message> dataStore;

    @BeforeEach
    void setUp() {

        dataStore = new HashMap<>();
        following = new Following() {
            @Override
            public String followUser(User me, User followed) {
                if (me.getFollowing() == null) {
                    me.setFollowing(new ArrayList<>());
                }
                me.getFollowing().add(followed.getUniqueName());
                return followed.getUniqueName();
            }
        };
        posting = new Posting() {
            @Override
            public Message postMessage(String user, String message) {
                Message postedMessage = new Message(message, LocalDateTime.now(DEFAULT_CLOCK));
                String key = user + "_" + postedMessage.getPublicationTime();
                dataStore.put(key, postedMessage);
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
            public List<Message> getFollowingMessages(User jose) {
                List<Message> followingMessages = new ArrayList<>();

                jose.getFollowing().stream().forEach(u -> dataStore.entrySet()
                        .stream()
                        .forEach(e -> {
                            if (e.getKey().startsWith(u)) {
                                followingMessages.add(e.getValue());
                            }
                        }));
                Collections.sort(followingMessages, Comparator.comparing(Message::getPublicationTime).reversed());
                return followingMessages;
            }
        };

    }

    @Test
    void givenUserThenReturnFollowingMessagesInReverseChronologicalOrder() {
        List<Message> actual = timeline.getFollowingMessages(jose);

        String expectedFirst = "Just came to say hello";
        String expectedSecond = "Hey guys I am doeing good LOL";
        String expectedThird = "Thanks for your support";
        String expectedFourth = "I am happy with my new Movie";

        Assertions.assertEquals(expectedFirst, ((Message)actual.toArray()[0]).getContent());
        Assertions.assertEquals(expectedSecond, ((Message)actual.toArray()[1]).getContent());
        Assertions.assertEquals(expectedThird, ((Message)actual.toArray()[2]).getContent());
        Assertions.assertEquals(expectedFourth, ((Message)actual.toArray()[3]).getContent());

    }

}