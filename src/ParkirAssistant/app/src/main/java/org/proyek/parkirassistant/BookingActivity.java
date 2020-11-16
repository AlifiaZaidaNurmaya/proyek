package org.proyek.parkirassistant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class BookingActivity extends AppCompatActivity {

    ImageView homeIcon;
    ImageView profileIcon;

    EditText inputNama;
    EditText inputNoIdentitas;
    EditText inputPlat;
    EditText inputNoParkir;

    Button submitBtn;

    Button[] box;

    ScrollView scrollView;
    LinearLayout linearLayout;

    boolean status = false;
    int []idParkir;
    String []noParkir;
    int []isBooked;

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
        inputNoParkir = (EditText) findViewById(R.id.input_nomor_parkir_booking);

        submitBtn = (Button) findViewById(R.id.submit_button_booking);

        scrollView = (ScrollView) findViewById(R.id.scroll_layout_booking);
        linearLayout = (LinearLayout) findViewById(R.id.before_login_layout_booking);

        idParkir = new int[20];
        isBooked = new int[20];
        noParkir = new String[20];

        box = new Button[20];
        box[0] = findViewById(R.id.parkir_1);
        box[1] = findViewById(R.id.parkir_2);
        box[2] = findViewById(R.id.parkir_3);
        box[3] = findViewById(R.id.parkir_4);
        box[4] = findViewById(R.id.parkir_5);
        box[5] = findViewById(R.id.parkir_6);
        box[6] = findViewById(R.id.parkir_7);
        box[7] = findViewById(R.id.parkir_8);
        box[8] = findViewById(R.id.parkir_9);
        box[9] = findViewById(R.id.parkir_10);
        box[10] = findViewById(R.id.parkir_11);
        box[11] = findViewById(R.id.parkir_12);
        box[12] = findViewById(R.id.parkir_13);
        box[13] = findViewById(R.id.parkir_14);
        box[14] = findViewById(R.id.parkir_15);
        box[15] = findViewById(R.id.parkir_16);
        box[16] = findViewById(R.id.parkir_17);
        box[17] = findViewById(R.id.parkir_18);
        box[18] = findViewById(R.id.parkir_19);
        box[19] = findViewById(R.id.parkir_20);

        // cek parkiran yang sudah dibooking (untuk ditampilkan dalam warna yang berbeda)
        checkBookedParking();


        shared = new SharedPrefManager(getApplicationContext());

        if(shared.getSPStatus()){
            inputNama.setText(shared.getSPNama());
            inputNoIdentitas.setText(String.valueOf(shared.getSPNoIdentitas()));
            inputPlat.setText(shared.getSPNomorPlat());

            scrollView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    insertBookingData();
                }
            });
        }else{
            scrollView.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);

        }

    }

    // input data booking
    private void insertBookingData() {
        if(checkNetworkConnection()){
            AndroidNetworking.post(DBContract.SERVER_INSERT_BOOKING_URL)
                    .addBodyParameter("id_pelanggan",SharedPrefManager.SP_ID_PENGGUNA)
                    .addBodyParameter("no_parkir",inputNoParkir.getText().toString())
                    .addBodyParameter("jam_booking",Long.toString(System.currentTimeMillis()))
                    .addHeaders("Content-Type","application/json")
                    .setTag("test input")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
        }
    }

    // mengecek apakah pada booking sudah ada datanya.
    private void checkBookedParking() {
        if(checkNetworkConnection()){
//            String idPelanggan = String.valueOf(shared.getSPIdPengguna());


            AndroidNetworking.post(DBContract.SERVER_BOOKING_URL)
//                    .addBodyParameter("email", email)
//                    .addBodyParameter("password", password)
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
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject data = jsonArray.getJSONObject(i);

                                        idParkir[i] = data.getInt("id_parkir");
                                        noParkir[i] = data.getString("no_parkir");
                                        isBooked[i] = data.getInt("is_booked");
                                        if((box[i].getText().toString()).equals(noParkir[i])){

                                            if(isBooked[i] == 0){
                                                box[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.not_booked_color));
                                            }else{
                                                box[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.booked_color));
                                            }
                                        }
                                    }

                                } else {
                                    String message = response.getString("message");
                                    Log.d("email", message);
                                    Log.d("password", message);
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
        }else{
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
        }
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

    public void onClickHome(View view) {
        Intent i = new Intent(this,HomeScreen.class);
        startActivity(i);
        finish();
    }

    public void onClickProfil(View view) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
        finish();
    }
}