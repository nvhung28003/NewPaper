package com.example.yuroko.newpaper.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.view.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseFontSizeFragment extends Fragment implements View.OnClickListener {
    private View rootview;
    @BindView(R.id.Relativesmall)
    RelativeLayout Relativesmall;

    @BindView(R.id.Relativemedium)
    RelativeLayout Relativemedium;

    @BindView(R.id.Relativelarge)
    RelativeLayout Relativelarge;

    @BindView(R.id.RelativeExtraLarge)
    RelativeLayout RelativeExtraLarge;

    @BindView(R.id.Relativehuge)
    RelativeLayout Relativehuge;

    @BindView(R.id.imvchecksmall)
    ImageView imvchecksmall;

    @BindView(R.id.imvcheckMedium)
    ImageView imvcheckMedium;

    @BindView(R.id.imvchecklarge)
    ImageView imvchecklarge;

    @BindView(R.id.imvcheckExtraLarge)
    ImageView imvcheckExtraLarge;

    @BindView(R.id.imvcheckHuge)
    ImageView imvcheckHuge;
    @BindView(R.id.imvBackfontsize) ImageView imvBackfontsize;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootview= inflater.inflate(R.layout.choosefontsizefragment,container,false);
        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,rootview);
        InvisibleCheck();
        Relativesmall.setOnClickListener(this);
        Relativemedium.setOnClickListener(this);
        Relativelarge.setOnClickListener(this);
        RelativeExtraLarge.setOnClickListener(this);
        Relativehuge.setOnClickListener(this);
        imvBackfontsize.setOnClickListener(this);
        InvisibleCheck();
        check();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Relativesmall:
                create_shared_preferences(16);
                InvisibleCheck();
                imvchecksmall.setVisibility(View.VISIBLE);

                break;
            case R.id.Relativemedium:
                InvisibleCheck();
                imvcheckMedium.setVisibility(View.VISIBLE);
                create_shared_preferences(18);
                break;
            case R.id.Relativelarge:
                InvisibleCheck();
                imvchecklarge.setVisibility(View.VISIBLE);
                create_shared_preferences(20);
                break;
            case R.id.RelativeExtraLarge:
                InvisibleCheck();
                imvcheckExtraLarge.setVisibility(View.VISIBLE);
                create_shared_preferences(22);
                break;
            case R.id.Relativehuge:
                InvisibleCheck();
                imvcheckHuge.setVisibility(View.VISIBLE);
                create_shared_preferences(24);
                break;
            case R.id.imvBackfontsize:
            {
                ( (SettingActivity) getActivity()).finishChooseFragment();

            }
                default:
                    break;
        }
    }

    public void InvisibleCheck()
    {
        imvchecksmall.setVisibility(View.GONE);
        imvcheckMedium.setVisibility(View.GONE);
        imvchecklarge.setVisibility(View.GONE);
        imvcheckExtraLarge.setVisibility(View.GONE);
        imvcheckHuge.setVisibility(View.GONE);
    }
    public void create_shared_preferences(int font_size)
    {
        getContext().getSharedPreferences("Shared_Preferences_Font_size", Context.MODE_PRIVATE)
                .edit()
                .putInt("font_size",font_size)
                .commit();
    }
    public void check()
    {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Shared_Preferences_Font_size", Context.MODE_PRIVATE);
        int font_size =sharedPreferences.getInt("font_size",12);
        if(font_size ==16)
        {
            imvchecksmall.setVisibility(View.VISIBLE);
        }
        else if(font_size == 18){
            imvcheckMedium.setVisibility(View.VISIBLE);
        }
        else if(font_size == 20){
            imvchecklarge.setVisibility(View.VISIBLE);

        }else if(font_size == 22)
        {
            imvcheckExtraLarge.setVisibility(View.VISIBLE);
        }else if(font_size ==24)
        {
            imvcheckHuge.setVisibility(View.VISIBLE);
        }
    }

}
