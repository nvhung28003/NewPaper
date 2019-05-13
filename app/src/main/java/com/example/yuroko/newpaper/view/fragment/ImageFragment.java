package com.example.yuroko.newpaper.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.yuroko.newpaper.R;

public class ImageFragment extends Fragment {
    private View rootview;
    private ImageView imageView;
    private ScaleGestureDetector scaleGestureDetector;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.imagefragment, container, false);
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = rootview.findViewById(R.id.image);

        Bundle bundle = getArguments();

        String image = bundle.getString("IMAGE");
        Glide.with(ImageFragment.super.getContext()).load(image).into(imageView);

        scaleGestureDetector = new ScaleGestureDetector(ImageFragment.super.getContext(),new MyGesture());
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                return true ;
            }
        });
    }

    class MyGesture extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        float scale = 1.0F , onScaleStart =0, onScaleEnd = 0;
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            imageView.setScaleX(scale);
            imageView.setScaleY(scale);
            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            onScaleStart = scale;
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            onScaleEnd = scale;
            super.onScaleEnd(detector);
        }
    }
}
