package com.example.yuroko.newpaper.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yuroko.newpaper.R;

import com.example.yuroko.newpaper.entity.Category;
import com.example.yuroko.newpaper.utils.ConnectionUtils;
import com.example.yuroko.newpaper.view.fragment.MoreFragment;
import com.example.yuroko.newpaper.view.fragment.TitleFragment;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout relativefirst;
    private ImageView imvtopSearch;
    private ImageView imvbottomicon1;
    private ImageView imvbottomicon2;
    private ImageView imvbottomicon3;
    private ImageView imvbottomicon4;
    private ImageView imvbottomicon5;
    private ImageView imvsplash;

    private TextView txtbottom1;
    private TextView txtbottom2;
    private TextView txtbottom3;
    private TextView txtbottom4;
    private TextView txtbottom5;

    private LinearLayout linearbottom1;
    private LinearLayout linearbottom2;
    private LinearLayout linearbottom3;
    private LinearLayout linearbottom4;
    private LinearLayout linearbottom5;
    private TitleFragment titleHomeFragment;
    private TitleFragment title2Fragment;
    private TitleFragment title3Fragment;
    private TitleFragment title4Fragment;
    private MoreFragment moreFragment;
    private RelativeLayout retoolbar;


    CountDownTimer countDownTimer;
    public ArrayList<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initializeComponents();

        imvtopSearch.setOnClickListener(this);
        linearbottom1.setOnClickListener(this);
        linearbottom2.setOnClickListener(this);
        linearbottom3.setOnClickListener(this);
        linearbottom4.setOnClickListener(this);
        linearbottom5.setOnClickListener(this);

        countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                imvsplash.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {

                imvsplash.setVisibility(View.GONE);

            }
        };
        countDownTimer.start();
        categories.clear();
        ConnectionUtils.getallcategory(null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Category category = new Category(jsonArray.getJSONObject(i));
                        categories.add(category);
                    }
                    Category home = new Category("", "Home", R.drawable.home, R.drawable.homeactive);
                    categories.add(0, home);
                    Loadlinearbottom();
                    openTitleHomeFragment();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public void openTitleHomeFragment() {
        if (titleHomeFragment == null) {
            titleHomeFragment = new TitleFragment();
        }
        retoolbar.setVisibility(View.VISIBLE);
        Bundle b = new Bundle();
        b.putString("ID", categories.get(0).getId());
        titleHomeFragment.setArguments(b);
        Loadlinearbottom();
        imvbottomicon1.setImageResource(R.drawable.homeactive);
        txtbottom1.setTextColor(Color.parseColor("#FEE155"));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.LinearMiddle, titleHomeFragment).commit();

    }

    public void openTitle1Fragment() {
        if (title2Fragment == null) {
            title2Fragment = new TitleFragment();
        }
        retoolbar.setVisibility(View.VISIBLE);
        Bundle b = new Bundle();
        b.putString("ID", categories.get(1).getId());
        title2Fragment.setArguments(b);
        Loadlinearbottom();
        Glide.with(MainActivity.this).load(categories.get(1).getIconactive()).into(imvbottomicon2);
        txtbottom2.setTextColor(Color.parseColor("#FEE155"));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.LinearMiddle, title2Fragment).commit();

    }

    public void openTitle2Fragment() {
        if (title3Fragment == null) {
            title3Fragment = new TitleFragment();
        }
        retoolbar.setVisibility(View.VISIBLE);
        Bundle b = new Bundle();
        b.putString("ID", categories.get(2).getId());
        title3Fragment.setArguments(b);
        Loadlinearbottom();
        Glide.with(MainActivity.this).load(categories.get(2).getIconactive()).into(imvbottomicon3);
        txtbottom3.setTextColor(Color.parseColor("#FEE155"));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.LinearMiddle, title3Fragment).commit();
    }

    public void openTitle3Fragment() {
        if (title4Fragment == null) {
            title4Fragment = new TitleFragment();
        }
        retoolbar.setVisibility(View.VISIBLE);
        Bundle b = new Bundle();
        b.putString("ID", categories.get(3).getId());
        Loadlinearbottom();
        Glide.with(MainActivity.this).load(categories.get(3).getIconactive()).into(imvbottomicon4);
        txtbottom4.setTextColor(Color.parseColor("#FEE155"));
        title4Fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.LinearMiddle, title4Fragment).commit();
    }

    public void openmoreFragment() {
        if (moreFragment == null) {
            moreFragment = new MoreFragment();
        }
        Loadlinearbottom();
        imvbottomicon5.setImageResource(R.drawable.moreactive);
        txtbottom5.setTextColor(Color.parseColor("#FEE155"));

        retoolbar.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().replace(R.id.LinearMiddle, moreFragment).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imvtopSearch:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.linearbottom1:

                openTitleHomeFragment();

                break;
            case R.id.linearbottom2:

                openTitle1Fragment();

                break;
            case R.id.linearbottom3:

                openTitle2Fragment();

                break;
            case R.id.linearbottom4:

                openTitle3Fragment();
                break;
            case R.id.linearbottom5:

                openmoreFragment();

                break;
            default:
                break;

        }

    }

    private void initializeComponents() {
        imvtopSearch = findViewById(R.id.imvtopSearch);
        imvbottomicon1 = findViewById(R.id.imvbottomicon1);
        imvbottomicon2 = findViewById(R.id.imvbottomicon2);
        imvbottomicon3 = findViewById(R.id.imvbottomicon3);
        imvbottomicon4 = findViewById(R.id.imvbottomicon4);
        imvbottomicon5 = findViewById(R.id.imvbottomicon5);
        imvsplash = findViewById(R.id.imvsplash);
        relativefirst = findViewById(R.id.relativefisrt);

        txtbottom1 = findViewById(R.id.txtbottom1);
        txtbottom2 = findViewById(R.id.txtbottom2);
        txtbottom3 = findViewById(R.id.txtbottom3);
        txtbottom4 = findViewById(R.id.txtbottom4);
        txtbottom5 = findViewById(R.id.txtbottom5);


        linearbottom1 = findViewById(R.id.linearbottom1);
        linearbottom2 = findViewById(R.id.linearbottom2);
        linearbottom3 = findViewById(R.id.linearbottom3);
        linearbottom4 = findViewById(R.id.linearbottom4);
        linearbottom5 = findViewById(R.id.linearbottom5);
        retoolbar = findViewById(R.id.retoolbar);

    }

    private void Loadlinearbottom() {
        imvbottomicon5.setImageResource(R.drawable.more);
        txtbottom5.setTextColor(Color.parseColor("#97979C"));
        imvbottomicon1.setImageResource(R.drawable.home);
        txtbottom1.setTextColor(Color.parseColor("#97979C"));
        Glide.with(MainActivity.this).load(categories.get(1).getIcon()).into(imvbottomicon2);
        txtbottom2.setText(categories.get(1).getTitle());
        txtbottom2.setTextColor(Color.parseColor("#97979C"));
        Glide.with(MainActivity.this).load(categories.get(2).getIcon()).into(imvbottomicon3);
        txtbottom3.setText(categories.get(2).getTitle());
        txtbottom3.setTextColor(Color.parseColor("#97979C"));
        Glide.with(MainActivity.this).load(categories.get(3).getIcon()).into(imvbottomicon4);
        txtbottom4.setText(categories.get(3).getTitle());
        txtbottom4.setTextColor(Color.parseColor("#97979C"));
    }

    public void highlighCate(Category cate) {
        if (cate.getId() == null) {
            //hight
            return;
        }

        int indexCate = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == cate.getId()) {
                indexCate = i;
                break;
            }
        }
    }


    public int highlighCate1(Category cate) {
        int indexCate = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == cate.getId()) {
                indexCate = i;
                break;
            }
        }
        return indexCate;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }
}

