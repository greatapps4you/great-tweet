/*
 * GreatApps4you LLC Copyright (c) 2020
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.greatapps4you.greattweet.application.persistence.UsersRepository;
import us.greatapps4you.greattweet.entities.Tweet;
import us.greatapps4you.greattweet.entities.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class TimelineController {

    private final UsersRepository repository;

    public TimelineController(UsersRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/timeline/{uniqueName}")
    CollectionModel<EntityModel<TimelineTO>> findByUserUniqueName(@PathVariable String uniqueName) {
        User user = repository.findByUniqueName(uniqueName);
        List<EntityModel<TimelineTO>> timeline = new ArrayList<>();

        for (User u : user.getFollowing()) {
            if (u.getTweets() == null) {
                continue;
            }
            if (u.getTweets().isEmpty()) {
                continue;
            }

            for (Tweet tweet : u.getTweets()) {
                TimelineTO to = new TimelineTO(u.getUniqueName(),
                        u.getName(),
                        tweet.getPublicationTime(),
                        tweet.getContent());

                timeline.add(EntityModel.of(to,
                        linkTo(methodOn(TweetsController.class).findById(tweet.getId())).withSelfRel()));
            }
        }

        //Reverse the List chronologically
        Collections.sort(timeline,
                Comparator.comparing((EntityModel e) -> ((TimelineTO) e.getContent()).getPublicationTime()).reversed());

        return CollectionModel.of(timeline, linkTo(methodOn(WallController.class).findByUserUniqueName(uniqueName))
                .withSelfRel());
    }
}
