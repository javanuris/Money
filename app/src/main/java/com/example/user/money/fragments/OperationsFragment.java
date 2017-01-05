package com.example.user.money.fragments;

import android.app.Fragment;
import android.app.ListFragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.money.adapters.OperationAdapter;
import com.example.user.money.enums.OperationType;
import com.example.user.money.gui.MenuExpandableList;
import com.example.user.money.objects.AppContext;

import java.util.List;

public class OperationsFragment extends ListFragment{
    private TextView textView;
    private OperationType operationType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int type = Integer.valueOf(getArguments().getInt(MenuExpandableList.OPERATION_TYPE));
        switch (type){
            case 0 :{
                this.operationType = OperationType.ALL;
                break;
            }
            case 1 :{
                this.operationType = OperationType.INCOME;
                break;
            }
            case 2 :{
                this.operationType = OperationType.OUTCOME;
                break;
            }

        }
        OperationAdapter operationAdapter = new OperationAdapter(getActivity() , AppContext.getDbAdapter().getOperations(operationType),false);
    setListAdapter(operationAdapter);

    }
}


