package us.greatapps4you.greattweet.application.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.greatapps4you.greattweet.application.persistence.UsersRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class UsersController {

    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users/{uniqueName}")
    public EntityModel findByUniqueName(@PathVariable String uniqueName) {
        return EntityModel.of(usersRepository.findByUniqueName(uniqueName),
                linkTo(methodOn(UsersController.class).findByUniqueName(uniqueName)).withSelfRel());
    }
}
