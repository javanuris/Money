package com.example.user.money;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.money.database.DbAdapter;
import com.example.user.money.gui.MenuExpandableList;

public class MainActivity extends Activity {

private static MenuExpandableList menuExpandableList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
  if(menuExpandableList ==null){
      menuExpandableList = new MenuExpandableList(this);
  }
        DbAdapter dbAdapter = new DbAdapter(this);
        dbAdapter.createDatabaseInstance();
        dbAdapter.getTranscationList();

    }


}
