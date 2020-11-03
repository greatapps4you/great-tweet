package us.greatapps4you.greattweet.application.persistence;

import org.springframework.stereotype.Service;
import us.greatapps4you.greattweet.application.utils.ClockService;
import us.greatapps4you.greattweet.entities.Message;
import us.greatapps4you.greattweet.features.Posting;

@Service
public class PostingServiceWithJPA extends Posting {

    private final ClockService clockService;

    public PostingServiceWithJPA(ClockService clockService) {
        this.clockService = clockService;
    }

    @Override
    public Message postMessage(String user, String message) {
        return null;
    }
}
