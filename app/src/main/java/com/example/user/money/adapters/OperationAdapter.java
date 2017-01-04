package com.example.user.money.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.money.R;
import com.example.user.money.database.DbAdapter;

/**
 * Created by User on 04.01.2017.
 */

public class OperationAdapter extends CursorAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
    public OperationAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        this.context =context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.listview_row_operation , parent , false);

        ViewHolder holder = new ViewHolder();
        holder.source = (TextView)view.findViewById(R.id.text_view_source);
        holder.date = (TextView)view.findViewById(R.id.text_view_date);
        holder.time = (TextView)view.findViewById(R.id.text_view_time);
        holder.image = (ImageView) view.findViewById(R.id.imageView);
        holder.amount = (TextView)view.findViewById(R.id.text_view_amount);
        holder.type = (TextView)view.findViewById(R.id.text_view_type);
        holder.currency = (TextView)view.findViewById(R.id.text_view_currency);

        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.amount.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.ALIAS_AMOUNT)));
        holder.source.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.ALIAS_SOURCE)));
        holder.type.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.ALIAS_TYPE)));
        holder.currency.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.ALIAS_CURRENCY)));
    }


    static class ViewHolder{
        public TextView date;
        public ImageView image;
        public TextView time;
        public TextView amount;
        public TextView source;
        public TextView type;
        public TextView currency;
    }
}
