package com.example.user.money.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.user.money.R;


/**
 * Created by User on 05.01.2017.
 */

public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settting);
    }
}
