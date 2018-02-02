package mywiki.mou.mywiki.actvities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mywiki.mou.mywiki.R;
import mywiki.mou.mywiki.adapters.SearchListAdapter;
import mywiki.mou.mywiki.database.TPage;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private TextView visited;
    private RelativeLayout defaultLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        visited = findViewById(R.id.visited_title);
        defaultLayout = findViewById(R.id.default_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateRv();
    }


    private void populateRv() {
        if (TPage.getVisitedPageNum() > 0) {
            visited.setVisibility(View.VISIBLE);
            defaultLayout.setVisibility(View.GONE);
            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
            SearchListAdapter adapter = new SearchListAdapter(MainActivity.this, null, SearchListAdapter.ADAPTER_TYPE_DB);
            rv.setAdapter(adapter);
        } else {
            visited.setVisibility(View.GONE);
            defaultLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                SearchActivity.navigate(MainActivity.this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
