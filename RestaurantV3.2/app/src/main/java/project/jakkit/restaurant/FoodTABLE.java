package project.jakkit.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 3/6/15 AD.
 */
public class FoodTABLE {

    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeDatabase, readDatabase;

    public static final String TABLE_FOOD = "foodTABLE";
    public static final String COLUMN_ID_FOOD = "_id";
    public static final String COLUMN_FOOD = "Food";
    public static final String COLUMN_PRICE = "Price";

    public FoodTABLE(Context context) {

        objMyOpenHelper = new MyOpenHelper(context);
        writeDatabase = objMyOpenHelper.getWritableDatabase();
        readDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    //Read All Price
    public String[] readAllPrice() {

        String strListPrice[] = null;
        Cursor objCursor = readDatabase.query(TABLE_FOOD, new String[]{COLUMN_ID_FOOD, COLUMN_PRICE}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListPrice = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListPrice[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_PRICE));
            objCursor.moveToNext();
        }   // for
        objCursor.close();
        return strListPrice;
    }

    //Read All Food
    public String[] readAllFood() {

        String strListFood[] = null;
        Cursor objCursor = readDatabase.query(TABLE_FOOD, new String[]{COLUMN_ID_FOOD, COLUMN_FOOD}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListFood = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListFood[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_FOOD));
            objCursor.moveToNext();
        }   // for

        objCursor.close();

        return strListFood;
    }
    public String[] readAllNumFood() {

        String strListFood[] = null;
        Cursor objCursor = readDatabase.query(TABLE_FOOD, new String[]{COLUMN_ID_FOOD, COLUMN_ID_FOOD}, null, null, null, null, null);
        objCursor.moveToFirst();
        strListFood = new String[objCursor.getCount()];
        for (int i = 0; i < objCursor.getCount(); i++) {
            strListFood[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_ID_FOOD));
            objCursor.moveToNext();
        }   // for

        objCursor.close();

        return strListFood;
    }

    public String[] searchFood(String strNumFood) {
        try {
            String strResult[] = null;
            Cursor objCursor = readDatabase.query(TABLE_FOOD,
                    new String[] {COLUMN_ID_FOOD, COLUMN_FOOD, COLUMN_PRICE},
                    COLUMN_ID_FOOD + "=?",
                    new String[] {String.valueOf(strNumFood)},
                    null, null, null, null);
            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    strResult = new String[objCursor.getColumnCount()];
                    strResult[0] = objCursor.getString(0);
                    strResult[1] = objCursor.getString(1);
                    strResult[2] = objCursor.getString(2);
                }
            }
            objCursor.close();
            return strResult;

        } catch (Exception e) {
            return null;
        }
        //return new String[0];
    }

    public long addValueToFood(String strFood, Integer intPrice, String strNumFood) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_FOOD, strFood);
        objContentValues.put(COLUMN_PRICE, intPrice);
        objContentValues.put(COLUMN_ID_FOOD, strNumFood);
        return writeDatabase.insert(TABLE_FOOD, null, objContentValues);
    }

}   // Main Class
