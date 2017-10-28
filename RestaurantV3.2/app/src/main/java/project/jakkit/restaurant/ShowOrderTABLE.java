package project.jakkit.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by AloneBOY on 11/09/2017.
 */

public class ShowOrderTABLE {
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;

    public static final String TABLE_SHOWO = "showoTABLE";
    public static final String COLUMN_ID_SFOODID = "_id";
    public static final String COLUMN_FOODID = "FoodID";
    public static final String COLUMN_FOOD = "NameFood";
    public static final String COLUMN_HOT = "Hot";
    public static final String COLUMN_AMOUNT = "Amount";
    public static final String COLUMN_PRICE = "Price";

    public ShowOrderTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();
    }   // Constructor

    public String[] searchOrder(String strFoodID) {
        try {
            String strResult[] = null;
            Cursor objCursor = readDatabase.query(TABLE_SHOWO,
                    new String[] {COLUMN_FOODID, COLUMN_HOT, COLUMN_AMOUNT, COLUMN_ID_SFOODID},
                    COLUMN_FOODID + "=?",
                    new String[] {String.valueOf(strFoodID)},
                    null, null, null, null);

            if (objCursor != null) {
                if (objCursor.moveToFirst()) {

                    strResult = new String[objCursor.getColumnCount()];
                    strResult[0] = objCursor.getString(0);
                    strResult[1] = objCursor.getString(1);
                    strResult[2] = objCursor.getString(2);
                    strResult[3] = objCursor.getString(3);
                }
            }
            objCursor.close();
            return strResult;
        } catch (Exception e) {
            return null;
        }
        //return new String[0];
    }

    public String[] readAllShowOpenID() {

        String strListOpenID[] = null;
        Cursor objCursor = readDatabase.query(TABLE_SHOWO, new String[]{COLUMN_ID_SFOODID, COLUMN_ID_SFOODID}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListOpenID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListOpenID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_SFOODID));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListOpenID;
    }
    public String[] readAllShowFoodID() {

        String strListFoodID[] = null;
        Cursor objCursor = readDatabase.query(TABLE_SHOWO, new String[]{COLUMN_ID_SFOODID, COLUMN_FOODID}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListFoodID = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListFoodID[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_FOODID));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListFoodID;
    }
    public String[] readAllShowNameFood() {

        String strListFood[] = null;
        Cursor objCursor = readDatabase.query(TABLE_SHOWO, new String[]{COLUMN_ID_SFOODID, COLUMN_FOOD}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListFood = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListFood[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_FOOD));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListFood;
    }
    public String[] readAllShowHot() {

        String strListHot[] = null;
        Cursor objCursor = readDatabase.query(TABLE_SHOWO, new String[]{COLUMN_ID_SFOODID, COLUMN_HOT}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListHot = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListHot[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_HOT));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListHot;
    }
    public String[] readAllShowAmount() {

        String strListAmount[] = null;
        Cursor objCursor = readDatabase.query(TABLE_SHOWO, new String[]{COLUMN_ID_SFOODID, COLUMN_AMOUNT}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListAmount = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListAmount[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_AMOUNT));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListAmount;
    }
    public String[] readAllShowPrice() {

        String strListPrice[] = null;
        Cursor objCursor = readDatabase.query(TABLE_SHOWO, new String[]{COLUMN_ID_SFOODID, COLUMN_PRICE}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListPrice = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListPrice[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_PRICE));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListPrice;
    }

    public long addValueToShowOrder(String strOpenID, String strFoodID, String strFood, String strHot, Integer intAmount, Integer intPrice) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_ID_SFOODID, strOpenID);
        objContentValues.put(COLUMN_FOODID,strFoodID);
        objContentValues.put(COLUMN_FOOD, strFood);
        objContentValues.put(COLUMN_HOT, strHot);
        objContentValues.put(COLUMN_AMOUNT, intAmount);
        objContentValues.put(COLUMN_PRICE, intPrice);
        return writeDatabase.insert(TABLE_SHOWO, null, objContentValues);
    }
}
