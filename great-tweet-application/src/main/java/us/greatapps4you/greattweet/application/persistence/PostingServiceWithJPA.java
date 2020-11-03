package us.greatapps4you.greattweet.application.persistence;

import org.springframework.stereotype.Service;
import us.greatapps4you.greattweet.entities.Message;
import us.greatapps4you.greattweet.entities.User;
import us.greatapps4you.greattweet.features.Posting;

@Service
public class PostingServiceWithJPA extends Posting {

    @Override
    public Message postMessage(User user, String message) {
        return null;
    }
}
