package project.jakkit.restaurant;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by KHAMMA on 03/09/2017.
 */

public class IndexMain extends ActionBarActivity {
    private TextView txtShowOfficer;
    private String strOfficer, strUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_main);

        bindWidget();
        showOfficer();
        showDate();
        getOfficerID();

    }

    private void getOfficerID() {
        strUserID = getIntent().getExtras().getString("IDofficer");
    }

    private void showOfficer() {
        strOfficer = getIntent().getExtras().getString("Officer");
        txtShowOfficer.setText(strOfficer);
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

    public void clickOrder(View view){
        Intent intent = new Intent(IndexMain.this, TableActivity.class);
        intent.putExtra("Officer", strOfficer);
        intent.putExtra("IDofficer", strUserID);
        startActivity(intent);
    }
    public void clickCook(View view){
        Intent intent = new Intent(IndexMain.this, ListCookActivity.class);
        intent.putExtra("Officer", strOfficer);
        intent.putExtra("IDofficer", strUserID);
        startActivity(intent);
    }
    public void clicklogout(View view){
        Intent intent = new Intent(IndexMain.this, MainActivity.class);
        startActivity(intent);
    }
}
