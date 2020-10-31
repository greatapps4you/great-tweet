package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.User;

public class Posting {

    public Posting message(String message) {
        System.out.println(message);
        return this;
    }

    public Response withUser(User user) {
        System.out.println(user.getUserName()
                + " ( " + user.getNickName() + " )");
        return Response.OK;
    }
}
