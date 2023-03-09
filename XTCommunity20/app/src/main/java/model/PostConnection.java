package model;

public class PostConnection {
    private String userId;
    private String postId;
    private UserAction relation;

    public PostConnection(String userId, String postId, UserAction relation) {
        this.userId = userId;
        this.postId = postId;
        this.relation = relation;
    }
}
