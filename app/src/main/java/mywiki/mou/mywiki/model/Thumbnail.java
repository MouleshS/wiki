package mywiki.mou.mywiki.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mou on 2/1/2018.
 */

public class Thumbnail {
    @SerializedName("source")
    private String source;
    @SerializedName("title")
    private int width;
    @SerializedName("extract")
    private int height;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
