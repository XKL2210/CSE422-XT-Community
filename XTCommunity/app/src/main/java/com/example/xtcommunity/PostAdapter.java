package com.example.xtcommunity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {
    private LayoutInflater inflater;
    private Context context;
    private TextView tvwQItemTitle, tvwQItemUser, tvwQItemUpvotes, tvwQItemDownvotes;
    private ImageButton btnQItemUpvotes, btnQItemDownvotes;

    public PostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);

        String questionTitle = post.getTitle();
        String questionUser = post.getOwner().getName();
        int questionUpvotes = 5;
        int questionDownvotes = 5;

        // Check if an existing view is being reused, otherwise inflate the view.
        if (convertView == null) {
            convertView = inflater.from(context).inflate(R.layout.question_item, parent, false);
        }

        tvwQItemTitle = (TextView) convertView.findViewById(R.id.tvwQItemTitle);
        tvwQItemUser = (TextView) convertView.findViewById(R.id.tvwQItemNumAnswers);
        tvwQItemDownvotes = (TextView) convertView.findViewById(R.id.tvwQItemDownvote);
        tvwQItemUpvotes = (TextView) convertView.findViewById(R.id.tvwQItemUpvote);

        tvwQItemTitle.setText(questionTitle);
        tvwQItemUser.setText(questionUser);
        tvwQItemUpvotes.setText("12");
        tvwQItemDownvotes.setText("0");

        return convertView;
    }
}
