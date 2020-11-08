package org.proyek.parkirassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.proyek.parkirassistant.util.SharedPrefManager;

import java.util.Objects;

public class HomeScreen extends AppCompatActivity {

    ImageView homeIcon;
    ImageView profileIcon;
    TextView logoutBtn;
    TextView profileName;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;


    ConstraintLayout saldoHome;
    ImageView walletIcon;

    ImageView middleButton;


    SharedPrefManager shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        shared = new SharedPrefManager(getApplicationContext());

        homeIcon = (ImageView) findViewById(R.id.home_icon);
        profileIcon = (ImageView) findViewById(R.id.profile_icon);
        logoutBtn = (TextView) findViewById(R.id.logout_home);

        middleButton = (ImageView) findViewById(R.id.middle_icon_button_home);

        //      profile picture & name
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout2);
        linearLayout = (LinearLayout) findViewById(R.id.linear_login_signup_home);
        profileName = (TextView) findViewById(R.id.prof_name_home);

        walletIcon = (ImageView) findViewById(R.id.wallet_image_home);
        saldoHome = (ConstraintLayout) findViewById(R.id.saldo_home);

        if (shared.getSPStatus()) {
            logoutBtn.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);

            linearLayout.setVisibility(View.GONE);

            profileName.setText(shared.getSPNama());

            if (shared.getSPRole().equals("petugas")) {
                middleButton.setImageResource(R.drawable.ic_quick_response_code);

                walletIcon.setVisibility(View.GONE);
                saldoHome.setVisibility(View.GONE);

                middleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(),ScanActivity.class);
                        startActivity(i);
                        finish();
                    }
                });

            }else if(shared.getSPRole().equals("pelanggan")){
                middleButton.setImageResource(R.drawable.ic_booking_icon);

                walletIcon.setVisibility(View.VISIBLE);
                saldoHome.setVisibility(View.VISIBLE);

                middleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(),BookingActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }

            logoutBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // menghapus session
                    shared.clearSPLogin();

                    //menghilangkan tombol logout & profpic + profname
                    logoutBtn.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.GONE);

                    // memunculkan tombol login dan sign up
                    linearLayout.setVisibility(View.VISIBLE);

                    Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                }
            });

        } else {
            logoutBtn.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.GONE);

            linearLayout.setVisibility(View.VISIBLE);

            middleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(),BookingActivity.class);
                    startActivity(i);
                }
            });

        }


    }

    public void onClickLogin(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void onClickEWallet(View view) {
        Intent i = new Intent(this, EWalletActivity.class);
        startActivity(i);
        finish();
    }


    public void onClickSignUp(View view) {
        Intent i = new Intent(this, SignUpActivity.class);
        this.startActivity(i);
        finish();
    }

    public void onClickProfil(View view) {
        Intent i = new Intent(this, Profil.class);
        startActivity(i);
        finish();
    }

}