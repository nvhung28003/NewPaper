package com.example.yuroko.newpaper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.adapter.ImageViewPagerAdapter;
import com.example.yuroko.newpaper.entity.Detail;
import com.example.yuroko.newpaper.entity.Image;
import com.example.yuroko.newpaper.view.fragment.ImageFragment;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private ImageView imvdelete;
    public static List<Object> details = new ArrayList<>();
    private List<Image> images= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        viewPager = findViewById(R.id.vpgimage);
        imvdelete = findViewById(R.id.imvdelete);


        for (int i = 0; i < details.size(); i++) {
            if (details.get(i) instanceof Detail) {
                Object o = details.get(i);
                Detail detail = (Detail) o;
                if(detail.getType().equals("image")==true)
                {
                    Image image = new Image(detail.getContent());
                    images.add(image);
                }
            }
        }

        ImageViewPagerAdapter imageViewPagerAdapter = new ImageViewPagerAdapter(getSupportFragmentManager(), images);

        viewPager.setAdapter(imageViewPagerAdapter);
        Intent intent = getIntent();
        String image = intent.getStringExtra("IMAGE");

        for(int i=0;i< images.size();i++)
        {
            if(images.get(i).getImage().equals(image)==true)
            {
                viewPager.setCurrentItem(i);
            }
        }


        imvdelete.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imvdelete:
                finish();
                break;
                default:
                    break;
        }
    }
}
