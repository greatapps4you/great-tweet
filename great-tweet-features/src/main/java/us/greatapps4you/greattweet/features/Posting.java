package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.Message;
import us.greatapps4you.greattweet.entities.User;

public abstract class Posting {

    private String message;

    public Posting tweet(String message) {
        this.message = message;
        return this;
    }

    public Message withUser(User user) {
        return this.postMessage(user, this.message);
    }

    public abstract Message postMessage(User user, String message);
}
