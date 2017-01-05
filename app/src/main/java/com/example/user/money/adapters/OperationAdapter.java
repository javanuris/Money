package com.example.user.money.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.view.LayoutInflaterFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.money.R;
import com.example.user.money.database.DbAdapter;
import com.example.user.money.enums.OperationType;
import com.example.user.money.objects.AppContext;
import com.example.user.money.objects.ImageUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by User on 04.01.2017.
 */

public class OperationAdapter extends CursorAdapter{
    private Context context;

    private AppContext appContext;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");

    private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    private LayoutInflater layoutInflater;
    public OperationAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        this.appContext =(AppContext)context.getApplicationContext();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.listview_row_operation , parent , false);

        ViewHolder holder = new ViewHolder();
        holder.source = (TextView)view.findViewById(R.id.text_view_name);
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

        String imagePath = appContext.getIconsFolder()+"/"+cursor.getString(cursor.getColumnIndex(DbAdapter.ALIAS_SOURCE_ID))+".png";

        try {
            holder.image.setImageBitmap(ImageUtils.getSizedBitmap(imagePath , AppContext.IMAGE_WIDTH_THMB , AppContext.IMAGE_HEIGHT_THMB));
        }catch (Exception e){
            e.printStackTrace();
        }

        long dateTime = cursor.getLong(cursor.getColumnIndex(DbAdapter.ALIAS_OPERATION_DATETIME));
        calendar.setTimeInMillis(dateTime);

        holder.date.setText(dateFormat.format(calendar.getTime())+ ", ");
        holder.time.setText(dateFormat.format(calendar.getTime()));

        if(cursor.getInt(cursor.getColumnIndex(DbAdapter.ALIAS_TYPE_ID))==Integer.valueOf(OperationType.INCOME.getId())){
            holder.type.setTextColor(context.getResources().getColor(R.color.green_dark));
        }else {
            holder.type.setTextColor(context.getResources().getColor(R.color.red_dark));
        }
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
