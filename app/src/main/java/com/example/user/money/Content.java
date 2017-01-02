package com.example.user.money;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Content extends Fragment{
private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_content , container, false);
        textView = (TextView)view.findViewById(R.id.textView);
        textView.setText(String.valueOf(getArguments().getInt("TEST_CONTENT")));
        return view;
    }
}
