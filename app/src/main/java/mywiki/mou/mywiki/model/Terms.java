package mywiki.mou.mywiki.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mou on 2/1/2018.
 */

public class Terms {
    @SerializedName("description")
    List<String> description;

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}
