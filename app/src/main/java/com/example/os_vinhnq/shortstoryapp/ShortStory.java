package com.example.os_vinhnq.shortstoryapp;

public class ShortStory {
    private int id;
    private String storyName;
    private String storyDescription;
    private String storyContent;
    private String storyThumbnailLink;

    public ShortStory(String storyName, String storyDescription, String storyContent, String storyThumbnailLink) {
        this.storyName = storyName;
        this.storyDescription = storyDescription;
        this.storyContent = storyContent;
        this.storyThumbnailLink = storyThumbnailLink;
    }

    public ShortStory() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getStoryDescription() {
        return storyDescription;
    }

    public void setStoryDescription(String storyDescription) {
        this.storyDescription = storyDescription;
    }

    public String getStoryContent() {
        return storyContent;
    }

    public void setStoryContent(String storyContent) {
        this.storyContent = storyContent;
    }

    public String getStoryThumbnailLink() {
        return storyThumbnailLink;
    }

    public void setStoryThumbnailLink(String storyThumbnailLink) {
        this.storyThumbnailLink = storyThumbnailLink;
    }
}
