package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import us.greatapps4you.greattweet.application.persistence.PostingServiceWithJPA;
import us.greatapps4you.greattweet.application.utils.ClockService;
import us.greatapps4you.greattweet.entities.Tweet;

import java.net.URI;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class PostingController {

    private final ClockService clockService;
    private final PostingServiceWithJPA postingService;

    public PostingController(ClockService clockService, PostingServiceWithJPA postingService) {
        this.clockService = clockService;
        this.postingService = postingService;
    }

    @GetMapping("/posting")
    public EntityModel serviceStatus() {
        return EntityModel.of(new Tweet("OK", LocalDateTime.now(clockService.CENTRAL_EUROPE())),
                linkTo(methodOn(PostingController.class).serviceStatus()).withSelfRel());
    }

    @PostMapping("/posting")
    public ResponseEntity<EntityModel<Tweet>> postMessage(@RequestBody TweetPostingTO tweetPostingTO) {
        Tweet tweet = postingService.postMessage(tweetPostingTO.getUser(), tweetPostingTO.getTweet());
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                        .path("/messages/{id}")
                        .buildAndExpand(tweet.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(EntityModel.of(tweet,
                linkTo(methodOn(PostingController.class).serviceStatus()).withSelfRel()));

    }

}
