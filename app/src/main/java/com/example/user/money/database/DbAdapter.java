package com.example.user.money.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.money.enums.OperationType;
import com.example.user.money.objects.Operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class DbAdapter {

    public static final String DB_NAME = "money.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_OPERATION = "operation";
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    private ArrayList<Operation> listOperation = new ArrayList<>();

    private String ALIAS_ID = "_id";
    private String ALIAS_AMOUNT = "amout";
    private String ALIAS_CURRENCY = "currency";
    private String ALIAS_DATE_OPERATION = "date_operation";
    private String ALIAS_SOURCE = "source";
    private String ALIAS_TYPE = "type";

   /* private static final String DB_CREATE = "CREATE TABLE [operation] ( "
            +"[_id] INTEGER PRIMARY KEY ON CONFLICT FAIL AUTOINCREMENT, "
            +"[date_operation]DATETIME NOT NULL, "
            +"[amount] INTEGER NOT NULL, "
            +"[type_id] INTEGER NOT NULL CONSTRAINT [fk_operation_type] REFERENCES [spr_operationType]([_id]) ON DELETE RESTRICT ON UPDATE CASCADE)";
*/

    public DbAdapter(Context context){
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    public List<Operation> getCurrentOperations(){
        return listOperation;
    }

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Operation> getAllTranscations(){
        Cursor c =db.query(TABLE_OPERATION , null, null , null , null, null, null);
        return fillListOperations(c);

    }

   public  List<Operation> getTranscations(OperationType type){
       String sql = "select "
               +" t.name as "+ALIAS_TYPE
               +",o._id as "+ALIAS_ID
               +",c.name as "+ALIAS_CURRENCY
               +",o.[amout] as "+ALIAS_AMOUNT
               +",o.[date_operation] as "+ALIAS_DATE_OPERATION
               +",s.[name] as "+ALIAS_SOURCE
               +" from operation o "
               +"inner join spr_currency c on o.currency_id=c.[_id] "
               +"inner join spr_operationsource s on o.source_id=s.[_id] "
               +"inner join spr_operationtype t on o.source_id=t.[_id] "
               +"where o.type_id=?";
Cursor c = db.rawQuery(sql , new String[]{type.getId()});
       return fillListOperations(c);

   }

    private List<Operation>fillListOperations(Cursor c){
        listOperation.clear();

        try {
            if(c !=null){
             while (c.moveToNext()){
            Operation operation = new Operation();

                 operation.setId(c.getInt(c.getColumnIndex(ALIAS_ID)));
                 operation.setAmount(c.getDouble(c.getColumnIndex(ALIAS_AMOUNT)));
                 operation.setCurrency(c.getString(c.getColumnIndex(ALIAS_CURRENCY)));
                 operation.setDateOperation(dateFormat.parse(c.getString(c.getColumnIndex(ALIAS_DATE_OPERATION))));
                 operation.setSource(c.getString(c.getColumnIndex(ALIAS_SOURCE)));
                 operation.setType(c.getString(c.getColumnIndex(ALIAS_TYPE)));
                    listOperation.add(operation);

             }
            }
        }catch (ParseException e){
                e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listOperation;
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
