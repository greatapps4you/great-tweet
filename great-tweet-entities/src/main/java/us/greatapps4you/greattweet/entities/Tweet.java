package us.greatapps4you.greattweet.entities;

import java.time.LocalDateTime;

public class Tweet {
    private int id;
    private String content;
    private LocalDateTime publicationTime;

    public Tweet(String content, LocalDateTime publicationTime) {
        this.content = content;
        this.publicationTime = publicationTime;
    }

    public Tweet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(LocalDateTime publicationTime) {
        this.publicationTime = publicationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (!content.equals(tweet.content)) return false;
        return publicationTime.equals(tweet.publicationTime);
    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + publicationTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "content='" + content + '\'' +
                ", publicationTime=" + publicationTime +
                '}';
    }
}
