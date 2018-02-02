package mywiki.mou.mywiki.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mou on 2/1/2018.
 */

public class Page {
    @SerializedName("pageid")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;
    @SerializedName("terms")
    private Terms terms;

    public Terms getTerms() {
        return terms;
    }

    public void setTerms(Terms terms) {
        this.terms = terms;
    }

    public long getId() {
        return id;

    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}