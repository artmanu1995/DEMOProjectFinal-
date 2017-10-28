package project.jakkit.restaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by KHAMMA on 07/04/2017.
 */

public class MainActivity extends ActionBarActivity {
//    public static final String keyServer = "http://192.168.1.38/connect_get_user.php";

    //Explicit
    private UserTABLE objUserTABLE;
    private FoodTABLE objFoodTABLE;
    private EditText edtUser, edtPassword;
    private String strUserChoose, strPasswordChoose, strPasswordTrue, strName, strUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Connected Database
        objUserTABLE = new UserTABLE(this);
        objFoodTABLE = new FoodTABLE(this);

        //Test Add New Value to SQLite
        //testAddValue();

        //Clear All Data
        clearAllData();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    }   // onCreate

    private void bindWidget() {
        edtUser = (EditText) findViewById(R.id.editText);
        edtPassword = (EditText) findViewById(R.id.editText2);
    }

    public void clickLogin(View view) {

        strUserChoose = edtUser.getText().toString().trim();
        strPasswordChoose = edtPassword.getText().toString().trim();

        if (strUserChoose.equals("") || strPasswordChoose.equals("") ) {

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this, "คำเเนะนำ !", "กรุณากรอก Username Password");

        } else {

            //Check User
            checkUser();

        }   // if

    }   // clickLogin

    private void checkUser() {

        try {

            String strMyResult[] = objUserTABLE.searchUser(strUserChoose);
            strPasswordTrue = strMyResult[2];
            strName = strMyResult[3];
            strUserID = strMyResult[0];

            Log.d("res", "Welcome ==> " + strName);

            //Check Password
            checkPassword();

        } catch (Exception e) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this, "Username ไม่ถูกต้อง !", "("+ strUserChoose +") Username นี้ไม่มีในฐานข้อมูล");
        }

    }

    private void checkPassword() {
        if (strPasswordChoose.equals(strPasswordTrue)) {

            welcome();

        } else {

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this, "Password ไม่ถูกต้อง !", "กรุณากรอก Password อีกครั้ง");

        }
    }

    private void welcome() {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.restaurant2);
        objBuilder.setTitle("ยินดีต้อนรับ");
        objBuilder.setMessage("[" + strName + "] เข้าสู่ระบบร้านอาหาร");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent objIntent = new Intent(MainActivity.this, IndexMain.class);
                objIntent.putExtra("Officer", strName);
                objIntent.putExtra("IDofficer", strUserID);
                startActivity(objIntent);
                dialog.dismiss();

                clearAllData();

                finish();
            }
        });
        objBuilder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edtUser.setText("");
                edtPassword.setText("");
                dialog.dismiss();
            }
        });
        objBuilder.show();

    }

    private void clearAllData() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("restaurantV4.db", MODE_PRIVATE, null);
        objSqLiteDatabase.delete("userTABLE", null, null);
        objSqLiteDatabase.delete("foodTABLE", null, null);
        objSqLiteDatabase.delete("toTABLE", null, null);
        objSqLiteDatabase.delete("orderTABLE", null, null);
        objSqLiteDatabase.delete("listoTABLE",null, null);
        objSqLiteDatabase.delete("showoTABLE",null, null);
        objSqLiteDatabase.delete("statustoTABLE", null, null);
    }

    private void synJSONtoSQLite() {

        /*ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("moreYear", "0"));*/

        //Setup New Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy objPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(objPolicy);
        }

        //Create InputStream
        InputStream objInputStream = null;
        String strJSON = "";

        try {
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://192.168.1.90/connect_get_user.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

            /*HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(keyServer);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            objInputStream = entity.getContent();*/

            /*ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                // Set to Http post
                // End set Value
            HttpClient httpclient = new DefaultHttpClient();
                // Set URL
            HttpPost httppost = new HttpPost("http://192.168.1.38/connect_get_user.php"); // https://10.0.2.2/php_get_data.php
                // End Set URL
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            objInputStream = entity.getContent();*/

        } catch (Exception e) {
            Log.d("oic", "InputStream ==> " + e.toString());
        }

        //Create strJSON
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = objBufferedReader.readLine()) != null) {
                objStringBuilder.append(strLine);
            }   // while

            objInputStream.close();
            strJSON = objStringBuilder.toString();


        } catch (Exception e) {
            Log.d("oic", "strJSON ==> " + e.toString());
        }

        //UpData SQLite
        try {

            final JSONArray objJsonArray = new JSONArray(strJSON);
            for (int i = 0; i < objJsonArray.length(); i++) {

                JSONObject objJSONObject = objJsonArray.getJSONObject(i);
                String strUserID = objJSONObject.getString("user_id");
                String strUser = objJSONObject.getString("user_username");
                String strPassword = objJSONObject.getString("user_password");
                String strName = objJSONObject.getString("user_name");

                long AddValue = objUserTABLE.addValueToUser(strUserID, strUser, strPassword, strName);

            }   // for

        } catch (Exception e) {
            Log.d("oic", "Update ==> " + e.toString());
        }
    }   // synJSONtoSQLite

    private void testAddValue() {
       // objUserTABLE.addValueToUser("User", "Pass", "Name");
       // objFoodTABLE.addValueToFood("Food", "Price");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
