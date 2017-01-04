package com.example.user.money.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.money.enums.OperationType;

import java.util.ArrayList;
import java.util.List;



public class DbAdapter {

    public static final String DB_NAME = "money.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_OPERATION = "operation";
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public static final String ALIAS_ID="_id";
    public static final String ALIAS_AMOUNT="amount";
    public static final String ALIAS_CURRENCY="currency";
    public static final String ALIAS_OPERATION_DATE="operationDate";
    public static final String ALIAS_OPERATION_TIME="operationTime";
    public static final String ALIAS_SOURCE="source";
    public static final String ALIAS_TYPE="type";


    public DbAdapter(Context context){
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
    }


    public Cursor getAllOperations(){
        String sql ="select "
                + "t.name as "+ALIAS_TYPE
                + ",o._id as "+ALIAS_ID
                + ",c.short_name as " + ALIAS_CURRENCY
                + ",o.[amount] as " + ALIAS_AMOUNT
                + ",o.[operation_date] as "+ALIAS_OPERATION_DATE
                + ",o.[operation_time] as "+ALIAS_OPERATION_TIME
                + ",s.[name] as "+ALIAS_SOURCE
                +" from operations o "
                + " inner join spr_currency c on o.currency_id=c.[_id]  "
                + " inner join spr_operationsource s on o.source_id=s.[_id] "
                + " inner join spr_operationtype t on o.source_id=t.[_id] ";
        return db.rawQuery(sql ,null);
    }

   public  Cursor getOperations(OperationType type){
       Cursor c = null;
       StringBuilder builder = new StringBuilder();
       builder.append(
               "select "
                       + "t.name as "+ALIAS_TYPE
                       + ",o._id as "+ALIAS_ID
                       + ",c.short_name as " + ALIAS_CURRENCY
                       + ",o.[amount] as " + ALIAS_AMOUNT
                       + ",o.[operation_date] as "+ALIAS_OPERATION_DATE
                       + ",o.[operation_time] as "+ALIAS_OPERATION_TIME
                       + ",s.[name] as "+ALIAS_SOURCE
                       +" from operations o "
                       + " inner join spr_currency c on o.currency_id=c.[_id]  "
                       + " inner join spr_operationsource s on o.source_id=s.[_id] "
                       + " inner join spr_operationtype t on o.source_id=t.[_id] "
       );
       if(type!=OperationType.ALL){
            builder.append(" where o.type_id=?");
           c = db.rawQuery(builder.toString() , new String[]{type.getId()});
       }else{
           c = db.rawQuery(builder.toString() , null);
       }
       return c;
   }



    private static class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context){
        super(context , DB_NAME , null , DB_VERSION);

}@Override
        public void onCreate(SQLiteDatabase db) {
         //   db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
