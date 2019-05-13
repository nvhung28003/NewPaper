package com.example.yuroko.newpaper.view.fragment;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.adapter.MenuHomeAdapter;
import com.example.yuroko.newpaper.entity.MenuHomePaper;
import com.example.yuroko.newpaper.utils.ConnectionUtils;
import com.example.yuroko.newpaper.view.activity.ReadnewpaperActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TitleFragment extends Fragment implements MenuHomeAdapter.OnitemClickListener {
    private View rootview;
    private LinearLayout linearLayout;
    ArrayList<MenuHomePaper> menuHomePapers = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuHomeAdapter menuHomeAdapter;
    private static String id_category;
    // private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.titlefragment, container, false);
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = rootview.findViewById(R.id.rcvpaper);
        //   progressBar  = rootview.findViewById(R.id.progress);
       swipeRefreshLayout = rootview.findViewById(R.id.swipetitle);
                        RecyclerView.LayoutManager layoutManager = new
                        LinearLayoutManager(rootview.getContext()
                );
                recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                menuHomePapers.clear();
                Bundle bundle = getArguments();
                id_category = bundle.getString("ID");


                if (id_category.isEmpty()) {
                    //  progressBar.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(true);
                    ConnectionUtils.getHome(null, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);

                            try {


                                JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");
                                for (int i = 23; i < jsonArray.length(); i++) {
                                    MenuHomePaper menuHomePaper = new MenuHomePaper(jsonArray.getJSONObject(i));
                                    menuHomePapers.add(menuHomePaper);
                                    ;
                                }
                                menuHomeAdapter = new MenuHomeAdapter(rootview.getContext(), menuHomePapers);
                                recyclerView.setAdapter(menuHomeAdapter);
                                menuHomeAdapter.setOnitemClickListener(TitleFragment.this);
                                //    progressBar.setVisibility(View.INVISIBLE);
                                swipeRefreshLayout.setRefreshing(false);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    //    progressBar.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(true);
                    ConnectionUtils.get(id_category, null, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            try {
                                JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    MenuHomePaper menuHomePaper = new MenuHomePaper(jsonArray.getJSONObject(i));
                                    menuHomePapers.add(menuHomePaper);

                                }
                                menuHomeAdapter = new MenuHomeAdapter(rootview.getContext(), menuHomePapers);
                                recyclerView.setAdapter(menuHomeAdapter);
                                menuHomeAdapter.setOnitemClickListener(TitleFragment.this);
                                //    progressBar.setVisibility(View.INVISIBLE);
                                swipeRefreshLayout.setRefreshing(false);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
            ;
        });
        if (!menuHomePapers.isEmpty()) {
            swipeRefreshLayout.setRefreshing(true);
            menuHomeAdapter = new MenuHomeAdapter(rootview.getContext(), menuHomePapers);
            recyclerView.setAdapter(menuHomeAdapter);
            menuHomeAdapter.setOnitemClickListener(TitleFragment.this);
            //  progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        Bundle bundle = getArguments();
        id_category = bundle.getString("ID");
        if (id_category == "") {
            //  progressBar.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(true);
            ConnectionUtils.getHome(null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);

                    try {
                        JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");
                        for (int i = 23; i < jsonArray.length(); i++) {
                            MenuHomePaper menuHomePaper = new MenuHomePaper(jsonArray.getJSONObject(i));
                            menuHomePapers.add(menuHomePaper);
                            ;
                        }
                        menuHomeAdapter = new MenuHomeAdapter(rootview.getContext(), menuHomePapers);
                        recyclerView.setAdapter(menuHomeAdapter);
                        menuHomeAdapter.setOnitemClickListener(TitleFragment.this);
                        //    progressBar.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout.setRefreshing(false);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            //    progressBar.setVisibility(View.VISIBLE);
           swipeRefreshLayout.setRefreshing(true);
            ConnectionUtils.get(id_category, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            MenuHomePaper menuHomePaper = new MenuHomePaper(jsonArray.getJSONObject(i));
                            menuHomePapers.add(menuHomePaper);

                        }
                        menuHomeAdapter = new MenuHomeAdapter(rootview.getContext(), menuHomePapers);
                        recyclerView.setAdapter(menuHomeAdapter);
                        menuHomeAdapter.setOnitemClickListener(TitleFragment.this);
                        //    progressBar.setVisibility(View.INVISIBLE);
                      swipeRefreshLayout.setRefreshing(false);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    }

    private void Loadmore(String url) {

        if (id_category == "") {
            //    progressBar.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(true);
            ConnectionUtils.getHome_last_publish_time(url, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");
                        for (int i = 1; i < jsonArray.length(); i++) {
                            MenuHomePaper menuHomePaper = new MenuHomePaper(jsonArray.getJSONObject(i));
                            menuHomePapers.add(menuHomePaper);

                        }
                        //   menuHomeAdapter = new MenuHomeAdapter(rootview.getContext(), menuHomePapers);
//                        recyclerView.setAdapter(menuHomeAdapter);
                        menuHomeAdapter.notifyDataSetChanged();
//                        menuHomeAdapter.setOnitemClickListener(TitleFragment.this);
                        //   progressBar.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout.setRefreshing(false);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            // progressBar.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(true);
            ConnectionUtils.get_last_publish_time(id_category, url, null, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");
                        for (int i = 1; i < jsonArray.length(); i++) {
                            MenuHomePaper menuHomePaper = new MenuHomePaper(jsonArray.getJSONObject(i));
                            menuHomePapers.add(menuHomePaper);

                        }
                        menuHomeAdapter.notifyDataSetChanged();
                        //  progressBar.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout.setRefreshing(false);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void OnitemClicked(MenuHomePaper menuHomePaper) {
        ReadnewpaperActivity.menuHomePapers = menuHomePapers;
        Intent intent = new Intent(TitleFragment.super.getContext(), ReadnewpaperActivity.class);
        intent.putExtra("LOCATION", menuHomePapers.indexOf(menuHomePaper));
        intent.putExtra("LINK_WEB",menuHomePaper.getLink_web());
        startActivity(intent);
        Log.d("TTT", menuHomePaper.getId());
    }

    @Override
    public void OnitemMoreClicked(MenuHomePaper menuHomePaper) {

        Loadmore(menuHomePaper.getPublished_at());
    }
    //final Detail detail = details.get(i);


}
