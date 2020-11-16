package org.proyek.parkirassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.proyek.parkirassistant.adapter.RiwayatTransaksiAdapter;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {


    String[] tanggal;
    boolean status = false;
    int[] total;
    int[] idTransaksi;

    TextView profileName;
    TextView noIdentitasProfile;
    TextView logoutButton;
    TextView belumTransaksi;

    ImageView middleButton;
    ImageView qrCode;

    ScrollView scrollView;
    LinearLayout linearLayout;

    SharedPrefManager shared;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        profileName = (TextView) findViewById(R.id.prof_name_profile);
        noIdentitasProfile = (TextView) findViewById(R.id.no_identitas_profile);
        logoutButton = (TextView) findViewById(R.id.logout_profile);
        belumTransaksi = (TextView) findViewById(R.id.belum_transaksi_text);

        qrCode = (ImageView) findViewById(R.id.QRCode);
        middleButton = (ImageView) findViewById(R.id.middle_icon_button_profile);

        scrollView = (ScrollView) findViewById(R.id.scroll_layout_profile);
        linearLayout = (LinearLayout) findViewById(R.id.before_login_layout_profile);
        lv = (ListView) findViewById(R.id.list_riwayat_transaksi);

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

                String text = shared.getSPHurufAcak(); // Whatever you need to encode in the QR code
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
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

        getRiwayatTransaksi();


    }

    private void getRiwayatTransaksi() {
        if (checkNetworkConnection()) {
            AndroidNetworking.post(DBContract.SERVER_RIWAYAT_TRANSAKSI_URL)
                    .addBodyParameter("id_pelanggan", String.valueOf(shared.getSPIdPengguna()))
                    .addHeaders("Content-Type", "application/json")
                    .setTag("test booked")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                status = response.getBoolean("status");

                                if (status) {
                                    JSONArray jsonArray = response.getJSONArray("data");

                                    tanggal = new String[jsonArray.length()];
                                    total = new int[jsonArray.length()];
                                    idTransaksi = new int[jsonArray.length()];

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject data = jsonArray.getJSONObject(i);

                                        idTransaksi[i] = data.getInt("id_transaksi");
                                        tanggal[i] = data.getString("tanggal");
                                        total[i] = data.getInt("total");
                                    }

                                    lv.setVisibility(View.VISIBLE);
                                    belumTransaksi.setVisibility(View.GONE);
                                    lv.setAdapter(new RiwayatTransaksiAdapter(ProfileActivity.this, tanggal, total));


                                } else {
                                    lv.setVisibility(View.GONE);
                                    belumTransaksi.setVisibility(View.VISIBLE);


                                    String message = response.getString("message");
                                    Log.d("email", message);
                                    Log.d("password", message);
//                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                Log.d("localizedMessage", Objects.requireNonNull(e.getLocalizedMessage()));
                                Log.d("message", Objects.requireNonNull(e.getMessage()));
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            if (anError.getErrorCode() != 0) {
                                // received error from server
                                // error.getErrorCode() - the error code from server
                                // error.getErrorBody() - the error body from server
                                // error.getErrorDetail() - just an error detail
                                Toast.makeText(getApplicationContext(),
                                        "onError errorCode : " + anError.getErrorCode() + "\n"
                                                + "onError errorBody : " + anError.getErrorBody() + "\n"
                                                + "onError errorDetail : " + anError.getErrorDetail() + "\n"
                                        , Toast.LENGTH_SHORT).show();
                                Log.d("errorCode", "onError errorCode : " + anError.getErrorCode());
                                Log.d("errorBody", "onError errorBody : " + anError.getErrorBody());
                                Log.d("errorDetail", "onError errorDetail : " + anError.getErrorDetail());
                                // get parsed error object (If ApiError is your class)
//                                ApiError apiError = error.getErrorAsObject(ApiError.class);
                            } else {
                                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                                Log.d("errorDetail", "onError errorDetail : " + anError.getErrorDetail());
                                Toast.makeText(getApplicationContext(), anError.getErrorDetail(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
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

    public boolean checkNetworkConnection() {


        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            Network nw = connectivityManager.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
            return (actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH)));
        } else {
            NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
            return (nwInfo != null && nwInfo.isConnected());
        }

    }
}