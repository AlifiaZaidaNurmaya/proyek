package org.proyek.parkirassistant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;


public class BookingActivity extends AppCompatActivity {

    private static final String TAG = "BroadcastReceiver";
//    private static final long START_TIME_IN_MILLIS = 900000;
//    private CountDownTimer mCountDownTimer;
//    private boolean mTimerRunning = false;

//    private long mTimeLeftInMillis;

//    private long mEndTime;


    ImageView homeIcon;
    ImageView bookingIcon;
    ImageView profileIcon;

    EditText inputNama;
    EditText inputNoIdentitas;
    EditText inputPlat;
    EditText inputNoParkir;

    TextView countdownText;

    Button submitBtn;

    Button[] box;

    ScrollView scrollView;
    LinearLayout linearLayout;

    boolean status = false;
    int[] idParkir;
    String[] noParkir;
    int[] isBooked;




    SharedPrefManager shared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        homeIcon = (ImageView) findViewById(R.id.home_icon_booking);
        bookingIcon = (ImageView) findViewById(R.id.booking_icon_booking);
        profileIcon = (ImageView) findViewById(R.id.profile_icon_booking);

        DrawableCompat.setTint(
                DrawableCompat.wrap(bookingIcon.getDrawable()),
                ContextCompat.getColor(this, android.R.color.white)
        );

        inputNama = (EditText) findViewById(R.id.input_nama_booking);
        inputNoIdentitas = (EditText) findViewById(R.id.input_identitas_booking);
        inputPlat = (EditText) findViewById(R.id.input_plat_booking);
        inputNoParkir = (EditText) findViewById(R.id.input_nomor_parkir_booking);

        countdownText = (TextView) findViewById(R.id.countdown_booking);

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

        if (shared.getSPStatus()) {
            inputNama.setText(shared.getSPNama());
            inputNoIdentitas.setText(String.valueOf(shared.getSPNoIdentitas()));
            inputPlat.setText(shared.getSPNomorPlat());

            scrollView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date now = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    String timeMySQLInput = formatter.format(now);
                    // input data booking
                    insertBookingData(timeMySQLInput);

                    startService(new Intent(BookingActivity.this, BroadcastCountdownService.class));
                    Log.i(TAG, "Started service");
                    updateParkir();

                    startActivity(getIntent());
//                    startTimer();


//                    new CountDownTimer(900000, 1000) {
//
//                        public void onTick(long millisUntilFinished) {
//                            countdownText.setText("Sisa waktu booking: " + millisUntilFinished / (1000 *60) + " menit");
//                        }
//
//                        public void onFinish() {
//                            countdownText.setText("Booking anda hangus!");
//                            deleteBookingData();
//                        }
//                    }.start();

                }
            });
        } else {
            scrollView.setVisibility(View.INVISIBLE);
            linearLayout.setVisibility(View.VISIBLE);

        }

    }

    private void updateParkir() {
        if (checkNetworkConnection()) {
            AndroidNetworking.post(DBContract.SERVER_UPDATE_PARKIR_BOOKING_URL)
//                    .addBodyParameter("id_pelanggan", String.valueOf(shared.getSPIdPengguna()))
                    .addBodyParameter("no_parkir", inputNoParkir.getText().toString())
//                    .addBodyParameter("jam_booking", )
                    .addHeaders("Content-Type", "application/json")
                    .setTag("test input")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean stts = response.getBoolean("status");
                                String msg = response.getString("message");
                                if (stts) {
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), "Segera tempati lokasi parkir anda dalam 15 menit", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(), "Gagal input data, error : " + anError.getMessage(), Toast.LENGTH_LONG).show();

                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
        }
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            shared.saveSPBoolean(SharedPrefManager.SP_IS_BOOKED,intent.getBooleanExtra("timeup",false));
            if(intent.getBooleanExtra("timeup", false)){
                long mTimeLeftInMillis = intent.getLongExtra("countdown", 0);
                int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                countdownText.setText("Sisa waktu booking: \n" + timeLeftFormatted);

//            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(),MODE_PRIVATE);
//            sharedPreferences.edit().putLong("mTimeLeftInMills",mTimeLeftInMillis).apply();

//            shared.saveSPLong(SharedPrefManager.SP_MILLIS_LEFT,mTimeLeftInMillis);

            }else{
                countdownText.setText("Waktu anda habis!");
                deleteBookingData();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastCountdownService.COUNTDOWN_BR));
        Log.i(TAG, "Registered broacast receiver");
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
        Log.i(TAG, "Unregistered broacast receiver");
    }

    @Override
    protected void onStop() {
        try {
            unregisterReceiver(br);
        } catch (Exception e) {
            Log.i(TAG, Objects.requireNonNull(e.getMessage()));
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, BroadcastCountdownService.class));
        Log.i(TAG, "Stopped service");
        super.onDestroy();
    }

    //    private void startTimer() {
//
//        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long l) {
//                mTimeLeftInMillis = l;
//                int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//                int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//
//                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//
//                countdownText.setText("Sisa waktu booking: \n" + timeLeftFormatted);
//            }
//
//            @Override
//            public void onFinish() {
//                countdownText.setText("Teeeett waktu habis!");
//                deleteBookingData();
//            }
//        }.start();
//    }

    private void deleteBookingData() {
        if (checkNetworkConnection()) {
            AndroidNetworking.post(DBContract.SERVER_DELETE_BOOKING_URL)
                    .addBodyParameter("id_pelanggan", String.valueOf(shared.getSPIdPengguna()))
//                    .addBodyParameter("no_parkir", inputNoParkir.getText().toString())
//                    .addBodyParameter("jam_booking", )
                    .addHeaders("Content-Type", "application/json")
                    .setTag("test input")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean stts = response.getBoolean("status");
                                String msg = response.getString("message");
                                if (stts) {

                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), "Segera tempati lokasi parkir anda dalam 15 menit", Toast.LENGTH_LONG).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(), "Gagal input data, error : " + anError.getMessage(), Toast.LENGTH_LONG).show();

                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
        }
    }

    // input data booking
    private void insertBookingData(String time) {
        if (checkNetworkConnection()) {
            AndroidNetworking.post(DBContract.SERVER_INSERT_BOOKING_URL)
                    .addBodyParameter("id_pelanggan", String.valueOf(shared.getSPIdPengguna()))
                    .addBodyParameter("no_parkir", inputNoParkir.getText().toString())
                    .addBodyParameter("jam_booking", time)
                    .addHeaders("Content-Type", "application/json")
                    .setTag("test input")
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean stts = response.getBoolean("status");
                                String msg = response.getString("message");
                                int idBooking = 0;
                                String noParkir = inputNoParkir.getText().toString();
                                if (stts) {
                                    JSONArray jsonArray = response.getJSONArray("data");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject data = jsonArray.getJSONObject(i);

                                        idBooking = data.getInt("id_booking");
                                    }

                                    shared.saveSPInt(SharedPrefManager.SP_ID_BOOKING, idBooking);
                                    shared.saveSPString(SharedPrefManager.SP_NO_PARKIR_BOOKING, noParkir);

                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), "Segera tempati lokasi parkir anda dalam 15 menit", Toast.LENGTH_LONG).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(), "Gagal input data, error : " + anError.getMessage(), Toast.LENGTH_LONG).show();

                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                            overridePendingTransition(0, 0);
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
        }
    }

    // mengecek apakah pada booking sudah ada datanya.
    private void checkBookedParking() {
        if (checkNetworkConnection()) {
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
                                        if ((box[i].getText().toString()).equals(noParkir[i])) {

                                            if (isBooked[i] == 0) {
                                                box[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.not_booked_color));
                                            } else {
                                                box[i].setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.booked_color));
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
        } else {
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
        DrawableCompat.setTint(
                DrawableCompat.wrap(bookingIcon.getDrawable()),
                ContextCompat.getColor(this, android.R.color.black)
        );

        Intent i = new Intent(this, HomeScreen.class);
        overridePendingTransition(0, 0);
        startActivity(i);
        overridePendingTransition(0, 0);
//        finish();
    }

    public void onClickProfil(View view) {
        DrawableCompat.setTint(
                DrawableCompat.wrap(bookingIcon.getDrawable()),
                ContextCompat.getColor(this, android.R.color.black)
        );

        Intent i = new Intent(this, ProfileActivity.class);
        overridePendingTransition(0, 0);
        startActivity(i);
        overridePendingTransition(0, 0);
//        finish();
    }
}