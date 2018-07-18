package com.example.os_vinhnq.shortstoryapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShortStoryAdapter extends RecyclerView.Adapter<ShortStoryAdapter.ShortStoryViewHolder> {

    private OnItemClickListener onItemClickListener;

    List<ShortStory> shortStories;

    public ShortStoryAdapter(OnItemClickListener onItemClickListener, List<ShortStory> shortStories) {
        this.onItemClickListener = onItemClickListener;
        this.shortStories = shortStories;
    }

    @NonNull
    @Override
    public ShortStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_short_story, parent, false);
        return new ShortStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShortStoryViewHolder holder, int position) {
        holder.bind(shortStories.get(position));
    }

    @Override
    public int getItemCount() {
        return shortStories.size();
    }

    class ShortStoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_story_thumbnail)
        ImageView ivStoryThumbNail;

        @BindView(R.id.tv_story_name)
        TextView tvStoryName;

        @BindView(R.id.tv_story_description)
        TextView tvStoryDescription;


        public ShortStoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ShortStory shortStory) {
            Picasso.get().load(shortStory.getStoryThumbnailLink()).into(ivStoryThumbNail);
            tvStoryName.setText(shortStory.getStoryName());
            tvStoryDescription.setText(shortStory.getStoryDescription());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ShortStory shortStory, int position);
    }
}
