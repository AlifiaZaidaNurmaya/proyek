package org.proyek.parkirassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void onClickBack(View view) {
        Intent intentProfile = new Intent(getApplicationContext(),Profil.class);
        startActivity(intentProfile);
        finish();
    }
}