package us.greatapps4you.greattweet.application.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import us.greatapps4you.greattweet.entities.Tweet;

public interface TweetsRepository extends JpaRepository<Tweet, Integer> {
}
