package com.example.user.money.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.money.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 01.01.2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listGroup;
    private HashMap<String , List<String>>mapChild;

    public ExpandableListAdapter(Context context, List<String> listGroup, HashMap<String, List<String>> mapChild) {
        this.context = context;
        this.listGroup = listGroup;
        this.mapChild = mapChild;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mapChild.get(listGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mapChild.get(listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       String groupMenuText = (String)getGroup(groupPosition);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_item_group, null);
        }
        TextView txtMenuGroup = (TextView)convertView.findViewById(R.id.menuItemGroup);
        txtMenuGroup.setText(groupMenuText);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childMenuText = (String)getChild(groupPosition , childPosition);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.menu_item_child, null);
        }
            TextView txtMenuChild = (TextView)convertView.findViewById(R.id.txtChildrenMenu);
            txtMenuChild.setText(childMenuText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}
