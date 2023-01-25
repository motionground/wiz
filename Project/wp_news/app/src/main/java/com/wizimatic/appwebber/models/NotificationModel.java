package com.wizimatic.appwebber.models;

/**
 * Created by Ashiq on 7/27/16.
 */
public class NotificationModel {

    private final int id;
    private final String title;
    private final String message;
    private final boolean isUnread;
    private final String postId;

    public NotificationModel(int id, String title, String message, boolean isUnread, String url) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.isUnread = isUnread;
        this.postId = url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }


    public String getPostId() {
        return postId;
    }

    public boolean isUnread() {
        return isUnread;
    }
}
