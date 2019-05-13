package com.example.yuroko.newpaper.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.adapter.MenuHomeAdapter;
import com.example.yuroko.newpaper.entity.Detail;
import com.example.yuroko.newpaper.view.fragment.DetailFragment;

public class OpenNextArticleActivity extends AppCompatActivity implements View.OnClickListener {
    private DetailFragment detailFragment;
    private TextView txtyellowtitleOpennextscreeen;
    private ImageView imvbackopennextarticle;
    private ImageView imvsharenextArticle;
    private  String link_web;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opennextarticle);
        txtyellowtitleOpennextscreeen = findViewById(R.id.txtYellowTitleopennextarticle);
        imvbackopennextarticle = findViewById(R.id.imvBackopennextarticle);
        imvsharenextArticle = findViewById(R.id.imvShareopennextarticle);
            detailFragment = new DetailFragment();

            Intent intent = getIntent();
            String id_show = intent.getStringExtra("ID_SHOW");
            String title_show = intent.getStringExtra("TITLE_SHOW");
            String descriptin_show = intent.getStringExtra("DESCRIPTION_SHOW");
            String parent_category = intent.getStringExtra("PARENT_CATEGORY");
            String site_name = intent.getStringExtra("SITE_NAME");
             link_web = intent.getStringExtra("LINK_WEB");

             txtyellowtitleOpennextscreeen.setText(title_show);



            Bundle b = new Bundle();

            b.putString("ID_SHOW",id_show);
            b.putString("TITLE_SHOW",title_show);
            b.putString("DESCRIPTION_SHOW",descriptin_show);
            b.putString("PARENT_CATEGORY",parent_category);
            b.putString("SITE_NAME",site_name);
            b.putString("LINK_WEB",link_web);
            detailFragment.setArguments(b);
            getSupportFragmentManager().beginTransaction().replace(R.id.opennextarticle, detailFragment).commit();
                imvbackopennextarticle.setOnClickListener(this);
                imvsharenextArticle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imvBackopennextarticle :
                finish();
                break;
            case R.id.imvShareopennextarticle:
                Intent sharingIntent =new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,link_web);
                startActivity(Intent.createChooser(sharingIntent,"Share"));
                default:
                    break;
        }
    }
}
