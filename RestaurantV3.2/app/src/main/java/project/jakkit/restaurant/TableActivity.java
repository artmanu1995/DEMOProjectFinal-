package project.jakkit.restaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
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
 * Created by KHAMMA on 03/09/2017.
 */

public class TableActivity extends ActionBarActivity {
    private TableTABLE objTableTEBLE;
    private ShowOrderTABLE objShowOrderTABLE;
    private StatusToTABLE objStatusTableTABLE;

    private TextView txtShowOfficer;
    private String strOfficer, strTable, strUserID;
    private String stt_lock = "0", stt_Unlock = "1",stt_blank = "0", stt_noblank = "1", stt_table, stt_OFtable, strCheckOrderOffTable="0";

    ConnectionClass connectionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        connectionClass = new ConnectionClass();

        objShowOrderTABLE = new ShowOrderTABLE(this);

        objTableTEBLE = new TableTABLE(this);

        objStatusTableTABLE= new StatusToTABLE(this);

        //Clear All Data
        clearDataTo();
        bindWidget();
        showOfficer();
        getOfficerID();
        showDate();

        synJSONstatusTable();
        synJSONstatusOFTable();

        checkTable1();
        checkTable2();
        checkTable3();
        checkTable4();
        checkTable5();
        checkTable6();
        checkTable7();
        checkTable8();
        checkTable9();
        checkTable10();
        checkTable11();
        checkTable12();
        checkTable13();
        checkTable14();
        checkTable15();
        checkTable16();
    }

    private void checkTable1() {
        strTable = "1";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok = (Button) findViewById(R.id.button1);
        if (stt_lock.equals(stt_table)) {
            btn_ok.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable2() {
        strTable = "2";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok2 = (Button) findViewById(R.id.button2);
        if (stt_lock.equals(stt_table)) {
            btn_ok2.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok2.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable3() {
        strTable = "3";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok3 = (Button) findViewById(R.id.button3);
        if (stt_lock.equals(stt_table)) {
            btn_ok3.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok3.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable4() {
        strTable = "4";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok4 = (Button) findViewById(R.id.button4);
        if (stt_lock.equals(stt_table)) {
            btn_ok4.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok4.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok4.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable5() {
        strTable = "5";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] = objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok5 = (Button) findViewById(R.id.button5);
        if (stt_lock.equals(stt_table)) {
            btn_ok5.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)) {
            if (stt_blank.equals(stt_OFtable)) {
                btn_ok5.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            } else if (stt_noblank.equals(stt_OFtable)) {
                btn_ok5.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable6() {
        strTable = "6";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok6 = (Button) findViewById(R.id.button6);
        if (stt_lock.equals(stt_table)) {
            btn_ok6.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok6.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok6.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable7() {
        strTable = "7";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok7 = (Button) findViewById(R.id.button7);
        if (stt_lock.equals(stt_table)) {
            btn_ok7.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok7.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok7.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable8() {
        strTable = "8";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok8 = (Button) findViewById(R.id.button8);
        if (stt_lock.equals(stt_table)) {
            btn_ok8.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok8.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok8.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable9() {
        strTable = "9";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok9 = (Button) findViewById(R.id.button9);
        if (stt_lock.equals(stt_table)) {
            btn_ok9.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok9.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok9.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable10() {
        strTable = "10";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok10 = (Button) findViewById(R.id.button10);
        if (stt_lock.equals(stt_table)) {
            btn_ok10.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok10.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok10.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable11() {
        strTable = "11";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok11 = (Button) findViewById(R.id.button11);
        if (stt_lock.equals(stt_table)) {
            btn_ok11.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok11.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok11.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable12() {
        strTable = "12";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok12 = (Button) findViewById(R.id.button12);
        if (stt_lock.equals(stt_table)) {
            btn_ok12.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok12.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok12.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable13() {
        strTable = "13";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok13 = (Button) findViewById(R.id.button13);
        if (stt_lock.equals(stt_table)) {
            btn_ok13.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok13.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok13.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable14() {
        strTable = "14";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok14 = (Button) findViewById(R.id.button14);
        if (stt_lock.equals(stt_table)) {
            btn_ok14.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok14.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok14.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable15() {
        strTable = "15";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok15 = (Button) findViewById(R.id.button15);
        if (stt_lock.equals(stt_table)) {
            btn_ok15.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok15.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok15.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
    private void checkTable16() {
        strTable = "16";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        Button btn_ok16 = (Button) findViewById(R.id.button16);
        if (stt_lock.equals(stt_table)) {
            btn_ok16.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        } else if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                btn_ok16.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }else if (stt_noblank.equals(stt_OFtable)){
                btn_ok16.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    private void clearDataTo() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("restaurantV4.db", MODE_PRIVATE, null);
        objSqLiteDatabase.delete("toTABLE", null, null);
        objSqLiteDatabase.delete("statustoTABLE", null, null);
    }

    private void showOfficer() {
        strOfficer = getIntent().getExtras().getString("Officer");
        txtShowOfficer.setText(strOfficer);
    }
    private void getOfficerID() {
        strUserID = getIntent().getExtras().getString("IDofficer");
    }

    private void bindWidget() {
        txtShowOfficer = (TextView) findViewById(R.id.txtShowOfficer);
    }

    private void showDate() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView time = (TextView) findViewById(R.id.txtShowDateTime);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String dateString = sdf.format(date);
                                time.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    private void chooseOnTable() {
        CharSequence[] charItem = {"เปิดโต๊ะ (ON)"};

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.restaurant2);
        objBuilder.setTitle("เลือกสถานะโต๊ะ" + "[" + " โต๊ะ "+ strTable + "]");
        objBuilder.setCancelable(false);
        objBuilder.setSingleChoiceItems(charItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case 0:
                        stt_table = "1";
                        upDataTableToMySQL();
                        Intent intent = new Intent(TableActivity.this, OrderActivity.class);
                        intent.putExtra("Officer", strOfficer);
                        intent.putExtra("Table", strTable);
                        intent.putExtra("IDofficer", strUserID);
                        startActivity(intent);
                        break;
                }   // switch
                dialog.dismiss();
            }   // event
        });
        objBuilder.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog objAlertDialog = objBuilder.create();
        objAlertDialog.show();
    }

    private void chooseOffTable() {
        CharSequence[] charItem = {"รับ Order เพิ่ม", "ปิดโต๊ะ (OFF)"};

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.restaurant2);
        objBuilder.setTitle("เลือกสถานะโต๊ะ" + "[" + " โต๊ะ "+ strTable + "]");
        objBuilder.setCancelable(false);
        objBuilder.setSingleChoiceItems(charItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case 0:
                        stt_table = "1";
                        Intent intent = new Intent(TableActivity.this, OrderActivity.class);
                        intent.putExtra("Officer", strOfficer);
                        intent.putExtra("Table", strTable);
                        intent.putExtra("IDofficer", strUserID);
                        startActivity(intent);
                        break;
                    case 1:
                        stt_table = "0";
                        synJSONgetListOrder();
                        break;
                }   // switch
                dialog.dismiss();
            }   // event
        });
        objBuilder.setPositiveButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog objAlertDialog = objBuilder.create();
        objAlertDialog.show();
    }
    private void synJSONgetListOrder() {
        //Setup New Policy
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy objPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(objPolicy);
        }//Create InputStream
        InputStream objInputStream = null;
        String strJSON = "";
        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://192.168.1.90/count_list_order.php");
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
            for (int j = 0; j < objJsonArray.length(); j++) {
                JSONObject objJSONObject = objJsonArray.getJSONObject(j);
                String strListOrder = objJSONObject.getString("COUNT(*)");
                if (strCheckOrderOffTable.equals(strListOrder)){
                    upDataTableToMySQL();
                    Intent intento = new Intent(TableActivity.this, IndexMain.class);
                    intento.putExtra("Officer", strOfficer);
                    intento.putExtra("Table", strTable);
                    intento.putExtra("IDofficer", strUserID);
                    startActivity(intento);
                }else{
                    checkOrderOffTable();
                }

            }
        } catch (Exception e) {
            Log.d("oic", "Update ==> " + e.toString());
        }
    }
    private void checkOrderOffTable() {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy objPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(objPolicy);
        }//Create InputStream
        strTable = getIntent().getExtras().getString("Table");
        InputStream objInputStream = null;
        String strJSON = "";
        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://192.168.1.90/count_listorder_table.php");
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
            for (int j = 0; j < objJsonArray.length(); j++) {
                JSONObject objJSONObject = objJsonArray.getJSONObject(j);
                String strTableID = objJSONObject.getString("table_id");
                String strCountOrder = objJSONObject.getString("count");

                if (strTable.equals(strTableID)){
                    MyAlertDialog objMyAlertDialog = new MyAlertDialog();
                    objMyAlertDialog.myDialog(TableActivity.this, "คำเตือน !", "โต๊ะ [" + strTable + "] ยังมี Order ที่รอทำรายการอยู่");
                }else{
                    upDataTableToMySQL();
                    Intent intento = new Intent(TableActivity.this, IndexMain.class);
                    intento.putExtra("Officer", strOfficer);
                    intento.putExtra("Table", strTable);
                    intento.putExtra("IDofficer", strUserID);
                    startActivity(intento);
                }
            }
        } catch (Exception e) {
            Log.d("oic", "Update ==> " + e.toString());
        }
    }
    public String upDataTableToMySQL() {
        String z = "";
        boolean isSuccess = false;

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check internet connection";
            } else {
                String query = "UPDATE data_onofftable SET sttOFTable_id = '" + stt_table + "' WHERE table_id = '" + strTable + "'";

                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);

                clearDataTo();
                synJSONstatusTable();

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

    public void btn1Click(View view) {
        strTable = "1";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){
 //           Toast.makeText(getApplicationContext(), "โต๊ะไม่ได้เปิดใช้งาน", Toast.LENGTH_SHORT).show();
        }
    }
    public void btn2Click(View view) {
        strTable = "2";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn3Click(View view) {
        strTable = "3";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn4Click(View view) {
        strTable = "4";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn5Click(View view) {
        strTable = "5";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn6Click(View view) {
        strTable = "6";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn7Click(View view) {
        strTable = "7";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn8Click(View view) {
        strTable = "8";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn9Click(View view) {
        strTable = "9";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn10Click(View view) {
        strTable = "10";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn11Click(View view) {
        strTable = "11";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn12Click(View view) {
        strTable = "12";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn13Click(View view) {
        strTable = "13";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn14Click(View view) {
        strTable = "14";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn15Click(View view) {
        strTable = "15";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void btn16Click(View view) {
        strTable = "16";
        String strMyResult[] = objTableTEBLE.searchTableData(strTable);
        stt_table = strMyResult[1];
        String strResult[] =objStatusTableTABLE.searchStutusTableData(strTable);
        stt_OFtable = strResult[1];
        if (stt_Unlock.equals(stt_table)){
            if (stt_blank.equals(stt_OFtable)){
                chooseOnTable();
            }else if (stt_noblank.equals(stt_OFtable)){
                chooseOffTable();
            }
        } else if (stt_lock.equals(stt_table)){

        }
    }
    public void clicklogout(View view){
        Intent intent = new Intent(TableActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void synJSONstatusTable() {
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
            HttpPost objHttpPost = new HttpPost("http://192.168.1.90/get_data_table.php");
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

                String strTableId = objJSONObject.getString("table_id");
                String strStatus = objJSONObject.getString("sttTable_id");

                long addValue = objTableTEBLE.addValueToTable(strTableId, strStatus);
            }   // for

        } catch (Exception e) {
            Log.d("oic", "Update ==> " + e.toString());
        }
    }
    private void synJSONstatusOFTable() {
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
            HttpPost objHttpPost = new HttpPost("http://192.168.1.90/get_status_table.php");
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

                String strTableId = objJSONObject.getString("table_id");
                String strStatusOF = objJSONObject.getString("sttOFTable_id");

                long addValue = objStatusTableTABLE.addValueStatusToTable(strTableId, strStatusOF);
            }   // for

        } catch (Exception e) {
            Log.d("oic", "Update ==> " + e.toString());
        }
    }
}
