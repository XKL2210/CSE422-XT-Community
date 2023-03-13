package database;

import java.util.ArrayList;
import java.util.List;

import model.Post;
import model.PostConnection;
import model.PostType;
import model.User;

public class Database {
    List<User> users;
    List<Post> posts;
    List<PostConnection> connection;

    public List<Post> getAllPost() {
        Post post01 = new Post("00", "001", PostType.Question
                , "What is life ?", 4, 4);
        Post post02 = new Post("002", "002", PostType.Question
                , "What is time ?", 4, 4);
        posts = new ArrayList<>();
        posts.add(post01);
        posts.add(post02);
        return posts;
    }

    public String loginToDatabase(String username, String password) {
        String successToken = "0000";
        if(username.equals("Admin4444")
                && password.equals("4444")) {
            successToken = "4444";
        }

        return successToken;
    }

    public String registerToDatabase(User user) {
        String successToken;
        successToken = "0000s";
        return successToken;
    }
}
