package mywiki.mou.mywiki.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mywiki.mou.mywiki.actvities.SearchActivity;
import mywiki.mou.mywiki.apiservice.ApiAdapter;
import mywiki.mou.mywiki.apiservice.ApiService;
import mywiki.mou.mywiki.model.Page;
import mywiki.mou.mywiki.model.Result;
import mywiki.mou.mywiki.viewholders.SearchItemHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mou on 2/1/2018.
 */

public class SearchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int curOffSet;
    private Context context;
    private List<Page> pageList;
    private String searchStr;

    public SearchListAdapter(Context context, String search) {
        this.context = context;
        this.searchStr = search;
        loadData(search, 0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SearchItemHolder.get(context, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Page page = pageList.get(position);
        if (page != null) ((SearchItemHolder) holder).bind(page);
        if (position == pageList.size() - 1 && !(curOffSet == pageList.size())) { //avoids repeated api calls when no more data
            curOffSet = pageList.size();
            loadData(searchStr, pageList.size());
        }

    }

    @Override
    public int getItemCount() {
        return pageList != null ? pageList.size() : 0;
    }

    private void loadData(String search, int offset) {
        ((SearchActivity) context).showProgress(true);
        ApiService apiService = ApiAdapter.getApiService();
        Call<Result> call = apiService.search(search, offset);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                ((SearchActivity) context).showProgress(false);
                Result result = response.body();
                if (result != null && result.getQuery() != null) {
                    updateData(result.getQuery().getPages());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                ((SearchActivity) context).showProgress(false);
                t.getMessage();
            }
        });
    }

    private void updateData(final List<Page> data) {
        if (pageList == null) pageList = new ArrayList<>();
        int insPos = pageList.size();
        pageList.addAll(data);
        notifyItemRangeInserted(insPos, data.size());
    }
}
