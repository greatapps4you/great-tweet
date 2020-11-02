package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import us.greatapps4you.greattweet.entities.Message;

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

}
