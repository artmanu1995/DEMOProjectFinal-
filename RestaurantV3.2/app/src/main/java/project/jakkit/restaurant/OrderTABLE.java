package project.jakkit.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KHAMMA on 11/09/2017.
 */

public class OrderTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;

    public static final String TABLE_ORDER = "orderTABLE";
    public static final String COLUMN_ID_ORDER = "_id";
    public static final String COLUMN_TABLEID = "TableID";
    public static final String COLUMN_NAMEFOOD = "NameFood";
    public static final String COLUMN_HOT = "HotLevel";
    public static final String COLUMN_AMOUNT = "Amount";

    public OrderTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public String[] readAllListID() {

        String strListID[] = null;
        Cursor objCursor = readDatabase.query(TABLE_ORDER, new String[]{COLUMN_ID_ORDER, COLUMN_ID_ORDER}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_ORDER));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListID;
    }
    public String[] readAllListTableID() {

        String strListTableID[] = null;
        Cursor objCursor = readDatabase.query(TABLE_ORDER, new String[]{COLUMN_ID_ORDER, COLUMN_TABLEID}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListTableID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListTableID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_TABLEID));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListTableID;
    }
    public String[] readAllListNameFood() {

        String strListNameFood[] = null;
        Cursor objCursor = readDatabase.query(TABLE_ORDER, new String[]{COLUMN_ID_ORDER, COLUMN_NAMEFOOD}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListNameFood = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListNameFood[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_NAMEFOOD));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListNameFood;
    }

    public String[] readAllListHot() {

        String strListHot[] = null;
        Cursor objCursor = readDatabase.query(TABLE_ORDER, new String[]{COLUMN_ID_ORDER, COLUMN_HOT}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListHot = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListHot[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_HOT));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListHot;
    }
    public String[] readAllListAmount() {

        String strListAmount[] = null;
        Cursor objCursor = readDatabase.query(TABLE_ORDER, new String[]{COLUMN_ID_ORDER, COLUMN_AMOUNT}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListAmount = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListAmount[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_AMOUNT));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListAmount;
    }


    public long addValueToOrder(String strListID, String strTableID, String strNameFood, String strHot, Integer intAmount) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_ORDER, strListID);
        objContentValues.put(COLUMN_TABLEID, strTableID);
        objContentValues.put(COLUMN_NAMEFOOD, strNameFood);
        objContentValues.put(COLUMN_HOT, strHot);
        objContentValues.put(COLUMN_AMOUNT, intAmount);

        return writeDatabase.insert(TABLE_ORDER, null, objContentValues);
    }
}
