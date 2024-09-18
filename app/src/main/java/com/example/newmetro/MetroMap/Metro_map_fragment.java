package com.example.newmetro.MetroMap;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newmetro.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class Metro_map_fragment extends Fragment {

    //ImageView
    ImageView metro_map;
    Bitmap bitmap;
    Bitmap resized;

    //Button
    FloatingActionButton nav_btn_left;
    FloatingActionButton nav_btn_right;

    SharedPreferences preferces;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.metro_map_fragment, container, false);

        preferces = requireActivity().getSharedPreferences("theme", 0);
//        scrollView = (ScrollView) view.findViewById(R.id.ScrollView);
//        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.HorizontalScrollView);
//
//        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.metro_map);
//        resized = Bitmap.createScaledBitmap(bitmap, 5000, 5000, true);
//        metro_map = (ImageView) view.findViewById(R.id.metro_map);
//        metro_map.setImageBitmap(resized);

        nav_btn_left = (FloatingActionButton) view.findViewById(R.id.nav_button_left);
        nav_btn_right = (FloatingActionButton) view.findViewById(R.id.nav_button_right);
        navBtnChange(preferces.getString("hand", "오른손 모드"));

        return view;
    }

    public void navBtnChange(String mode){
        SharedPreferences.Editor editor = preferces.edit();
        
        if(mode.equals("왼손 모드")){
            editor.putString("hand", "왼손 모드");
            nav_btn_left.setVisibility(View.VISIBLE);
            nav_btn_right.setVisibility(View.GONE);
        }
        else{
            editor.putString("hand", "오른손 모드");
            nav_btn_right.setVisibility(View.VISIBLE);
            nav_btn_left.setVisibility(View.GONE);
        }
        
        editor.commit();
    }

}
