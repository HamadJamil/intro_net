package Model;

import java.util.ArrayList;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Integer> friendsID;
    private ArrayList<Integer> likesIDs;

    public User() {
        likesIDs = new ArrayList<>();
        friendsID = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getLikesIDs() {
        return likesIDs;
    }

    public void setLikesIDs(ArrayList<Integer> likesIDs) {
        this.likesIDs = likesIDs;
    }

    public boolean isLiked(Post post) {
        return likesIDs.contains(post.getPostID());
    }

    public void like(Post p){
        likesIDs.add(p.getPostID());
    }

    public void setFriends(ArrayList<User> friends) {
        for (User u : friends) {
            friendsID.add(u.getId());
        }
    }

    public void setFriendsIDs(ArrayList<Integer> friendsIds) {
        this.friendsID = friendsIds;
    }

    public boolean isFriend(User user) {
        return friendsID.contains(user.getId());
    }

    public ArrayList<Integer> getFriendsID() {
        return friendsID;
    }

    public void addFriend(User f){
        friendsID.add(f.getId());
    }
    public void removeFriend(User f){
        friendsID.remove((Integer)f.getId());
    }

    public void dislike(Post post) {
        likesIDs.remove((Integer)post.getPostID());
        likesIDs.remove((Integer)post.getPostID());
    }
}
