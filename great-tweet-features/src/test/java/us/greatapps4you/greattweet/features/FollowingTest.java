/*
 * GreatApps4you LLC Copyright (c) 2021
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

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
            public User followUser(User me, User followed) {
                if (me.getFollowing() == null) {
                    me.setFollowing(new ArrayList<>());
                }
                me.getFollowing().add(followed);
                return followed;
            }
        };
    }

    @Test
    void whenFollowUserThenReturnFollowingList() {
        User me = new User("josethedeveloper", "Jose Esteves");
        User emma = new User("emmagic", "Emma Watson");
        User james = new User("jdoe", "James Doe");

        User followedEmma = following.follow(emma).withUser(me);
        User followedJames = following.follow(james).withUser(me);

        Assertions.assertEquals(emma, followedEmma);
        Assertions.assertEquals(james, followedJames);

        Assertions.assertNotNull(me.getFollowing());
        Assertions.assertEquals(2, me.getFollowing().size());

    }

}