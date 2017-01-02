package com.example.user.money.gui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.user.money.Content;
import com.example.user.money.R;
import com.example.user.money.adapters.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 01.01.2017.
 */

public class MenuExpandableList {
    private Activity context;
    private DrawerLayout navDrawer;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String>listGroup;
    private HashMap<String , List<String>> mapChild;


    public MenuExpandableList(final Activity context) {
        this.context = context;
        expListView = (ExpandableListView)context.findViewById(R.id.exlvMenu);
        navDrawer = (DrawerLayout)context.findViewById(R.id.drawer_layout);

        fillMenu();

        listAdapter = new ExpandableListAdapter(context , listGroup , mapChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Fragment fragment = new Content();
                Bundle args = new Bundle();
                args.putInt("TEST_CONTENT" , childPosition);
                fragment.setArguments(args);
                FragmentManager fragmentManager = context.getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame , fragment).commit();
                navDrawer.closeDrawer(Gravity.LEFT);
                return true;
            }
        });
    }

    private void fillMenu(){
        listGroup = new ArrayList<>();
        mapChild = new HashMap<String, List<String>>();

        listGroup.add(context.getResources().getString(R.string.menu1));
        listGroup.add(context.getResources().getString(R.string.menu2));
        listGroup.add(context.getResources().getString(R.string.menu3));

        List<String>menu1 = new ArrayList<>();
        for(String child :context.getResources().getStringArray(R.array.menu4_childs)){
            menu1.add(child);
        }
        List<String>menu2 = new ArrayList<>();
        for(String child :context.getResources().getStringArray(R.array.menu2_childs)){
            menu2.add(child);
        }
        List<String>menu3 = new ArrayList<>();
        for(String child :context.getResources().getStringArray(R.array.menu3_childs)){
            menu3.add(child);
        }

        mapChild.put(listGroup.get(0) , menu1);
        mapChild.put(listGroup.get(1) , menu2);
        mapChild.put(listGroup.get(2) , menu3);

    }
}
