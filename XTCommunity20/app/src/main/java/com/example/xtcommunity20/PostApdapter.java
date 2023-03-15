package com.example.xtcommunity20;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import model.Post;

public class PostApdapter extends ArrayAdapter<Post> {
    private Context context;
    private List<Post> posts;

    public PostApdapter(Context context, List<Post> posts) {
        super(context, R.layout.item_post, posts);
        this.context = context;
        this.posts = posts;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = convertView;
        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_post, parent, false);
        }

        Post post = posts.get(position);

        String QuestionContext = "Question: " + post.getContext();
        String QuestionUser = "User: " + post.getOwner();
        String QuestionUpvotes = post.getUpvotes() + " upvote(s)";
        String QuestionDownvotes = post.getDownvotes() + " downvote(s)";

        TextView tvwQuestionContext = (TextView) rowView.findViewById(R.id.tvwQuestionItemTitle);
        TextView tvwQuestionUser = (TextView) rowView.findViewById(R.id.tvwQuestionItemUser);
        TextView tvwQuestionUpvote = (TextView) rowView.findViewById(R.id.tvwQuestionItemUpvote);
        TextView tvwQuestionDownvote = (TextView) rowView.findViewById(R.id.tvwQuestionItemDownvote);
        RelativeLayout rltQuestionContainer02 = (RelativeLayout) rowView.findViewById(R.id.rltItemQuestionContainer02);

        tvwQuestionContext.setText(QuestionContext);
        tvwQuestionUser.setText(QuestionUser);
        tvwQuestionUpvote.setText(QuestionUpvotes);
        tvwQuestionDownvote.setText(QuestionDownvotes);

        rltQuestionContainer02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SinglePostActivity.class);
                intent.putExtra("postId", post.getOrigin());
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
