package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.Tweet;

public abstract class Posting {

    private String tweet;

    public Posting tweet(String tweet) {
        this.tweet = tweet;
        return this;
    }

    public Tweet withUser(String user) {
        return this.postTweet(user, this.tweet);
    }

    public abstract Tweet postTweet(String user, String tweet);
}
