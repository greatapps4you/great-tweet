/*
 * GreatApps4you LLC Copyright (c) 2021
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.features;

import us.greatapps4you.greattweet.entities.Tweet;
import us.greatapps4you.greattweet.entities.User;

import java.util.List;

public abstract class Timeline {

    public abstract List<Tweet> getFollowingTweets(User jose);
}
