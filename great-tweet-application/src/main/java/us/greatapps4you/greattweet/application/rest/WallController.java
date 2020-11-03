package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.greatapps4you.greattweet.application.persistence.UsersRepository;
import us.greatapps4you.greattweet.entities.Tweet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class WallController {

    private final UsersRepository repository;

    public WallController(UsersRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/wall/{uniqueName}")
    CollectionModel<EntityModel<Tweet>> findByUserUniqueName(@PathVariable String uniqueName) {
        List<EntityModel<Tweet>> tweets = repository.findByUniqueName(uniqueName).getTweets()
                .stream()
                .map(tweet -> EntityModel.of(tweet,
                        linkTo(methodOn(TweetsController.class).findById(tweet.getId())).withSelfRel()))
                .collect(Collectors.toList());

        //Reverse the List chronologically
        Collections.sort(tweets,
                Comparator.comparing((EntityModel e) -> ((Tweet)e.getContent()).getPublicationTime()).reversed());

        return CollectionModel.of(tweets, linkTo(methodOn(WallController.class).findByUserUniqueName(uniqueName))
                .withSelfRel());
    }

}
