package project.jakkit.restaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import android.app.ProgressDialog;
import android.widget.Toast;

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
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 * Created by KHAMMA on 04/09/2017.
 */

public class ListOrderActivity extends ActionBarActivity {
    private ListView ListOrder;

    private ShowOrderTABLE objShowOrderTABLE;
    private FoodTABLE objFoodTABLE;
    private TextView txtShowTable, txtShowOfficer;
    private String strTable, strFoodID, strNameFood, strHotLevel, strAmount, strPriceFood,
            strOpenID,strDefaultSttSend = "1",strOfficer, strUserID, strDate, strTotal;

    ConnectionClass connectionClass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        objShowOrderTABLE = new ShowOrderTABLE(this);
        objFoodTABLE = new FoodTABLE(this);

        connectionClass = new ConnectionClass();
        progressDialog = new ProgressDialog(this);

        clearAllOrder();
        synJSONListOrder();

        bindWidget();
        showTable();
        showOfficer();
        getOfficerID();
        getDate();
        createListView1();

        synJSONTotalPrice();
    }

    private void bindWidget() {
        txtShowTable = (TextView) findViewById(R.id.txtShowTable);
        ListOrder = (ListView)findViewById(R.id.listOrder);
        txtShowOfficer = (TextView) findViewById(R.id.txtShowOfficer);
    }
    private void clearAllOrder() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("restaurantV4.db", MODE_PRIVATE, null);
        objSqLiteDatabase.delete("showoTABLE",null, null);
    }
    private void showOfficer() {
        strOfficer = getIntent().getExtras().getString("Officer");
        txtShowOfficer.setText(strOfficer);
    }
    private void getOfficerID() {
        strUserID = getIntent().getExtras().getString("IDofficer");
    }
    private void getDate() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                strDate = sdf.format(date);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }
    private void showTable() {
        strTable = getIntent().getExtras().getString("Table");
        txtShowTable.setText(strTable);
    }

    private void createListView1() {
        final String[] strListOpenID = objShowOrderTABLE.readAllShowOpenID();
        final String[] strListFoodID = objShowOrderTABLE.readAllShowFoodID();
        final String[] strListFood = objShowOrderTABLE.readAllShowNameFood();
        final String[] strListHot = objShowOrderTABLE.readAllShowHot();
        final String[] strListAmount = objShowOrderTABLE.readAllShowAmount();
        final String[] strListPrice = objShowOrderTABLE.readAllShowPrice();


        AdapterListOrder objMyAdapter = new AdapterListOrder(ListOrderActivity.this,strListOpenID,strListFoodID,strListFood,strListHot,strListAmount,strListPrice );
        ListOrder.setAdapter(objMyAdapter);

        //Click Active
        ListOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strOpenID = strListOpenID[position];
                strFoodID = strListFoodID[position];
                strNameFood = strListFood[position];
                strHotLevel = strListHot[position];
                strAmount = strListAmount[position];
                strPriceFood = strListPrice[position];

 //               deleteOrder();
            }   // event
        });
    }
    private void deleteOrder() {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.danger);
        objBuilder.setTitle("ยกเลิก Order");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onPreExecute();
 //               deleteOrderToMySQL();
                dialog.dismiss();
            }
        });
        objBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objBuilder.show();
    }
    public String deleteOrderToMySQL() {
        String z="";
        boolean isSuccess=false;

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check internet connection";
            } else {

                String query = "DELETE FROM data_order WHERE order_openTable = '"+ strOpenID +"' ";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);

                Toast.makeText(getApplicationContext(), "บันทึกเรียบร้อย", Toast.LENGTH_SHORT).show();

                z = "insert into successfull";
                isSuccess = true;
            }
        } catch (Exception ex) {
            isSuccess = false;
            z = "Exceptions" + ex;
        }
        return z;
    }
    protected void onPreExecute() {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }
    /*               - String to Int
                    String aString = “78”;
                    int aInt = Integer.parseInt(aString);

                     - Int To String
                    int aInt = 1;
                    String aString = Integer.toString(aInt);
    */
    public void synJSONListOrder() {
            if (Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy objPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(objPolicy);
            }
            strTable = getIntent().getExtras().getString("Table");
            //Create InputStream
            InputStream objInputStream = null;
            String strJSON = "";
            try {
                HttpClient objHttpClient = new DefaultHttpClient();
                HttpPost objHttpPost = new HttpPost("http://192.168.1.90/join_order.php");
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();

            } catch (Exception e) {
                Log.d("oic", "InputStream ==> " + e.toString());
            }//Create strJSON
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
            }//UpData SQLite
            try {
                final JSONArray objJsonArray = new JSONArray(strJSON);
                for (int i = 0; i < objJsonArray.length(); i++) {
                    JSONObject objJSONObject = objJsonArray.getJSONObject(i);
                    String strOpenID = objJSONObject.getString("order_openTable");
                    String strTableID =objJSONObject.getString("table_id");
                    String strFoodID = objJSONObject.getString("food_id");
                    String strAmount = objJSONObject.getString("order_amount");
                    String strHotLevel = objJSONObject.getString("listO_hot");
                    Integer intAmount = Integer.parseInt(strAmount);
                    String strHotName;

                    if (strHotLevel.equals("1")){
                        strHotName = "น้อย";
                    }else if (strHotLevel.equals("2")){
                        strHotName = "ปานกลาง";
                    }else {
                        strHotName = "มาก";
                    }
 //                     Log.d("table", "TablePage ==> " + strTable);
 //                     Log.d("table", "TableJSON ==> " + strTableID);
                    if (strTableID.equals(strTable)){
                        String strSynFoodResult[] = objFoodTABLE.searchFood(strFoodID);
                        strFoodID = strSynFoodResult[0];
                        String strNameFood = strSynFoodResult[1];
                        String strPriceFood = strSynFoodResult[2];
                        Integer intPrice = Integer.parseInt(strPriceFood);
                        long AddValue = objShowOrderTABLE.addValueToShowOrder(strOpenID, strFoodID, strNameFood, strHotName, intAmount, intPrice);
                    }
                }
            } catch (Exception e) {
                Log.d("oic", "Update ==> " + e.toString());
            }
    }
    private void synJSONTotalPrice() {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy objPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(objPolicy);
        }
        strTable = getIntent().getExtras().getString("Table");
        //Create InputStream
        InputStream objInputStream = null;
        String strJSON = "";
        try {
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://192.168.1.90/join_sum_total.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

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
        }//UpData SQLite
        try {
            final JSONArray objJsonArray = new JSONArray(strJSON);
            for (int i = 0; i < objJsonArray.length(); i++) {
                JSONObject objJSONObject = objJsonArray.getJSONObject(i);
                String strTableID = objJSONObject.getString("table_id");
                String strTotal = objJSONObject.getString("total");
                if (strTableID.equals(strTable)){
                    TextView total = (TextView) findViewById(R.id.txtShowTotal);
                    total.setText(strTotal);
                }
            }
        } catch (Exception e) {
            Log.d("oic", "Update ==> " + e.toString());
        }
    }
    public void clickOrder(View view){
        Intent intent = new Intent(ListOrderActivity.this, OrderActivity.class);
        intent.putExtra("Officer", strOfficer);
        intent.putExtra("IDofficer", strUserID);
        intent.putExtra("Table", strTable);
        startActivity(intent);
    }
    public void clickListSendOrder(View view){
        Intent intent = new Intent(ListOrderActivity.this, ListConfirmSendOrderActivity.class);
        intent.putExtra("Officer", strOfficer);
        intent.putExtra("IDofficer", strUserID);
        intent.putExtra("Table", strTable);
        startActivity(intent);
    }
    public void clickListConSendOrder(View view){
        Intent intent = new Intent(ListOrderActivity.this, ListSendOrderActivity.class);
        intent.putExtra("Officer", strOfficer);
        intent.putExtra("IDofficer", strUserID);
        intent.putExtra("Table", strTable);
        startActivity(intent);
    }
    public void clicklogout(View view){
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.danger);
        objBuilder.setTitle("คำเตือน !");
        objBuilder.setMessage("[" + strOfficer + "] คุณต้องการออกจากระบบร้านอาหาร");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent objIntent = new Intent(ListOrderActivity.this, MainActivity.class);
                startActivity(objIntent);
                dialog.dismiss();

                finish();
            }
        });
        objBuilder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        objBuilder.show();
    }
    public void clickhome(View view){
        Intent intent = new Intent(ListOrderActivity.this, IndexMain.class);
        intent.putExtra("Officer", strOfficer);
        intent.putExtra("IDofficer", strUserID);
        startActivity(intent);
    }
}
