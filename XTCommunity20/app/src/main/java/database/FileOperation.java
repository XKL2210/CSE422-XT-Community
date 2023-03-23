package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Gender;
import model.Post;
import model.PostType;
import model.User;

public class FileOperation {

    public FileOperation() {
    }

    public ArrayList<Post> getAllData() {
        Post post01 = new Post("00", "None", PostType.Question
                , "Java ?", 4, 4);
        post01.setId("001");
        Post post02 = new Post("002", "None", PostType.Question
                , "Python ?", 0, 0);
        Post post03 = new Post("00", "None", PostType.Question
                , "Dart ?", 0, 0);
        Post post04 = new Post("002", "None", PostType.Question
                , "React ?", 0, 0);
        Post post05 = new Post("002", "None", PostType.Question
                , "NodeJS ?", 0, 0);
        Post comment0101 = new Post("00", "001", PostType.Answer
                , "A programming Language", 9, 0);
        Post comment0102 = new Post("00", "001", PostType.Answer
                , "An object oriented Language", 2, 0);
        Post comment0103 = new Post("00", "001", PostType.Answer
                , "Released by Sun Microsystems in 1995", 2, 0);
        Post comment0104 = new Post("00", "001", PostType.Answer
                , "It is designed to be platform-independent", 2, 0);
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post01);
        posts.add(post02);
        posts.add(post03);
        posts.add(post04);
        posts.add(post05);
        posts.add(comment0101);
        posts.add(comment0102);
        posts.add(comment0103);
        posts.add(comment0104);

        return posts;
    }

    public void saveUsersToFile(ArrayList<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Users.txt"))) {
            for (User user : users) {
                writer.write(user.getId()
                        + "," + user.getUsername()
                        + "," + user.getPassword()
                        + "," + user.getFullName()
                        + "," + user.getGender().toString()
                        + "," + user.getDateOfBirth()
                        + "," + user.getEmail()
                        + "," + user.getPhone());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public void savePostsToFile(ArrayList<Post> posts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Posts.txt"))) {
            for (Post post : posts) {
                writer.write(post.getId()
                        + "," + post.getOwner()
                        + "," + post.getOrigin()
                        + "," + post.getType().toString()
                        + "," + post.getContext()
                        + "," + String.valueOf(post.getUpvotes())
                        + "," + String.valueOf(post.getDownvotes()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public ArrayList<Post> loadPostsFromFile() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            FileReader reader = new FileReader("Users.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String owner = values[1];
                String origin = values[2];
                String type = values[3];
                String context = values[4];
                int upvotes = Integer.parseInt(values[5]);
                int downvotes = Integer.parseInt(values[6]);

                PostType tempType = PostType.Question;
                if(type.equals("Answer")) {
                    tempType = PostType.Answer;
                }

                Post post = new Post(owner, origin, tempType, context, upvotes, downvotes);
                post.setId(id);
                posts.add(post);
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public ArrayList<User> loadUsersFromFile() {
        ArrayList<User> users = new ArrayList<>();
        try {
            FileReader reader = new FileReader("Users.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null) {
                String[] values = line.split(",");
                String id = values[0];
                String username = values[1];
                String password = values[2];
                String fullname = values[3];
                String gender = values[4];
                String birthday = values[5];
                String email = values[6];
                String phone = values[7];

                Gender tempGender = Gender.Female;
                if(gender.equals("Male")) {
                    tempGender = Gender.Male;
                }

                User user = new User(username, password, fullname, tempGender, birthday, email, phone);
                user.setId(id);
                users.add(user);
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
