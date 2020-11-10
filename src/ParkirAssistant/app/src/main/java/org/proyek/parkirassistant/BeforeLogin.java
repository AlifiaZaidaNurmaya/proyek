package org.proyek.parkirassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BeforeLogin extends AppCompatActivity {

    SharedPrefManager shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);

        shared = new SharedPrefManager(getApplicationContext());



    }

    public void onClickHome(View view) {
        Intent intentHome = new Intent(getApplicationContext(),HomeScreen.class);
        startActivity(intentHome);
        finish();
    }

}