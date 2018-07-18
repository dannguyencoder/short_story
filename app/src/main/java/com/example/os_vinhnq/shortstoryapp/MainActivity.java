package com.example.os_vinhnq.shortstoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ShortStoryAdapter.OnItemClickListener {

    @BindView(R.id.rv_short_story)
    RecyclerView rvShortStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ShortStoryDatabaseHelper shortStoryDatabaseHelper = ShortStoryDatabaseHelper.getInstance(this);
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_1_name),getString(R.string.story_1_description),
                getString(R.string.story_1_content),getString(R.string.story_1_thumbnail_link)));
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_2_name),getString(R.string.story_2_description),
                getString(R.string.story_2_content),getString(R.string.story_2_thumbnail_link)));
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_3_name),getString(R.string.story_3_description),
                getString(R.string.story_3_content),getString(R.string.story_3_thumbnail_link)));
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_4_name),getString(R.string.story_4_description),
                getString(R.string.story_4_content),getString(R.string.story_4_thumbnail_link)));
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_5_name),getString(R.string.story_5_description),
                getString(R.string.story_5_content),getString(R.string.story_5_thumbnail_link)));
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_6_name),getString(R.string.story_6_description),
                getString(R.string.story_6_content),getString(R.string.story_6_thumbnail_link)));
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_7_name),getString(R.string.story_7_description),
                getString(R.string.story_7_content),getString(R.string.story_7_thumbnail_link)));
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_8_name),getString(R.string.story_8_description),
                getString(R.string.story_8_content),getString(R.string.story_8_thumbnail_link)));
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_9_name),getString(R.string.story_9_description),
                getString(R.string.story_9_content),getString(R.string.story_9_thumbnail_link)));
        shortStoryDatabaseHelper.addStory(new ShortStory(getString(R.string.story_10_name),getString(R.string.story_10_description),
                getString(R.string.story_10_content),getString(R.string.story_10_thumbnail_link)));

        List<ShortStory> shortStories = shortStoryDatabaseHelper.getAllStory();
        for (ShortStory shortStory : shortStories) {
            Log.d("Short_story_app", String.format("Story name is %s, story description is %s, story content is %s, and story thumbnail link is %s",
                    shortStory.getStoryName(), shortStory.getStoryDescription(), shortStory.getStoryContent(), shortStory.getStoryThumbnailLink()));
        }

        ShortStoryAdapter shortStoryAdapter = new ShortStoryAdapter(this, shortStories);

        rvShortStory.setLayoutManager(new LinearLayoutManager(this));
        rvShortStory.setAdapter(shortStoryAdapter);
    }

    @Override
    public void onItemClick(ShortStory shortStory, int position) {

    }
}
