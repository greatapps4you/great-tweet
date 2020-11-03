package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import us.greatapps4you.greattweet.application.persistence.TweetsRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class MessagesController {

    private TweetsRepository tweetsRepository;

    public MessagesController(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }

    @GetMapping("/messages/{id}")
    public EntityModel findById(@PathVariable Integer id) {
        return EntityModel.of(tweetsRepository.findById(id),
                linkTo(methodOn(MessagesController.class).findById(id)).withSelfRel());
    }

}
