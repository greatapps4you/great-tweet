package us.greatapps4you.greattweet.application.rest;

import java.io.Serializable;

public final class TweetPostingTO implements Serializable {

    private final String uniqueName;
    private final String message;

    public TweetPostingTO(String uniqueName, String message) {
        this.uniqueName = uniqueName;
        this.message = message;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TweetPostingTO tweetPostingTO = (TweetPostingTO) o;

        if (!uniqueName.equals(tweetPostingTO.uniqueName)) return false;
        return message.equals(tweetPostingTO.message);
    }

    @Override
    public int hashCode() {
        int result = uniqueName.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PostingTO{" +
                "uniqueName='" + uniqueName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
