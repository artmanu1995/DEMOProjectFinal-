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
 * Created by KHAMMA on 07/09/2017.
 */

public class ListCookActivity extends ActionBarActivity {
    private TextView txtShowOfficer;
    private ListView ListCook;
    private OrderTABLE objOrderTABLE;
    private String strOfficer, strUserID, strDate, strTableID , strFoodID, strNameFood,
            strHotLevel, strAmount, strPriceFood,strOpenID;

    ConnectionClass connectionClass;
    ProgressDialog progressDialog;
    private AdapterListCook objMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_list);

        objOrderTABLE = new OrderTABLE(this);

        connectionClass = new ConnectionClass();
        progressDialog = new ProgressDialog(this);

        clearAllOrder();

        bindWidget();

        synJSONListOrder();
        showOfficer();
        getOfficerID();
        getDate();

        createListViewCook();
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
    private void synJSONListOrder() {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy objPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(objPolicy);
        }
        InputStream objInputStream = null;
        String strJSON = "";
        try {
            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://192.168.1.90/join_order_cook.php");
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
        }
        //UpData SQLite
        try {
            final JSONArray objJsonArray = new JSONArray(strJSON);
            for (int i = 0; i < objJsonArray.length(); i++) {
                JSONObject objJSONObject = objJsonArray.getJSONObject(i);
                String strOpenID = objJSONObject.getString("order_openTable");
                String strTableID =objJSONObject.getString("table_id");
                String strFoodName = objJSONObject.getString("food_name");
                String strAmount = objJSONObject.getString("order_amount");
                String strHotLevel = objJSONObject.getString("listO_hot");
                Integer intAmount = Integer.parseInt(strAmount);

                    long AddValue = objOrderTABLE.addValueToOrder(strOpenID, strTableID, strFoodName, strHotLevel, intAmount);
                }
            } catch (Exception e) {
            Log.d("oic", "Update ==> " + e.toString());
            }
    }

    private void createListViewCook() {
        final String[] strListOpenID = objOrderTABLE.readAllListID();
        final String[] strListTableID = objOrderTABLE.readAllListTableID();
        final String[] strListFood = objOrderTABLE.readAllListNameFood();
        final String[] strListHot = objOrderTABLE.readAllListHot();
        final String[] strListAmount = objOrderTABLE.readAllListAmount();

        AdapterListCook objMyAdapter = new AdapterListCook(ListCookActivity.this, strListOpenID, strListTableID, strListFood, strListHot, strListAmount);
        ListCook.setAdapter(objMyAdapter);

        //Click Active
        ListCook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strOpenID = strListOpenID[position];
                strTableID = strListTableID[position];
                strNameFood = strListFood[position];
                strHotLevel = strListHot[position];
                strAmount = strListAmount[position];

                setClickListView();;
            }   // event
        });
    }

    private void setClickListView() {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.food_order);
        objBuilder.setTitle("ยืนยันการส่ง Order");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onPreExecute();
                upDateOrderToMySQL();

                dialog.dismiss();
            }
        });
        objBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objBuilder.show();
    }
    public String upDateOrderToMySQL() {
        String z="";
        boolean isSuccess=false;

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check internet connection";
            } else {

                String query = "UPDATE data_order SET sttSO_id = '0' WHERE order_openTable = '"+ strOpenID +"' ";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);

                Toast.makeText(getApplicationContext(), "บันทึกเรียบร้อย", Toast.LENGTH_SHORT).show();

                clearAllOrder();
                synJSONListOrder();

                z = "insert into successfull";
                isSuccess = true;
            }
        } catch (Exception ex) {
            isSuccess = false;
            z = "Exceptions" + ex;
        }
        return z;
    }
    public void clickhome(View view){
        Intent intent = new Intent(ListCookActivity.this, IndexMain.class);
        intent.putExtra("Officer", strOfficer);
        intent.putExtra("IDofficer", strUserID);
        startActivity(intent);
    }
    public void ClickCO(View view) {
        Intent intent = new Intent(ListCookActivity.this, ListCookOutActivity.class);
        intent.putExtra("Officer", strOfficer);
        intent.putExtra("IDofficer", strUserID);
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
                Intent objIntent = new Intent(ListCookActivity.this, MainActivity.class);
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
    protected void onPreExecute() {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }
    private void bindWidget() {
        ListCook = (ListView)findViewById(R.id.listOrderCook);
        txtShowOfficer= (TextView)findViewById(R.id.txtShowOfficer);
    }
    private void clearAllOrder() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("restaurantV4.db", MODE_PRIVATE, null);
        objSqLiteDatabase.delete("orderTABLE",null, null);
    }

}
