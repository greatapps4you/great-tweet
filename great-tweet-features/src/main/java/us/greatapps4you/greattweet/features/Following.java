/*
 * GreatApps4you LLC Copyright (c) 2020
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.User;

public abstract class Following {

    private User followed;

    public Following follow(User user) {
        this.followed = user;
        return this;
    }

    public User withUser(User me) {
        return this.followUser(me, followed);
    }

    public abstract User followUser(User me, User followed);
}
