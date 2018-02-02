package mywiki.mou.mywiki.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mywiki.mou.mywiki.R;
import mywiki.mou.mywiki.actvities.WebViewActivity;
import mywiki.mou.mywiki.database.TPage;
import mywiki.mou.mywiki.model.Page;
import mywiki.mou.mywiki.utils.Util;

/**
 * Created by Mou on 2/1/2018.
 */

public class SearchItemHolder extends RecyclerView.ViewHolder {
    public static final String WIKI_PG_URL = "https://en.wikipedia.org/?curid=";
    private Context ctx;
    private ImageView itemImg;
    private TextView title, desc;
    private Page curPage;

    public SearchItemHolder(Context context, View itemView) {
        super(itemView);
        this.ctx = context;
        itemImg = itemView.findViewById(R.id.search_img);
        title = itemView.findViewById(R.id.title);
        desc = itemView.findViewById(R.id.desc);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curPage != null) {
                    TPage.insert(curPage);
                    WebViewActivity.navigate(ctx, WIKI_PG_URL + String.valueOf(curPage.getId()));
                }
            }
        });
    }

    public static SearchItemHolder get(Context context, ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item_layout, parent, false);
        return new SearchItemHolder(context, v);
    }

    public void bind(Page page) {
        String url = null;
        this.curPage = page;
        title.setText(page.getTitle());
        if (page.getTerms() != null && page.getTerms().getDescription() != null && page.getTerms().getDescription().size() > 0) {
            desc.setText(page.getTerms().getDescription().get(0));
        }
        if (page.getThumbnail() != null) {
            url = page.getThumbnail().getSource();
        }
        Util.displayPic(ctx, itemImg, url, R.drawable.place_holder);
    }

}
