package database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.Gender;
import model.Post;
import model.PostConnection;
import model.PostType;
import model.User;

public class Database {
    ArrayList<User> users;
    ArrayList<Post> posts;
    FileOperation fileOperation;
    List<PostConnection> connection;


    public boolean createNewPost(Post post) {
        users = new ArrayList<>();
        posts = new ArrayList<>();
        return true;
    }

    public Database() {
        users = new ArrayList<>();
        posts = new ArrayList<>();
        fileOperation = new FileOperation();
        users = fileOperation.loadUsersFromFile();
        posts = fileOperation.getAllData();
    }

    public List<Post> getAllPost(PostType type) {
        ArrayList<Post> questions = new ArrayList<>();
        for(Post post : posts) {
            if(post.getType().equals(PostType.Question)){
                questions.add(post);
            }
        }
        return questions;
    }

    public User getUserById(String id) {
        User currentUser = new User();
        if(id.equalsIgnoreCase("4444")) {
            User admin = new User("Admin4444", "4444"
                    , "XKL", Gender.Male
                    , "22/10/2000", "XKL2210@Gmail.com"
                    , "0944716520");
            currentUser = new User(admin);
        }
        return currentUser;
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

    public List<Post> getPostByUser(String id) {
        List<Post> posts = new ArrayList<>();
        Post post01 = new Post("00", "001", PostType.Question
                , "What is life ?", 4, 4);
        posts.add(post01);
        return posts;
    }

    public List<Post> getPostByContext(String context) {
        List<Post> tempPosts = new ArrayList<>();
        for (Post post : posts) {
            if(post.getContext().contains(context)) {
                tempPosts.add(post);
            }
        }
        return tempPosts;
    }

    public List<Post> getPostByOrigin(String id) {
        id = "001";
        List<Post> answers = new ArrayList<>();
        for (Post post : posts) {
            if(post.getOrigin().equals(id)) {
                answers.add(post);
            }
        }
        return answers;
    }

    public Post getPostById(String id) {
        Post tempPost = new Post();
        for (Post post : posts) {
            if(post.getId().equals(id)) {
                tempPost = post;
            }
        }
        return tempPost;
    }

    public boolean upvotePost(String id) {
        boolean successToken = true;
        return successToken;
    }

    public boolean downvotePost(String id) {
        boolean successToken = true;
        return successToken;
    }
}
