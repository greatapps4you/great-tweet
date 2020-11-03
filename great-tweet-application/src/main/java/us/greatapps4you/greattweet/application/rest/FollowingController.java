package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.greatapps4you.greattweet.application.persistence.FollowingServiceWithJPA;
import us.greatapps4you.greattweet.entities.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class FollowingController {

    private final FollowingServiceWithJPA followingService;

    public FollowingController(FollowingServiceWithJPA followingService) {
        this.followingService = followingService;
    }

    @PostMapping(path = "/following/{followed}",
            consumes = "application/json",
            produces = "application/hal+json")
    public ResponseEntity<EntityModel<User>> followUser(@RequestBody User me, @PathVariable String followed) {
        User followedUser = followingService.followUser(me, new User(followed, followed));
        if (followedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(EntityModel.of(followedUser,
                    linkTo(methodOn(UsersController.class)
                            .findByUniqueName(followedUser.getUniqueName())).withSelfRel()));
        }
    }

}
