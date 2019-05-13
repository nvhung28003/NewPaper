package com.example.yuroko.newpaper.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuroko.newpaper.R;
import com.example.yuroko.newpaper.adapter.CategoryAdapter;
import com.example.yuroko.newpaper.entity.Category;
import com.example.yuroko.newpaper.utils.ConnectionUtils;
import com.example.yuroko.newpaper.view.activity.CategoryInMoreActivity;
import com.example.yuroko.newpaper.view.activity.MainActivity;
import com.example.yuroko.newpaper.view.activity.SettingActivity;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MoreFragment extends Fragment implements CategoryAdapter.OnitemClickListener, View.OnClickListener {
    private View rootview;
    TextView txtEdit;
    ImageView imvSetting;
    private ArrayList<Category> categoriess = new ArrayList<>();
    RecyclerView rcvcategory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.morefragment, container, false);
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtEdit = rootview.findViewById(R.id.txtEdit);
        imvSetting = rootview.findViewById(R.id.imvSetting);
        rcvcategory = rootview.findViewById(R.id.rcvcategory);
        if(categoriess.isEmpty()){
            categoriess.addAll(((MainActivity)getActivity()).getCategories());
        }
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(MoreFragment.super.getContext());
        rcvcategory.setLayoutManager(layoutManager);
        imvSetting.setOnClickListener(this);
        txtEdit.setOnClickListener(this);
//        if(!categories.isEmpty())
//        {
//            CategoryAdapter categoryAdapter = new CategoryAdapter(rootview.getContext(),categories);
//            rcvcategory.setAdapter(categoryAdapter);
//            categoryAdapter.setOnItemClickListener(MoreFragment.this);
//            return;
//        }
//
//        ConnectionUtils.getallcategory(null,new JsonHttpResponseHandler()
//        {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//                try {
//                    JSONArray jsonArray = response.getJSONObject("response").getJSONArray("data");
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Category category = new Category(jsonArray.getJSONObject(i));
//                        categories.add(category);
//
//                    }
        Category category1 = new Category("MAIN CATEGORY");
        Category category2 = new Category("OTHER CATEGORY");
        if(categoriess.get(0).getTitle().equals("MAIN CATEGORY")==false)
        {
            Log.d("EEE", categoriess.get(0).getTitle());
            Log.d("EEE", "1"+categoriess.get(1).getTitle());
            categoriess.add(0, category1);
            categoriess.add(5, category2);
        }
      //  categoriess.clear();
        CategoryAdapter categoryAdapter = new CategoryAdapter(MoreFragment.super.getContext(), categoriess);
        rcvcategory.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(MoreFragment.this);
//
////
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
    }

    @Override
    public void OnitemClickedtop(Category category) {
        //((MainActivity) getActivity()).openTitleHomeFragment();
        int a = ((MainActivity) getActivity()).highlighCate1(category);
        Log.d("highlighCate1", "highlighCate1 = " + a);

        if (a == 1) {
            ((MainActivity) getActivity()).openTitle1Fragment();
        } else if (a == 2) {
            ((MainActivity) getActivity()).openTitle2Fragment();
        } else if (a == 3) {
            ((MainActivity) getActivity()).openTitle3Fragment();
        } else if (a == 0) {
            ((MainActivity) getActivity()).openTitleHomeFragment();
        }

    }

    @Override
    public void OnitemClickedbot(Category category) {
        Intent intent = new Intent(MoreFragment.super.getContext(), CategoryInMoreActivity.class);
        intent.putExtra("ID", category.getId());
        intent.putExtra("NAME_CATEGORY", category.getTitle());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtEdit:

                break;
            case R.id.imvSetting:
                Intent intent1 = new Intent(MoreFragment.super.getContext(), SettingActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
