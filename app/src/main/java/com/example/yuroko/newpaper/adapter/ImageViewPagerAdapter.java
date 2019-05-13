package com.example.yuroko.newpaper.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuroko.newpaper.entity.Detail;
import com.example.yuroko.newpaper.entity.Image;
import com.example.yuroko.newpaper.view.fragment.ImageFragment;

import java.util.List;

public class ImageViewPagerAdapter extends FragmentPagerAdapter {
    private List<Image> images;
    public ImageViewPagerAdapter(FragmentManager fm,List<Image> images) {
        super(fm);
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        ImageFragment imageFragment = new ImageFragment();

        Bundle b = new Bundle();
        b.putString("IMAGE",images.get(position).getImage());
        imageFragment.setArguments(b);
        return imageFragment;
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
