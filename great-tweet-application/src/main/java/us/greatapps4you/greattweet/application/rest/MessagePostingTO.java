package us.greatapps4you.greattweet.application.rest;

import java.io.Serializable;

public final class MessagePostingTO implements Serializable {

    private final String uniqueName;
    private final String message;

    public MessagePostingTO(String uniqueName, String message) {
        this.uniqueName = uniqueName;
        this.message = message;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagePostingTO messagePostingTO = (MessagePostingTO) o;

        if (!uniqueName.equals(messagePostingTO.uniqueName)) return false;
        return message.equals(messagePostingTO.message);
    }

    @Override
    public int hashCode() {
        int result = uniqueName.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PostingTO{" +
                "uniqueName='" + uniqueName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
