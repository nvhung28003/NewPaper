package com.example.yuroko.newpaper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.yuroko.newpaper.R;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoActivity extends AppCompatActivity {
    private JzvdStd jzvdStd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
            jzvdStd = findViewById(R.id.videoplayer);
        Intent intent =getIntent();
        String url_video = intent.getStringExtra(  "URL_VIDEO");
        jzvdStd.setUp(url_video,null, Jzvd.SCREEN_WINDOW_NORMAL);

    }

    @Override
    public void onBackPressed() {
        if(Jzvd.backPress())
        {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
            Jzvd.releaseAllVideos();
    }
}
