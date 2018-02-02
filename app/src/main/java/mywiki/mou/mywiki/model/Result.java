package mywiki.mou.mywiki.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mou on 2/1/2018.
 */

public class Result {
    @SerializedName("batchcomplete")
    private String result;
    @SerializedName("query")
    private Query query;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
