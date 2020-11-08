package org.proyek.parkirassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.proyek.parkirassistant.util.SharedPrefManager;

import androidx.appcompat.app.AppCompatActivity;



public class BookingActivity extends AppCompatActivity {

    ImageView homeIcon;
    ImageView profileIcon;

    EditText inputNama;
    EditText inputNoIdentitas;
    EditText inputPlat;

    ScrollView scrollView;
    LinearLayout linearLayout;

    SharedPrefManager shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        homeIcon = (ImageView) findViewById(R.id.home_icon);
        profileIcon = (ImageView) findViewById(R.id.profile_icon);
        inputNama = (EditText) findViewById(R.id.input_nama_booking);
        inputNoIdentitas = (EditText) findViewById(R.id.input_identitas_booking);
        inputPlat = (EditText) findViewById(R.id.input_plat_booking);

        scrollView = (ScrollView) findViewById(R.id.scroll_layout_booking);
        linearLayout = (LinearLayout) findViewById(R.id.before_login_layout_booking);


        shared = new SharedPrefManager(getApplicationContext());

        if(shared.getSPStatus()){
            inputNama.setText(shared.getSPNama());
            inputNoIdentitas.setText(Integer.toString(shared.getSPNoIdentitas()));
            inputPlat.setText(shared.getSPNomorPlat());

            scrollView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);
        }else{
            scrollView.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);

        }

    }

    public void onClickHome(View view) {
        Intent i = new Intent(this,HomeScreen.class);
        startActivity(i);
        finish();
    }

    public void onClickProfil(View view) {
        Intent i = new Intent(this,Profil.class);
        startActivity(i);
        finish();
    }
}