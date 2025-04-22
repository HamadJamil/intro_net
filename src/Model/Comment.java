package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private int commentID;
    private String content;
    private User user;
    private LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy");

    public Comment() {
    }

    public Comment(String content, User user) {
        this.content = content;
        this.user = user;
        localDateTime = LocalDateTime.now();
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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
