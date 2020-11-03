package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import us.greatapps4you.greattweet.application.persistence.PostingServiceWithJPA;
import us.greatapps4you.greattweet.application.utils.ClockService;
import us.greatapps4you.greattweet.entities.Message;

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
    @ResponseBody
    public EntityModel serviceStatus() {
        return EntityModel.of(new Message("OK", LocalDateTime.now(clockService.CENTRAL_EUROPE())),
                linkTo(methodOn(PostingController.class).serviceStatus()).withSelfRel());
    }

    @PostMapping("/posting")
    @ResponseBody
    public ResponseEntity<EntityModel<Message>> postMessage(@RequestBody MessagePostingTO messagePostingTO) {
        Message message = postingService.postMessage(messagePostingTO.getUniqueName(), messagePostingTO.getMessage());
        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                        .path("/{id}")
                        .buildAndExpand(message.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(EntityModel.of(message,
                linkTo(methodOn(PostingController.class).serviceStatus()).withSelfRel()));

    }

}
