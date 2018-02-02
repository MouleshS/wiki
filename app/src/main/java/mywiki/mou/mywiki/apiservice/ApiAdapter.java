package mywiki.mou.mywiki.apiservice;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiAdapter {
    public static final String BASE_URL = "https://en.wikipedia.org//w/api.php/";
    private static volatile ApiService apiService;
    private static int cacheSize = 10 * 1024 * 1024; // 10 MB


    private ApiAdapter() {
    }

    public static synchronized ApiService getApiService() {

        if (apiService == null) {
            initApiService();
        }
        return apiService;
    }

    private static void initApiService() {
        //Cache cache = new Cache(getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = restAdapter.create(ApiService.class);
    }
}
