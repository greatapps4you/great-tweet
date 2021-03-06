/*
 * GreatApps4you LLC Copyright (c) 2021
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.application.persistence;

import org.springframework.stereotype.Service;
import us.greatapps4you.greattweet.entities.User;
import us.greatapps4you.greattweet.features.Following;

import java.util.ArrayList;

@Service
public class FollowingServiceWithJPA extends Following {
    private final UsersRepository usersRepository;

    public FollowingServiceWithJPA(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User followUser(User me, User followed) {
        followed = usersRepository.findByUniqueName(followed.getUniqueName());
        if(followed == null) {
            return null;
        }
        me = usersRepository.findByUniqueName(me.getUniqueName());
        if(me == null) {
            return null;
        }
        if(me.getFollowing() == null) {
            me.setFollowing(new ArrayList<>());
        }
        if(!me.getFollowing().contains(followed)) {
            me.getFollowing().add(followed);
            usersRepository.save(me);
        }
        return followed;
    }
}
