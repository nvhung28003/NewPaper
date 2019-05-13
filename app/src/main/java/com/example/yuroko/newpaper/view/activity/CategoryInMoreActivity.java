package com.example.yuroko.newpaper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.view.fragment.TitleFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryInMoreActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.imvBackinother)
    ImageView imvBackinother;
    @BindView(R.id.txtcategoryinother)
    TextView txtcategoryinother;
    @BindView(R.id.imvsearchinother)
    ImageView imvsearchinother;
    private TitleFragment titleFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inother);
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        String name_category = intent.getStringExtra("NAME_CATEGORY");
        ButterKnife.bind(this);
        if(titleFragment == null)
        {
            titleFragment = new TitleFragment();
        }
        Bundle b = new Bundle();
        b.putString("ID",id);
        titleFragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.linearinother, titleFragment).commit();
        txtcategoryinother.setText(name_category);
        imvBackinother.setOnClickListener(this);
        imvsearchinother.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imvBackinother:
                finish();
                break;
            case  R.id.imvsearchinother :
                Intent intent = new Intent(this,SearchActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }
}
