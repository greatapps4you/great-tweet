/*
 * GreatApps4you LLC Copyright (c) 2020
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.greatapps4you.greattweet.application.persistence.TweetsRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class TweetsController {

    private TweetsRepository tweetsRepository;

    public TweetsController(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }

    @GetMapping("/tweets/{id}")
    public EntityModel findById(@PathVariable Integer id) {
        return EntityModel.of(tweetsRepository.findById(id),
                linkTo(methodOn(TweetsController.class).findById(id)).withSelfRel());
    }

}
