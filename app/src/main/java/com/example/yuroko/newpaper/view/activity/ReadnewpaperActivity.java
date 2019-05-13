package com.example.yuroko.newpaper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.adapter.ViewpagerAdapter;
import com.example.yuroko.newpaper.entity.MenuHomePaper;

import java.util.ArrayList;

public class ReadnewpaperActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TextView txtTitleYellow;
    private ImageView imvbacknextscreen;
    private ImageView imvShare;
    public static ArrayList<MenuHomePaper> menuHomePapers = new ArrayList<>();
    private String linkweb1 = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextscreen);
        imvbacknextscreen = findViewById(R.id.imvBack);
        viewPager = (ViewPager) findViewById(R.id.vpgdetail);
        imvShare = findViewById(R.id.imvShare);

        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(getSupportFragmentManager(), menuHomePapers);
        viewPager.setAdapter(viewpagerAdapter);

        txtTitleYellow = findViewById(R.id.txtYellowTitle);
        imvbacknextscreen.setOnClickListener(this);
        imvShare.setOnClickListener(this);
        Intent intent = getIntent();
         linkweb1 = intent.getStringExtra("LINK_WEB");
        int location = intent.getIntExtra("LOCATION", 0);
        txtTitleYellow.setText(menuHomePapers.get(location).getTitle());
        viewPager.setCurrentItem(location);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                txtTitleYellow.setText(menuHomePapers.get(i).getTitle());
                linkweb1 = menuHomePapers.get(i).getLink_web();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imvBack:
                finish();
                break;
            case R.id.imvShare:
                Intent sharingIntent =new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,linkweb1 );
                startActivity(Intent.createChooser(sharingIntent,"Share"));
                break;
            default:
                break;
        }
    }
}
