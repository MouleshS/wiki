package mywiki.mou.mywiki.actvities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import mywiki.mou.mywiki.R;


/**
 * Created by Mou on 2/01/2018.
 */

public class WebViewActivity extends AppCompatActivity {
    public static final String WEB_LINK = "web_link";
    private static final int FULL_PROGRESS = 100;
    private WebView webView;
    private TextView loading;
    private String currentLink;

    public static void navigate(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WEB_LINK, url);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String s = getIntent().getStringExtra(WEB_LINK);
        if (s != null && !s.isEmpty()) {
            currentLink = s;
        }
        webView = (WebView) findViewById(R.id.web_view);
        loading = (TextView) findViewById(R.id.loading_text);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebChromeClient(new WebChromeClient() {
            String loadTxt = getResources().getString(R.string.loading);

            public void onProgressChanged(WebView view, int progress) {
                loading.setText(loadTxt + progress);
                if (progress == FULL_PROGRESS) {
                    loading.setVisibility(View.GONE);
                }
            }
        });

        webView.loadUrl(currentLink);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
