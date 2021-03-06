/*
 * GreatApps4you LLC Copyright (c) 2021
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.application.persistence;

import org.springframework.stereotype.Service;
import us.greatapps4you.greattweet.application.utils.ClockService;
import us.greatapps4you.greattweet.entities.Tweet;
import us.greatapps4you.greattweet.entities.User;
import us.greatapps4you.greattweet.features.Posting;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class PostingServiceWithJPA extends Posting {

    private final ClockService clockService;
    private final TweetsRepository tweetsRepository;
    private final UsersRepository usersRepository;

    public PostingServiceWithJPA(ClockService clockService, TweetsRepository tweetsRepository, UsersRepository usersRepository) {
        this.clockService = clockService;
        this.tweetsRepository = tweetsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Tweet postTweet(String uniqueName, String tweet) {
        Tweet postedTweet = new Tweet(tweet, LocalDateTime.now(clockService.CENTRAL_EUROPE()));
        postedTweet = tweetsRepository.save(postedTweet);
        User postingUser = usersRepository.findByUniqueName(uniqueName);
        if(postingUser == null){
            postingUser = usersRepository.save(new User(uniqueName,uniqueName));
        }
        if(postingUser.getTweets() == null){
            postingUser.setTweets(new ArrayList<>());
        }
        postingUser.getTweets().add(postedTweet);
        System.out.println(usersRepository.save(postingUser));
        return postedTweet;
    }
}
