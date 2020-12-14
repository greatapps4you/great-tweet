/*
 * GreatApps4you LLC Copyright (c) 2020
 * https://greatapps4you.us
 * CSSML NDSMD VRS + NSMV SMQL IVB
 */

package us.greatapps4you.greattweet.entities;

import java.util.List;

public class User {
    private int id;
    private String uniqueName;
    private String name;
    private List<User> following;
    private List<Tweet> tweets;

    public User(String uniqueName, String name) {
        this.uniqueName = uniqueName;
        this.name = name;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!uniqueName.equals(user.uniqueName)) return false;
        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        int result = uniqueName.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "uniqueName='" + uniqueName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
