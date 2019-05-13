package com.example.yuroko.newpaper.view.activity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.adapter.SearchAdapter;
import com.example.yuroko.newpaper.entity.Detail;
import com.example.yuroko.newpaper.entity.MenuHomePaper;
import com.example.yuroko.newpaper.utils.ConnectionUtils;
import com.example.yuroko.newpaper.view.fragment.DetailFragment;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnitemClickListener, View.OnClickListener {
    @BindView(R.id.imvBackSearch)
    ImageView imvBackSearch;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    List<MenuHomePaper> Searchs = new ArrayList<>();
    @BindView(R.id.rcvSearch)
    RecyclerView rcvSearch;
    private SearchAdapter searchAdapter;
    @BindView(R.id.imvdelete)
    ImageView imvDelete;
    @BindView(R.id.swipesearch)
    SwipeRefreshLayout swipesearch;
    private boolean isLoading;
    int visibleThreshold = 5;
    String keyword ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        imvBackSearch.setOnClickListener(this);
        edtSearch.setOnClickListener(this);
        imvDelete.setOnClickListener(this);
        final RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(SearchActivity.this);
        rcvSearch.setLayoutManager(layoutManager);

        swipesearch.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipesearch.setRefreshing(false);
            }
        });

            rcvSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int totalItemCount, lastVisibleItem;
                    totalItemCount = layoutManager.getItemCount();
                    lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

                        if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold )) {
                         //   progressSearch.setVisibility(View.GONE);
                            swipesearch.setRefreshing(false);

                               // Toast.makeText(SearchActivity.this,Searchs.get(totalItemCount-1).getTitle().toString(),Toast.LENGTH_SHORT).show();
                                LoadmoreSearch(Searchs.get(totalItemCount-1).getPublished_at());
                           //     isLoading=true;
                        }
                }

            });

        }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imvBackSearch:
                finish();
                break;
            case R.id.edtSearch:
                edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if(!edtSearch.getText().toString().isEmpty())
                        {
                            keyword = edtSearch.getText().toString();
                            searchNewpaper();
                        }
                        return false;
                    }
                });

                //progressSearch.setVisibility(View.GONE);
                break;
            case R.id.imvdelete:
                edtSearch.setText("");
                break;
            default:
                break;
        }
    }

    public void searchNewpaper() {
      //  progressSearch.setVisibility(View.VISIBLE);
        swipesearch.setRefreshing(true);
        ConnectionUtils.getSeach(keyword, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    Searchs.clear();
                    JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        MenuHomePaper search = new MenuHomePaper(jsonArray.getJSONObject(i));
                        Searchs.add(search);
                    }
                    searchAdapter = new SearchAdapter(SearchActivity.this, Searchs);
                    rcvSearch.setAdapter(searchAdapter);
                    searchAdapter.setOnitemClickListener(SearchActivity.this);
           //         progressSearch.setVisibility(View.GONE);
                    swipesearch.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void LoadmoreSearch(String last_publish_time) {
      //  progressSearch.setVisibility(View.VISIBLE);
        swipesearch.setRefreshing(true);
        isLoading = true;
        ConnectionUtils.getMoreSeach(keyword, last_publish_time, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {

                    JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        MenuHomePaper search = new MenuHomePaper(jsonArray.getJSONObject(i));
                        Searchs.add(search);
                    }
                    searchAdapter.notifyDataSetChanged();
                //              progressSearch.setVisibility(View.GONE);
                    swipesearch.setRefreshing(false);
                    isLoading = false;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void OnitemClicked(MenuHomePaper search) {
     Intent intent = new Intent(SearchActivity.this,OpenNextArticleActivity.class);
        intent.putExtra("ID_SHOW",search.getId());
        intent.putExtra("TITLE_SHOW",search.getTitle());
        intent.putExtra("DESCRIPTION_SHOW",search.getDescription());
        intent.putExtra("PARENT_CATEGORY",search.getParent_category());
        intent.putExtra("SITE_NAME",search.getSite_name());
     startActivity(intent);
    }
}
