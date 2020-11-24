package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import us.greatapps4you.greattweet.application.persistence.PostingServiceWithJPA;
import us.greatapps4you.greattweet.entities.Tweet;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class PostingController {

    private final PostingServiceWithJPA postingService;

    public PostingController(PostingServiceWithJPA postingService) {
        this.postingService = postingService;
    }

    @PostMapping(path = "/posting",
            consumes = "application/json",
            produces = "application/hal+json")
    public ResponseEntity<EntityModel<Tweet>> postTweet(@RequestBody TweetPostingTO tweetPostingTO) {
        Tweet tweet = postingService.tweet(tweetPostingTO.getTweet()).withUser(tweetPostingTO.getUser());
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                        .path("/tweets/{id}")
                        .buildAndExpand(tweet.getId())
                        .toUri();

        return ResponseEntity.created(uri)
                .body(EntityModel.of(tweet,
                linkTo(methodOn(TweetsController.class).findById(tweet.getId())).withSelfRel()));

    }

}
