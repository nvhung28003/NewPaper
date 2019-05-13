package com.example.yuroko.newpaper.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;

import com.example.yuroko.newpaper.entity.MenuHomePaper;
import com.example.yuroko.newpaper.view.fragment.DetailFragment;


import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdapter extends FragmentPagerAdapter {
    private List<MenuHomePaper> menuHomePapers;

    public ViewpagerAdapter(FragmentManager fm, List<MenuHomePaper> menuHomePapers) {
        super(fm);
        this.menuHomePapers = menuHomePapers;
    }

    @Override
    public Fragment getItem(int position) {
        DetailFragment detailFragment = new DetailFragment();

        Bundle b = new Bundle();

        b.putString("ID_SHOW",menuHomePapers.get(position).getId());
        b.putString("TITLE_SHOW",menuHomePapers.get(position).getTitle());
        b.putString("DESCRIPTION_SHOW",menuHomePapers.get(position).getDescription());
        b.putString("PARENT_CATEGORY",menuHomePapers.get(position).getParent_category());
        b.putString("SITE_NAME",menuHomePapers.get(position).getSite_name());

        detailFragment.setArguments(b);
        return detailFragment;

    }

    @Override
    public int getCount() {
        return menuHomePapers.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);

    }

}
