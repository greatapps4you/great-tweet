package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.Message;
import us.greatapps4you.greattweet.entities.User;

import java.util.List;

public abstract class Wall {

    public abstract List<Message> getMessages(User user);
}
