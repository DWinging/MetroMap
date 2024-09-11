package com.example.newmetro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.newmetro.Menu.MainMenuAdapter;
import com.example.newmetro.Menu.MainMenuGroup;
import com.example.newmetro.Menu.SettingMenuAdapter;
import com.example.newmetro.Menu.SettingMenuGroup;
import com.example.newmetro.MetroMap.Metro_map_fragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Metro_map_fragment metro_map_fragment;

    //Layout
    DrawerLayout drawerLayout;
    ImageButton menu_button;

    //Menu
    NavigationView navigationView;
    ExpandableListView settingMenuList;
    ListView mainMenuList;

    //Menu Link
    ArrayList<MainMenuGroup> mainMenus;
    ArrayList<SettingMenuGroup> settingMenus;

    //Toolbar
    Toolbar toolbar;
    TextView page_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metro_map_fragment = new Metro_map_fragment();

        //FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container_view, metro_map_fragment).commitAllowingStateLoss();

        drawerLayout = (DrawerLayout) findViewById(R.id.main);
        menu_button = (ImageButton) findViewById(R.id.menu_button);

        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.menu_button){
                    if(drawerLayout.isDrawerOpen(R.id.main)){
                        drawerLayout.closeDrawer(GravityCompat.START);
                    }
                    else{
                        drawerLayout.openDrawer(GravityCompat.START);
                    }
                }
            }
        });

        //Menu
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setMainMenu();
        setSettingMenu();

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
    }

    public void setMainMenu(){
        mainMenuList = (ListView) findViewById(R.id.main_menu);


        mainMenus = new ArrayList<>();
        mainMenus.add(new MainMenuGroup(getResources().getString(R.string.lostAndFind), this.getResources().getDrawable(R.drawable.icon_lost_property, null)));

        MainMenuAdapter menuLinkAdapter = new MainMenuAdapter(this, mainMenus);
        mainMenuList.setAdapter(menuLinkAdapter);

        mainMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.lost112.go.kr/lost/lostList.do"));
                startActivity(intent);
            }
        });

    }

    public void setSettingMenu(){
        settingMenuList = (ExpandableListView) findViewById(R.id.setting_menu);

        //Font
        settingMenus = new ArrayList<>();
        SettingMenuGroup item = new SettingMenuGroup(getResources().getString(R.string.fontSize), this.getResources().getDrawable(R.drawable.icon_font, null));
        item.child.add(getResources().getString(R.string.fontSizeSmall));
        item.child.add(getResources().getString(R.string.fontSizeNormal));
        item.child.add(getResources().getString(R.string.fontSizeBig));
        settingMenus.add(item);

        //hand mode
        item = new SettingMenuGroup(getResources().getString(R.string.hand_mode), this.getResources().getDrawable(R.drawable.icon_left_hand, null));
        item.child.add("왼손 모드");
        item.child.add("오른손 모드");
        settingMenus.add(item);

        //Theme
        item = new SettingMenuGroup(getResources().getString(R.string.menu_theme), this.getResources().getDrawable(R.drawable.icon_theme, null));
        settingMenus.add(item);

        SettingMenuAdapter adapter = new SettingMenuAdapter(getApplicationContext(), R.layout.setting_menu_group, R.layout.setting_menu_child, settingMenus);
        settingMenuList.setGroupIndicator(null);
        settingMenuList.setAdapter(adapter);

        settingMenuList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String groupName = settingMenus.get(groupPosition).groupName;

                if(groupName.equals(getResources().getString(R.string.menu_theme))){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }

                return false;
            }
        });

        settingMenuList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                drawerLayout.closeDrawer(GravityCompat.START);
                String child = settingMenus.get(groupPosition).child.get(childPosition);
                Bundle bundle = new Bundle();

                if(settingMenus.get(groupPosition).groupName.equals(getResources().getString(R.string.fontSize))){
                    return true;
                }

                if(settingMenus.get(groupPosition).groupName.equals(getResources().getString(R.string.hand_mode))){
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}