/*
 * GreatApps4you LLC Copyright (c) 2021
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.application.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import us.greatapps4you.greattweet.entities.Tweet;

public interface TweetsRepository extends JpaRepository<Tweet, Integer> {
}
