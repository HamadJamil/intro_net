package Model;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Post {
    private int postID;
    private User user;
    private String content;
    private LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy");

    public Post() {

    }

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
        this.localDateTime = LocalDateTime.now();
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTimeToString() {
        return dateTimeFormatter.format(localDateTime);
    }

    public void setDateTimeFromString(String dateTime) {
        this.localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    public String getDateToString() {
        return dateTimeFormatter1.format(localDateTime);
    }
}
