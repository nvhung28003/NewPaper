package com.example.yuroko.newpaper.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.view.fragment.ChooseFontSizeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity  extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.imvBackSetting)
    ImageView imvBackSetting;
@BindView(R.id.txtfontsizeinsetting)
    TextView txtfontsizeinsetting;
    @BindView(R.id.relativeFontsize)
    RelativeLayout relativeFontsize;
    ChooseFontSizeFragment chooseFontSizeFragment;
    String name_font_size;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        imvBackSetting.setOnClickListener(this);
        relativeFontsize.setOnClickListener(this);
        txtfontsizeinsetting.setText(NameFontSize());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imvBackSetting:
                finish();
                break;
            case R.id.relativeFontsize:
                OpenChooseFontSizeFragment();
                break;
                default:
                    break;
        }
    }

    public void OpenChooseFontSizeFragment()
    {
        if(chooseFontSizeFragment == null)
        {
            chooseFontSizeFragment = new ChooseFontSizeFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.relativeSetting, chooseFontSizeFragment).commit();
    }
    public void finishChooseFragment()
    {
        if(!(chooseFontSizeFragment == null)){
            getSupportFragmentManager().beginTransaction().remove(chooseFontSizeFragment).commit();
        }
        txtfontsizeinsetting.setText(NameFontSize());

    }
    public String NameFontSize()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Shared_Preferences_Font_size", Context.MODE_PRIVATE);
        int font_size =sharedPreferences.getInt("font_size",16);
        if(font_size == 16)
        {
            name_font_size = "Small";
        }
        else if(font_size == 18)
        {
            name_font_size = "Medium";
        }
        else if(font_size == 20)
        {
            name_font_size = "Large";
        }
        else if(font_size == 22)
        {
            name_font_size = "Extra Large";
        }
        else if(font_size == 24)
        {
            name_font_size = "Huge";
        }
        else {
            name_font_size = "Small";
        }
        return name_font_size;
    }
}
