package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.Message;

public abstract class Posting {

    private String message;

    public Posting tweet(String message) {
        this.message = message;
        return this;
    }

    public Message withUser(String user) {
        return this.postMessage(user, this.message);
    }

    public abstract Message postMessage(String user, String message);
}
