package project.jakkit.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KHAMMA on 11/09/2017.
 */

public class ListOrderTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;

    public static final String TABLE_LISTO = "listoTABLE";
    public static final String COLUMN_ID_LISTO = "_id";
    public static final String COLUMN_TABLEID = "TableID";
    public static final String COLUMN_NAMEFOOD = "NameFood";
    public static final String COLUMN_HOT = "HotLevel";
    public static final String COLUMN_AMOUNT = "Amount";

    public ListOrderTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();
    }   // Constructor

    public String[] readAllListLID() {

        String strListLID[] = null;
        Cursor objCursor = readDatabase.query(TABLE_LISTO, new String[]{COLUMN_ID_LISTO, COLUMN_ID_LISTO}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListLID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListLID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_LISTO));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListLID;
    }
    public String[] readAllListTableID() {

        String strListTableID[] = null;
        Cursor objCursor = readDatabase.query(TABLE_LISTO, new String[]{COLUMN_ID_LISTO, COLUMN_TABLEID}, null, null, null, null, null);
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
        Cursor objCursor = readDatabase.query(TABLE_LISTO, new String[]{COLUMN_ID_LISTO, COLUMN_NAMEFOOD}, null, null, null, null, null);
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
        Cursor objCursor = readDatabase.query(TABLE_LISTO, new String[]{COLUMN_ID_LISTO, COLUMN_HOT}, null, null, null, null, null);
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
        Cursor objCursor = readDatabase.query(TABLE_LISTO, new String[]{COLUMN_ID_LISTO, COLUMN_AMOUNT}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListAmount = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListAmount[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_AMOUNT));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListAmount;
    }

    public long addValueToListOrder(String strOpenID, String strTableID, String strNameFood, String strLHotLevel, Integer intLAmount) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_LISTO, strOpenID);
        objContentValues.put(COLUMN_TABLEID, strTableID);
        objContentValues.put(COLUMN_NAMEFOOD, strNameFood);
        objContentValues.put(COLUMN_HOT, strLHotLevel);
        objContentValues.put(COLUMN_AMOUNT, intLAmount);

        return writeDatabase.insert(TABLE_LISTO, null, objContentValues);
    }
}
