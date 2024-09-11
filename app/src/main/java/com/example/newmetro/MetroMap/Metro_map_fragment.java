package com.example.newmetro.MetroMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newmetro.R;

public class Metro_map_fragment extends Fragment {

    //ScrollView
    ScrollView scrollView;
    HorizontalScrollView horizontalScrollView;

    //ImageView
    ImageView metro_map;
    Bitmap bitmap;
    Bitmap resized;

    //Button
    static Button btn;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.metro_map_fragment, container, false);

//        scrollView = (ScrollView) view.findViewById(R.id.ScrollView);
//        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.HorizontalScrollView);
//
//        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.metro_map);
//        resized = Bitmap.createScaledBitmap(bitmap, 5000, 5000, true);
//        metro_map = (ImageView) view.findViewById(R.id.metro_map);
//        metro_map.setImageBitmap(resized);

        btn = (Button) view.findViewById(R.id.nav_button);

        return view;
    }

}
