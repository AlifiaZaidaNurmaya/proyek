package org.proyek.parkirassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ProfileActivity extends AppCompatActivity {


    TextView profileName;
    TextView noIdentitasProfile;
    TextView logoutButton;

    ImageView middleButton;
    ImageView qrCode;

    ScrollView scrollView;
    LinearLayout linearLayout;

    SharedPrefManager shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        profileName = (TextView) findViewById(R.id.prof_name_profile);
        noIdentitasProfile = (TextView) findViewById(R.id.no_identitas_profile);
        logoutButton = (TextView) findViewById(R.id.logout_profile);

        qrCode = (ImageView) findViewById(R.id.QRCode);
        middleButton = (ImageView) findViewById(R.id.middle_icon_button_profile);

        scrollView = (ScrollView) findViewById(R.id.scroll_layout_profile);
        linearLayout = (LinearLayout) findViewById(R.id.before_login_layout_profile);

        shared = new SharedPrefManager(getApplicationContext());

        if (shared.getSPStatus()) {
            profileName.setText(shared.getSPNama());

            scrollView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);




            if (shared.getSPRole().equals("petugas")) {
                middleButton.setImageResource(R.drawable.ic_quick_response_code);
                noIdentitasProfile.setText("Petugas");

                qrCode.setVisibility(View.GONE);

                middleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), ScanActivity.class);
                        startActivity(i);
                        finish();
                    }
                });

            } else if (shared.getSPRole().equals("pelanggan")) {
                middleButton.setImageResource(R.drawable.ic_booking_icon);
                noIdentitasProfile.setText(String.valueOf(shared.getSPNoIdentitas()));

                String text= shared.getSPHurufAcak(); // Whatever you need to encode in the QR code
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    qrCode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }


                middleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), BookingActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }

            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shared.clearSPLogin();

                    Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                }
            });

        } else {
            scrollView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);

            middleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), BookingActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }


    }


    public void onClickHome(View view) {
        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);
        finish();
    }


    public void onClickEditProfile(View view) {
        Intent intentEdit = new Intent(getApplicationContext(), EditProfile.class);
        startActivity(intentEdit);
        finish();
    }
}