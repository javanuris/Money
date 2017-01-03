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

import com.example.user.money.enums.OperationType;
import com.example.user.money.gui.MenuExpandableList;
import com.example.user.money.objects.AppContext;
import com.example.user.money.objects.Operation;

import java.util.List;

public class OperationsFragment extends Fragment{
    private TextView textView;
    private OperationType transactionType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_content , container, false);
        textView = (TextView)view.findViewById(R.id.textView);
        switch (Integer.valueOf(getArguments().getInt(MenuExpandableList.OPERATION_TYPE))){
            case 0:{
                this.transactionType = OperationType.INCOME;
                break;
            }
            case 1:{
                this.transactionType = OperationType.OUTCOME;
                break;
            }
        }
        fillTextContent();

        return view;
    }
    private void fillTextContent(){
        List<Operation> listOperations = AppContext.getDbAdapter().getTranscations(transactionType);
        for(Operation operation: listOperations){
            textView.setText(operation.getAmount().toString()+"\n");

        }

    }

}
