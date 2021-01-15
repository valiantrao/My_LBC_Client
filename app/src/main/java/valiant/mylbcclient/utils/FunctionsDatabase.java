package valiant.mylbcclient.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import valiant.mylbcclient.BuildConfig;


public class FunctionsDatabase extends SQLiteOpenHelper {

    private final String TAG = FunctionsDatabase.this.getClass().getSimpleName();
    private static final String DATABASE_NAME = BuildConfig.APPLICATION_ID;

    private static final String TABLE_NAME = "functions";

    public static final String key_function_name = "function_name";
    public static final String key_ad_id = "ad_id";
    public static final String key_ad_ids_to_compare = "amount";
    public static final String key_amount = "photographer_url";
    public static final String key_new_trade_first_msg = "new_trade_first_msg";
    public static final String key_isAbove = "isAbove";



    public FunctionsDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                key_function_name + " varchar NOT NULL, " +
                key_ad_id + " varchar NOT NULL, " +
                key_ad_ids_to_compare + " varchar NOT NULL, " +
                key_amount + " varchar NOT NULL, " +
                key_new_trade_first_msg + " varchar NOT NULL, " +
                key_isAbove + " boolean NOT NULL)";

        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addFunction(String function_name, String ad_id, String ad_ids_to_compare, String amount, String new_trade_first_msg, boolean isAbove) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(key_function_name, function_name);
        contentValues.put(key_ad_id, ad_id);
        contentValues.put(key_ad_ids_to_compare, ad_ids_to_compare);
        contentValues.put(key_amount, amount);
        contentValues.put(key_new_trade_first_msg, new_trade_first_msg);
        contentValues.put(key_isAbove, isAbove);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();

    }
    public Cursor getFunctions(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

    public Cursor getFunctionNames(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + key_function_name + ", " + key_ad_id + " FROM " + TABLE_NAME;

        return db.rawQuery(query, null);
    }

    public void deleteFunction(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE ID = \"" + id + "\"";
        Log.d(TAG, "deleteName: query: " + query);
        db.execSQL(query);
        db.close();
    }
}
