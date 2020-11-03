package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.Tweet;

public abstract class Posting {

    private String message;

    public Posting tweet(String message) {
        this.message = message;
        return this;
    }

    public Tweet withUser(String user) {
        return this.postMessage(user, this.message);
    }

    public abstract Tweet postMessage(String user, String message);
}
