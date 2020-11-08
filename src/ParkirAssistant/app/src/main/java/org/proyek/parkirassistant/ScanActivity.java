package org.proyek.parkirassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.proyek.parkirassistant.util.SharedPrefManager;

public class ScanActivity extends AppCompatActivity {

    SharedPrefManager shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        shared = new SharedPrefManager(getApplicationContext());



    }

    public void onClickBack(View view) {
        Intent i = new Intent(this,HomeScreen.class);
        startActivity(i);
        finish();
    }
}