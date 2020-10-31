package us.greatapps4you.greattweet.entities;

import java.util.List;

public class User {
    private int id;
    private String userName;
    private String nickName;
    private List<User> following;
    private List<Message> messages;

    public User(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userName.equals(user.userName)) return false;
        return nickName.equals(user.nickName);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + nickName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
