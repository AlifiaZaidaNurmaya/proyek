package org.proyek.parkirassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class BookingActivity extends AppCompatActivity {

    ImageView bookingIcon;
    ImageView homeIcon;
    ImageView profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        bookingIcon = (ImageView) findViewById(R.id.booking_icon);
        homeIcon = (ImageView) findViewById(R.id.home_icon);
        profileIcon = (ImageView) findViewById(R.id.profile_icon);

        bookingIcon.setColorFilter(Color.WHITE);
        homeIcon.setColorFilter(R.color.colorOption);
        profileIcon.setColorFilter(R.color.colorOption);

    }

    public void onClickHome(View view) {
        homeIcon.setColorFilter(Color.WHITE);
        bookingIcon.setColorFilter(Color.BLACK);

        Intent i = new Intent(this,HomeScreen.class);
        startActivity(i);
        finish();
    }
}