package model;

import java.util.UUID;

public class Post {
    private String id;
    private String owner;
    private String origin;
    private PostType type;
    private String context;
    private int upvotes;
    private int downvotes;

    public Post(String userId, String originalPostId, PostType type, String context, int upvotes, int downvotes) {
        this.id = UUID.randomUUID().toString();
        this.owner = userId;
        this.origin = originalPostId;
        this.type = type;
        this.context = context;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }
    public Post() {
    }

    public boolean setConnection(UserAction action, User user) {
        boolean successToken = false;
        if(!user.getUsername().equalsIgnoreCase(owner)) {
            PostConnection connection = new PostConnection(user.getId(), id, action);
            successToken = true;

            if(action.equals(UserAction.upvoted)) {
                upvotes++;
            }

            if(action.equals(UserAction.downvoted)) {
                downvotes++;
            }
        }

        return  successToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public PostType getType() {
        return type;
    }

    public void setType(PostType type) {
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
