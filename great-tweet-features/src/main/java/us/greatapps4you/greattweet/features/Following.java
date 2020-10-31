package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.User;

public abstract class Following {

    private User followed;

    public Following follow(User user) {
        this.followed = user;
        return this;
    }

    public String withUser(User me) {
        return this.followUser(me, followed);
    }

    public abstract String followUser(User me, User followed);
}
