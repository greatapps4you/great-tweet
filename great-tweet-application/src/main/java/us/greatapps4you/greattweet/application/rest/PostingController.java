package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import us.greatapps4you.greattweet.entities.Message;

import java.net.URI;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class PostingController {

    @GetMapping("/posting")
    @ResponseBody
    public EntityModel serviceStatus() {
        return EntityModel.of(new Message("OK", LocalDateTime.now()),
                linkTo(methodOn(PostingController.class).serviceStatus()).withSelfRel());
    }

    @PostMapping("/posting")
    @ResponseBody
    public ResponseEntity<EntityModel<Message>> postMessage(@RequestBody PostingTO postingTO) {

        //TODO: Clock, JPA, Creat User
        Message message = new Message(postingTO.getMessage(), LocalDateTime.now());

        final URI uri =
                MvcUriComponentsBuilder.fromController(getClass())
                        .path("/{id}")
                        .buildAndExpand(message.getId())
                        .toUri();

        return ResponseEntity.created(uri).body(EntityModel.of(message,
                linkTo(methodOn(PostingController.class).serviceStatus()).withSelfRel()));

    }

}
