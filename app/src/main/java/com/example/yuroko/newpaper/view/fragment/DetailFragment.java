package com.example.yuroko.newpaper.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.adapter.DetailAdapter;
import com.example.yuroko.newpaper.entity.Description;
import com.example.yuroko.newpaper.entity.Detail;
import com.example.yuroko.newpaper.entity.MenuHomePaper;
import com.example.yuroko.newpaper.entity.Share;
import com.example.yuroko.newpaper.entity.Time;
import com.example.yuroko.newpaper.entity.Title;
import com.example.yuroko.newpaper.entity.Toprelated;
import com.example.yuroko.newpaper.utils.ConnectionUtils;
import com.example.yuroko.newpaper.view.activity.ImageActivity;
import com.example.yuroko.newpaper.view.activity.OpenNextArticleActivity;
import com.example.yuroko.newpaper.view.activity.VideoActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;

public class DetailFragment extends Fragment implements DetailAdapter.OnitemClickLitsener {
    private View rootview;
    private RecyclerView rcvdetail;
    private DetailAdapter detailAdapter;
    private ArrayList<Object> details = new ArrayList<>();
    private String id_show;
    private String title_show;
    private String descriptin_show;
    private String parent_category;
    private String site_name;
    private String link_web;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.nextscreenfragment, container, false);
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvdetail = rootview.findViewById(R.id.rcvdetail);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(rootview.getContext());
        rcvdetail.setLayoutManager(layoutManager);
        swipeRefreshLayout = rootview.findViewById(R.id.swipedetail);
        if (rcvdetail != null && rcvdetail.getAdapter() != null) {
            return;
        }
        //details.clear();
        Bundle bundle = getArguments();
        id_show = bundle.getString("ID_SHOW");
        title_show = bundle.getString("TITLE_SHOW");
        descriptin_show = bundle.getString("DESCRIPTION_SHOW");
        parent_category = bundle.getString("PARENT_CATEGORY");
        site_name = bundle.getString("SITE_NAME");
        link_web = bundle.getString("LINK_WEB");
        Log.d("FFFFF", id_show);


        swipeRefreshLayout.setRefreshing(true);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.sfprodisplayregular);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Shared_Preferences_Font_size", Context.MODE_PRIVATE);
        final int font_size = sharedPreferences.getInt("font_size", 12);
        Log.d("BBB", String.valueOf(font_size));
        ConnectionUtils.getdetail(id_show, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                details.clear();
                try {
                    Title title = new Title(title_show);
                    details.add(0, title);
                    String time = response.getJSONObject("response").getString("published_at");
                    Time timedate = new Time(time);
                    details.add(1, timedate);

                    Share share = new Share("share");
                    details.add(share);

                    JSONArray jsonrelatedtop = response.getJSONObject("response").getJSONArray("related_top");
                    for (int y = 0; y < jsonrelatedtop.length(); y++) {
                        Toprelated relatedtop = new Toprelated(jsonrelatedtop.getJSONObject(y));
                        details.add(relatedtop);
                    }
                    Description description = new Description(descriptin_show);
                    details.add(description);

                    JSONArray jsonArray = response.getJSONObject("response").getJSONArray("parse_content");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (jsonArray.getJSONObject(i).getString("type").startsWith("text") == true || jsonArray.getJSONObject(i).getString("type").startsWith("image") == true) {
                            String type = jsonArray.getJSONObject(i).getString("type");
                            String content = jsonArray.getJSONObject(i).getJSONObject("data").getString("content");
                            String align = jsonArray.getJSONObject(i).getString("align");

                            Detail detail = new Detail(type, content, align);
                            details.add(detail);
                        } else if (jsonArray.getJSONObject(i).getString("type").startsWith("video") == true) {
                            String type = jsonArray.getJSONObject(i).getString("type");
                            String img = jsonArray.getJSONObject(i).getJSONObject("data").getString("img");
                            String content = jsonArray.getJSONObject(i).getJSONObject("data").getString("content");
                            String align = jsonArray.getJSONObject(i).getJSONObject("data").getString("align");

                            Detail detail = new Detail(type, img, content, align);
                            details.add(detail);
                        }
                    }
                    details.add(share);

                    final String nameCategory = response.getJSONObject("response").getJSONObject("parent_category_data").getString("title");
//                    Typeface tfBold = Typeface.create(ResourcesCompat.getFont(DetailFragment.this, (configMap.get(FONT))), Typeface.ITALIC);
//                    selectedTextView.setTypeface(tfBold);

                    ConnectionUtils.getbottomarticle(id_show, parent_category, site_name, null, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            try {
                                MenuHomePaper nextarticle = new MenuHomePaper(response.getJSONObject("response").getJSONObject("next_article"));
                                details.add(nextarticle);
                                JSONArray jsonArray1 = response.getJSONObject("response").getJSONArray("related_bottom");

                                for (int j = 0; j < jsonArray1.length(); j++) {
                                    MenuHomePaper relatedbottom = new MenuHomePaper(jsonArray1.getJSONObject(j));
                                    details.add(relatedbottom);
                                }
                                detailAdapter = new DetailAdapter(rootview.getContext(), details, nameCategory, font_size);
                                if (rcvdetail != null)
                                    rcvdetail.setAdapter(detailAdapter);
                                detailAdapter.setOnitemClickListener(DetailFragment.this);
                                swipeRefreshLayout.setRefreshing(false);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Bundle bundle = getArguments();
                id_show = bundle.getString("ID_SHOW");
                title_show = bundle.getString("TITLE_SHOW");
                descriptin_show = bundle.getString("DESCRIPTION_SHOW");
                parent_category = bundle.getString("PARENT_CATEGORY");
                site_name = bundle.getString("SITE_NAME");
                link_web = bundle.getString("LINK_WEB");

                Log.d("FFFFF", id_show);


                swipeRefreshLayout.setRefreshing(true);
                Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.sfprodisplayregular);

                SharedPreferences sharedPreferences = getContext().getSharedPreferences("Shared_Preferences_Font_size", Context.MODE_PRIVATE);
                final int font_size = sharedPreferences.getInt("font_size", 12);
                Log.d("BBB", String.valueOf(font_size));
                ConnectionUtils.getdetail(id_show, null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        details.clear();
                        try {
                            Title title = new Title(title_show);
                            details.add(0, title);
                            String time = response.getJSONObject("response").getString("published_at");
                            Time timedate = new Time(time);
                            details.add(1, timedate);

                            Share share = new Share("share");
                            details.add(share);

                            JSONArray jsonrelatedtop = response.getJSONObject("response").getJSONArray("related_top");
                            for (int y = 0; y < jsonrelatedtop.length(); y++) {
                                Toprelated relatedtop = new Toprelated(jsonrelatedtop.getJSONObject(y));
                                details.add(relatedtop);
                            }
                            Description description = new Description(descriptin_show);
                            details.add(description);

                            JSONArray jsonArray = response.getJSONObject("response").getJSONArray("parse_content");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                if (jsonArray.getJSONObject(i).getString("type").startsWith("text") == true || jsonArray.getJSONObject(i).getString("type").startsWith("image") == true) {
                                    String type = jsonArray.getJSONObject(i).getString("type");
                                    String content = jsonArray.getJSONObject(i).getJSONObject("data").getString("content");
                                    String align = jsonArray.getJSONObject(i).getString("align");

                                    Detail detail = new Detail(type, content, align);
                                    details.add(detail);
                                } else if (jsonArray.getJSONObject(i).getString("type").startsWith("video") == true) {
                                    String type = jsonArray.getJSONObject(i).getString("type");
                                    String img = jsonArray.getJSONObject(i).getJSONObject("data").getString("img");
                                    String content = jsonArray.getJSONObject(i).getJSONObject("data").getString("content");
                                    String align = jsonArray.getJSONObject(i).getJSONObject("data").getString("align");

                                    Detail detail = new Detail(type, img, content, align);
                                    details.add(detail);
                                }
                            }
                            details.add(share);

                            final String nameCategory = response.getJSONObject("response").getJSONObject("parent_category_data").getString("title");
//                    Typeface tfBold = Typeface.create(ResourcesCompat.getFont(DetailFragment.this, (configMap.get(FONT))), Typeface.ITALIC);
//                    selectedTextView.setTypeface(tfBold);

                            ConnectionUtils.getbottomarticle(id_show, parent_category, site_name, null, new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    super.onSuccess(statusCode, headers, response);
                                    try {
                                        MenuHomePaper nextarticle = new MenuHomePaper(response.getJSONObject("response").getJSONObject("next_article"));
                                        details.add(nextarticle);
                                        JSONArray jsonArray1 = response.getJSONObject("response").getJSONArray("related_bottom");

                                        for (int j = 0; j < jsonArray1.length(); j++) {
                                            MenuHomePaper relatedbottom = new MenuHomePaper(jsonArray1.getJSONObject(j));
                                            details.add(relatedbottom);
                                        }
                                        detailAdapter = new DetailAdapter(rootview.getContext(), details, nameCategory, font_size);
                                        if (rcvdetail != null)
                                            rcvdetail.setAdapter(detailAdapter);
                                        detailAdapter.setOnitemClickListener(DetailFragment.this);
                                        swipeRefreshLayout.setRefreshing(false);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void OnitemNextArticleClicked(MenuHomePaper menuHomePaper) {

        Intent intent = new Intent(DetailFragment.super.getContext(), OpenNextArticleActivity.class);
        intent.putExtra("ID_SHOW", menuHomePaper.getId());
        intent.putExtra("TITLE_SHOW", menuHomePaper.getTitle());
        intent.putExtra("DESCRIPTION_SHOW", menuHomePaper.getDescription());
        intent.putExtra("PARENT_CATEGORY", menuHomePaper.getParent_category());
        intent.putExtra("SITE_NAME", menuHomePaper.getSite_name());
        intent.putExtra("LINK_WEB", menuHomePaper.getLink_web());
        startActivity(intent);

    }

    @Override
    public void OnitemShareClicked() {

    }


    @Override
    public void OnitemVideoClicked(Detail detail) {
        Intent intent = new Intent(DetailFragment.super.getContext(), VideoActivity.class);
        intent.putExtra("URL_VIDEO", detail.getContent());
        startActivity(intent);
    }

    @Override
    public void OnitemToprelatedClicked(Toprelated toprelated) {
        Intent intent = new Intent(DetailFragment.super.getContext(), OpenNextArticleActivity.class);
        intent.putExtra("ID_SHOW", toprelated.getId());
        intent.putExtra("TITLE_SHOW", toprelated.getTitle());
        intent.putExtra("DESCRIPTION_SHOW", toprelated.getDescription());
        intent.putExtra("PARENT_CATEGORY", toprelated.getParent_category());
        intent.putExtra("SITE_NAME", toprelated.getSite_name());
        intent.putExtra("LINK_WEB", toprelated.getLink_web());
        startActivity(intent);
    }

    @Override
    public void OnitemImageClicked(Detail detail) {
        Intent intent = new Intent(DetailFragment.super.getContext(), ImageActivity.class);
        intent.putExtra("IMAGE",detail.getContent());
        ImageActivity.details = details;
        startActivity(intent);
    }
}
