package us.greatapps4you.greattweet.features;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.greatapps4you.greattweet.entities.User;

import java.util.ArrayList;

class FollowingTest {

    private Following following;

    @BeforeEach
    void setUp() {
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
    }

    @Test
    void whenFollowUserThenReturnFollowingList() {
        User me = new User("josethedeveloper", "Jose Esteves");
        User emma = new User("emmagic", "Emma Watson");
        User james = new User("jdoe", "James Doe");

        String followedEmma = following.follow(emma).withUser(me);
        String followedJames = following.follow(james).withUser(me);

        Assertions.assertEquals("emmagic", followedEmma);
        Assertions.assertEquals("jdoe", followedJames);

        Assertions.assertNotNull(me.getFollowing());
        Assertions.assertEquals(2, me.getFollowing().size());

    }

}