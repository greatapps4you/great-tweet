package us.greatapps4you.greattweet.application.rest;

import us.greatapps4you.greattweet.entities.User;

import java.io.Serializable;

public final class PostingTO implements Serializable {

    private final User user;
    private final String message;

    public PostingTO(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }
}
