package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.Message;
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
            public List<Message> getMessages(User user) {
                List<Message> messages = new ArrayList<>(4);
                LocalDateTime timePosted = LocalDateTime.of(2020, 10, 31, 15, 0, 0);
                messages.add(new Message("I am starting to like this", timePosted.plusMinutes(45)));
                messages.add(new Message("This is my second Tweet", timePosted.plusMinutes(35)));
                messages.add(new Message("Hey this is my first Great Tweet!", timePosted));
                messages.add(new Message("I would love to start following people", timePosted.plusMinutes(60)));

                Collections.sort(messages, Comparator.comparing(Message::getPublicationTime).reversed());

                return messages;
            }
        };
    }

    @Test
    void givenUserThenReturnMessage() {
        User jose = new User("Jose Esteves", "josethedeveloper");
        List<Message> actual = wall.getMessages(jose);
        LocalDateTime timePosted = LocalDateTime.of(2020, 10, 31, 15, 0, 0);
        Message expected = new Message("Hey this is my first Great Tweet!", timePosted);
        Assertions.assertEquals(expected, actual.toArray()[3]);
    }

    @Test
    void givenUserThenReturnMessagesInReverseChronologicalOrder() {
        User jose = new User("Jose Esteves", "josethedeveloper");
        List<Message> actual = wall.getMessages(jose);
        LocalDateTime timePosted = LocalDateTime.of(2020, 10, 31, 15, 0, 0);

        Message expectedFirst = new Message("I would love to start following people", timePosted.plusMinutes(60));
        Message expectedSecond = new Message("I am starting to like this", timePosted.plusMinutes(45));
        Message expectedThird = new Message("This is my second Tweet", timePosted.plusMinutes(35));
        Message expectedFourth = new Message("Hey this is my first Great Tweet!", timePosted);


        Assertions.assertEquals(expectedFirst, actual.toArray()[0]);
        Assertions.assertEquals(expectedSecond, actual.toArray()[1]);
        Assertions.assertEquals(expectedThird, actual.toArray()[2]);
        Assertions.assertEquals(expectedFourth, actual.toArray()[3]);
    }

}