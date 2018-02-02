package mywiki.mou.mywiki.apiservice;

import mywiki.mou.mywiki.model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
/*
    @GET("?format=json&action=query&pilimit=10&prop=extracts&exintro=&explaintext=")
    Call<Result> search(@Query("titles") String search);
*/

    @GET("?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=10")
    Call<Result> search(@Query("gpssearch") String search, @Query("gpsoffset") int offset);
}
