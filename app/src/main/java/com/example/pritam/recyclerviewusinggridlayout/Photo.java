package com.example.pritam.recyclerviewusinggridlayout;

public class Photo {

    private Number id;
    private Number albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Number albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
