package project.jakkit.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by KHAMMA on 27/10/2017.
 */

public class StatusToTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;

    public static final String TABLE_STATUSTO = "statustoTABLE";
    public static final String COLUMN_ID_TO = "_id";
    public static final String COLUMN_STATUSOF = "StatusOF";

    public StatusToTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    //Search Table
    public String[] searchStutusTableData(String strTableId) {
        try {
            String strResult[] = null;
            Cursor objCursor = readDatabase.query(TABLE_STATUSTO,
                    new String[] {COLUMN_ID_TO, COLUMN_STATUSOF},
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

    public long addValueStatusToTable(String strTableId, String strStatusOF) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_TO, strTableId);
        objContentValues.put(COLUMN_STATUSOF, strStatusOF);

        return writeDatabase.insert(TABLE_STATUSTO, null, objContentValues);
    }
}
