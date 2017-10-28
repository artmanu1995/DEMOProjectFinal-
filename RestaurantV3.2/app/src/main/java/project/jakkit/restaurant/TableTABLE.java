package project.jakkit.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KHAMMA on 08/09/2017.
 */

public class TableTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;

    public static final String TABLE_TO = "toTABLE";
    public static final String COLUMN_ID_TO = "_id";
    public static final String COLUMN_STATUS = "Status";

    public TableTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    //Search Table
    public String[] searchTableData(String strTableId) {

        try {
            String strResult[] = null;
            Cursor objCursor = readDatabase.query(TABLE_TO,
                    new String[] {COLUMN_ID_TO, COLUMN_STATUS},
                    COLUMN_ID_TO + "=?",
                    new String[] {String.valueOf(strTableId)},
                    null, null, null, null);

            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    strResult = new String[objCursor.getColumnCount()];
                    strResult[0] = objCursor.getString(0);
                    strResult[1] = objCursor.getString(1);
                }
            }
            objCursor.close();
            return strResult;

        } catch (Exception e) {
            return null;
        }
        //return new String[0];
    }

    public long addValueToTable(String strTableId, String strStatus) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_TO, strTableId);
        objContentValues.put(COLUMN_STATUS, strStatus);

        return writeDatabase.insert(TABLE_TO, null, objContentValues);
    }
}
