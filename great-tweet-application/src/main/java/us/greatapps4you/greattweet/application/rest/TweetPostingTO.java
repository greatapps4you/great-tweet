/*
 * GreatApps4you LLC Copyright (c) 2020
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.application.rest;

import java.io.Serializable;

public final class TweetPostingTO implements Serializable {

    private final String user;
    private final String tweet;

    public TweetPostingTO(String user, String tweet) {
        this.user = user;
        this.tweet = tweet;
    }

    public String getUser() {
        return user;
    }

    public String getTweet() {
        return tweet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TweetPostingTO tweetPostingTO = (TweetPostingTO) o;

        if (!user.equals(tweetPostingTO.user)) return false;
        return tweet.equals(tweetPostingTO.tweet);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + tweet.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TweetPostingTO{" +
                "user='" + user + '\'' +
                ", tweet='" + tweet + '\'' +
                '}';
    }
}
