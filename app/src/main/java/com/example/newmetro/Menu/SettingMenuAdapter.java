package com.example.newmetro.Menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newmetro.R;

import java.util.ArrayList;

public class SettingMenuAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLay = 0;
    private int childLay = 0;
    private ArrayList<SettingMenuGroup> listView;
    private LayoutInflater myInf = null;

    public SettingMenuAdapter(Context context, int groupLay, int childLay, ArrayList<SettingMenuGroup> listView){
        this.context = context;
        this.groupLay = groupLay;
        this.childLay = childLay;
        this.listView = listView;
        this.myInf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = myInf.inflate(this.groupLay, parent, false);
        }
        TextView groupName = (TextView) convertView.findViewById(R.id.setting_menu_text);
        ImageView icon = (ImageView) convertView.findViewById(R.id.setting_menu_icon);
        groupName.setText(listView.get(groupPosition).groupName);
        icon.setImageDrawable(listView.get(groupPosition).icon);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = myInf.inflate(this.childLay, parent, false);
        }
        TextView childName = (TextView) convertView.findViewById(R.id.setting_menu_child);
        childName.setText(listView.get(groupPosition).child.get(childPosition));
        return convertView;
    }

    @Override
    public int getGroupCount() {
        return listView.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listView.get(groupPosition);
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listView.get(groupPosition).child.get(childPosition);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listView.get(groupPosition).child.size();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
