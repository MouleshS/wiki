package mywiki.mou.mywiki.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by Mou on 2/1/2018.
 */

public class Query {
    @SerializedName("pages")
    private List<Page> pages;

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}