package org.proyek.parkirassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EWalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_wallet);
    }

    public void onClickBack(View view) {
        Intent i = new Intent(this,HomeScreen.class);
        startActivity(i);
        finish();
    }
}