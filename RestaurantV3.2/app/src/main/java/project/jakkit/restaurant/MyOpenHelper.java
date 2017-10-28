package project.jakkit.restaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KHAMMA on 03/04/2017.
 */

public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    private static final String DATABASE_NAME = "restaurantV4.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table userTABLE (_id text primary key, " + " User text, Password text, Name text);";
    private static final String CREATE_FOOD_TABLE = "create table foodTABLE (_id text primary key, "+" Food text, Price integer);";
    private static final String CREATE_TO_TABLE = "create table toTABLE (_id text primary key, "+" Status text)";
    private static final String CREATE_STATUSTO_TABLE = "create table statustoTABLE (_id text primary key, "+" StatusOF text)";
    private static final String CREATE_ORDER_TABLE = "create table orderTABLE (_id text primary key, "+" TableID text, NameFood text, HotLevel text, Amount integer)";
    private static final String CREATE_LISTO_TABLE = "create table listoTABLE (_id text primary key, "+" TableID text, NameFood text, HotLevel text, Amount integer)";
    private static final String CREATE_SHOWO_TABLE = "create table showoTABLE (_id text primary key, "+" FoodID text, NameFood text, Hot text, Amount integer, Price integer)";

    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }   // Constructor
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(CREATE_TO_TABLE);
        db.execSQL(CREATE_STATUSTO_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_LISTO_TABLE);
        db.execSQL(CREATE_SHOWO_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}   // MyOpenHelper
