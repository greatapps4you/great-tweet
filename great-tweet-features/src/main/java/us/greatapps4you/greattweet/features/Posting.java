package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.User;

public abstract class Posting {

    private String message;

    public Posting tweet(String message) {
        this.message = message;
        return this;
    }

    public Response withUser(User user) {
        return this.postMessage(user, this.message);
    }

    public abstract Response postMessage(User user, String message);
}
