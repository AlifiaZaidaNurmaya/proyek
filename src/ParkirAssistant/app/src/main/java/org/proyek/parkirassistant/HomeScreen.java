package org.proyek.parkirassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeScreen extends AppCompatActivity {

    ImageView bookingIcon;
    ImageView homeIcon;
    ImageView profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        bookingIcon = (ImageView) findViewById(R.id.booking_icon);
        homeIcon = (ImageView) findViewById(R.id.home_icon);
        profileIcon = (ImageView) findViewById(R.id.profile_icon);

        bookingIcon.setColorFilter(R.color.colorOption);
        homeIcon.setColorFilter(Color.WHITE);
        profileIcon.setColorFilter(R.color.colorOption);
    }

    public void onClickLogin(View view) {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickEWallet(View view) {
        Intent i = new Intent(this,EWalletActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickBooking(View view) {
        bookingIcon.setColorFilter(Color.WHITE);
        homeIcon.setColorFilter(R.color.colorOption);

        Intent i = new Intent(this,BookingActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickSignUp(View view) {
        Intent i = new Intent(this,SignUpActivity.class);
        startActivity(i);
        finish();
    }
}