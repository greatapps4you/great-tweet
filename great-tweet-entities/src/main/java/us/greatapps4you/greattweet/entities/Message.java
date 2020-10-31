package us.greatapps4you.greattweet.entities;

import java.time.LocalDateTime;

public class Message {
    private int id;
    private String content;
    private LocalDateTime publicationTime;

    public Message(String content, LocalDateTime publicationTime) {
        this.content = content;
        this.publicationTime = publicationTime;
    }

    public Message() {
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

        Message message = (Message) o;

        if (!content.equals(message.content)) return false;
        return publicationTime.equals(message.publicationTime);
    }

    @Override
    public int hashCode() {
        int result = content.hashCode();
        result = 31 * result + publicationTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", publicationTime=" + publicationTime +
                '}';
    }
}
