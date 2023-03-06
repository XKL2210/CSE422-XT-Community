package com.example.xtcommunity;

import android.os.Build;

import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
    ArrayList<Post> posts;
    User user01;
    User user02;
    User user03;
    User user04;

    public Database() {
        posts = new ArrayList<Post>();
    }

    public ArrayList<Post> getAllPost() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            user01 = new User("XKL", "XKL",
                    "Kain", LocalDate.of(2000, 10, 22)
                    , "kl@gmail.com","0944726520");
            user02 = new User("TB", "TB",
                    "Tom", LocalDate.of(1999, 12, 22)
                    , "tm@gmail.com","0944726519");
            user03 = new User("TD", "TD",
                    "Tallin", LocalDate.of(2000, 8, 24)
                    , "tn@gmail.com","0944726518");
            user03 = new User("V", "V",
                    "Vincent", LocalDate.of(1999, 9, 1)
                    , "vc@gmail.com","0944726517");
        }
        Post post01 = new Post("What is Fortran ?", 2, user01, 44, 4);
        Post post02 = new Post("What is Java ?", 0, user02, 44, 4);
        Post post03 = new Post("What is Python ?", 2, user03, 44, 4);
        Post post04 = new Post("What is Dart ?", 1, user04, 44, 4);

        posts.add(post01);
        posts.add(post02);
        posts.add(post03);
        posts.add(post04);
        return posts;
    }

}
