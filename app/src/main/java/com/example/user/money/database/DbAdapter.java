package com.example.user.money.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 02.01.2017.
 */

public class DbAdapter {

    public static final String DB_NAME = "money.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_OPERATION = "operation";
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    private static final String DB_CREATE = "CREATE TABLE [operation] ( "
            +"[_id] INTEGER PRIMARY KEY ON CONFLICT FAIL AUTOINCREMENT, "
            +"[date_operation]DATETIME NOT NULL, "
            +"[amount] INTEGER NOT NULL, "
            +"[type_id] INTEGER NOT NULL CONSTRAINT [fk_operation_type] REFERENCES [spr_operationType]([_id]) ON DELETE RESTRICT ON UPDATE CASCADE)";


    public DbAdapter(Context context){
        this.context = context;
    }
    public void createDatabaseInstance(){
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public void getTranscationList(){
        Cursor c =db.query(TABLE_OPERATION , null, null , null , null, null, null);
        if(c!=null){
            while (c.moveToNext()){
                System.out.println(c.getInt(c.getColumnIndex("amount")));
            }
        }


    }

    private static class DbHelper extends SQLiteOpenHelper{
        public DbHelper(Context context){
        super(context , DB_NAME , null , DB_VERSION);

}@Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
