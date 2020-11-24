package org.proyek.parkirassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.proyek.parkirassistant.customScanPaint.CustomViewFinderView;

import java.util.Objects;

public class ScanActivity extends AppCompatActivity implements InputParkirDialog.InputParkirDialogListener {

    int idPelanggan = 0;
    int idBooking = 0;
    int idParkir = 0;
    long jamEntry = 0;
    long jamCheckout = 0;

    int totalBiaya = 0;

    int hargaPerjam = 2000;
    boolean hitung = true;


    private CountDownTimer mCountDownTimer = null;

    private ZXingScannerView mScannerView;
    private boolean isCaptured = false;
    SharedPrefManager shared;

    String hasil = "";
    boolean booking = false;
    String noParkir = "";
    String scan = "";

    FrameLayout frameLayoutCamera;

    Button ulangButton, inputParkirButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);


        shared = new SharedPrefManager(getApplicationContext());
        frameLayoutCamera = (FrameLayout) findViewById(R.id.frame_layout_camera_scan);
        ulangButton = (Button) findViewById(R.id.ulang_button);
        inputParkirButton = (Button) findViewById(R.id.input_nomor_parkir_button_dialog);

        initScannerView();

        ulangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.GONE);
                inputParkirButton.setVisibility(View.GONE);
                mScannerView.resumeCameraPreview(new ZXingScannerView.ResultHandler() {
                    @Override
                    public void handleResult(Result result) {
                        hasil = result.getText().trim();
                        ulangButton.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), result.getText().toString(), Toast.LENGTH_SHORT).show();

                        entryData(String.valueOf(result.getText()).trim());

                    }
                });
            }
        });


    }

    private void initScannerView() {
        mScannerView = new ZXingScannerView(getApplicationContext()) {
            @Override
            public IViewFinder createViewFinderView(Context context) {
                return new CustomViewFinderView(context);
            }
        };
        mScannerView.setAutoFocus(true);
        mScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {
                hasil = result.getText().trim();
                Toast.makeText(getApplicationContext(), result.getText().trim(), Toast.LENGTH_SHORT).show();

                ulangButton.setVisibility(View.VISIBLE);
                entryData(String.valueOf(result.getText()).trim());

            }


        });
        frameLayoutCamera.addView(mScannerView);
    }

    @Override
    protected void onStart() {
        mScannerView.startCamera();
        doRequestPermission();
        super.onStart();
    }

    private void doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 100:
                initScannerView();
                break;
            default:
                break;
        }

    }


    @Override
    protected void onPause() {
        mScannerView.stopCamera();
        super.onPause();
    }

    public void onClickBack(View view) {
        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);
        finish();
    }


    public void entryData(String hurufAcak) {
        AndroidNetworking.post(DBContract.SERVER_CEK_ENTRY_URL)
                .addBodyParameter("huruf_acak", hurufAcak)
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.MEDIUM)
                .setTag("Test Cek Entry")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean status = response.getBoolean("status");
                            if (status) {
                                scan = response.getString("scan");

                                if (scan.trim().equals("check in")) {
                                    cekBookingPelanggan(String.valueOf(hasil));
                                } else if (scan.equals("check out")) {
                                    checkoutPelanggan();
                                }

                                Toast.makeText(getApplicationContext(), scan + " cek entry data", Toast.LENGTH_SHORT).show();
                            } else {
                                String message = response.getString("message");
                                Toast.makeText(getApplicationContext(), message + " cek entry data", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage() + " json exception cek entry data", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), anError.getErrorDetail() + "\n" + anError.getMessage() + " ANError cek entry data", Toast.LENGTH_LONG).show();
                        Log.d("CekEntryData", anError.getErrorDetail());
                        Log.d("CekEntryData", anError.getErrorBody());
                        Log.d("CekEntryData", Objects.requireNonNull(anError.getMessage()));
                        Log.d("CekEntryData", String.valueOf(anError.getErrorCode()));
                    }
                });
    }

    private void checkoutPelanggan() {
        AndroidNetworking.post(DBContract.SERVER_CHECKOUT_ENTRY_URL)
                .addBodyParameter("huruf_acak", String.valueOf(hasil))
                .addBodyParameter("jam_checkout", String.valueOf(System.currentTimeMillis()))
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .setTag("Test Checkout Entry")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean status = response.getBoolean("status");

                            if (status) {
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(i);

                                    hargaPerjam = data.getInt("harga_perjam");
                                    idPelanggan = data.getInt("id_pelanggan");
                                    idBooking = data.getInt("id_booking");
                                    idParkir = data.getInt("id_parkir");
                                    jamEntry = data.getLong("jam_entry");
                                }
                                hitung = false;
                                calculateHasil(hargaPerjam, hitung, idPelanggan, idBooking, idParkir, jamEntry);
                            } else {
                                String message = response.getString("message");
                                Toast.makeText(getApplicationContext(), message + " checkout pelanggan", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage() + " jsonexception checkout pelanggan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), anError.getErrorDetail() + " ANError  checkout pelanggan", Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void cekBookingPelanggan(String hurufAcak) {
        AndroidNetworking.post(DBContract.SERVER_CEK_BOOKING_PELANGGAN_URL)
                .addBodyParameter("huruf_acak", hurufAcak)
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .setTag("Test Input Entry")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            boolean status = response.getBoolean("status");
//                            String message = response.getString("message");
                            booking = response.getBoolean("booking");
                            if (booking) {
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(i);

                                    noParkir = data.getString("no_parkir");
                                }

                                inputEntryData(noParkir);
                            } else if (!booking) {

                                inputParkirButton.setVisibility(View.VISIBLE);
                                inputParkirButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        InputParkirDialog inputParkirDialog = new InputParkirDialog();
                                        inputParkirDialog.show(getSupportFragmentManager(), "Input Parkir Dialog");
                                    }
                                });
                                inputEntryData(noParkir);
                            } else {
                                Toast.makeText(getApplicationContext(), "Some mistake in booking check", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getApplicationContext(), "Selamat Datang", Toast.LENGTH_LONG).show();

                            Toast.makeText(getApplicationContext(), booking + " " + noParkir + " cek booking", Toast.LENGTH_SHORT).show();

                        } catch (
                                JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage() + " cek booking", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), anError.getErrorDetail() + " cek booking", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void inputEntryData(String noParkir) {

        jamEntry = System.currentTimeMillis();

        if (noParkir.equals("")) {
            AndroidNetworking.post(DBContract.SERVER_INPUT_BOOKING_ENTRY_URL)
//                    .addBodyParameter("no_parkir",noParkir)
                    .addBodyParameter("huruf_acak", hasil)
                    .addBodyParameter("jam_entry", String.valueOf(jamEntry))
//                .addBodyParameter("jam_checkout", String.valueOf(System.currentTimeMillis()))
                    .addBodyParameter("harga_perjam", String.valueOf(hargaPerjam))
                    .addHeaders("Content-Type", "application/json")
                    .setPriority(Priority.HIGH)
                    .setTag("Test Input Entry")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean status = response.getBoolean("status");
                                if (status) {
                                    JSONArray jsonArray = response.getJSONArray("data");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject data = jsonArray.getJSONObject(i);

                                        hargaPerjam = data.getInt("harga_perjam");
                                        idPelanggan = data.getInt("id_pelanggan");
                                        idBooking = data.getInt("id_booking");
                                        idParkir = data.getInt("id_parkir");
                                        jamEntry = data.getLong("jam_entry");
                                    }
                                    calculateHasil(hargaPerjam, hitung, idPelanggan, idBooking, idParkir, jamEntry);
                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(getApplicationContext(), message + " input entry", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage() + " input entry", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(), anError.getErrorDetail() + " input entry", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {

            AndroidNetworking.post(DBContract.SERVER_INPUT_NO_BOOKING_ENTRY_URL)
                    .addHeaders("Content-Type", "application/json")
                    .addBodyParameter("no_parkir", noParkir)
                    .addBodyParameter("huruf_acak", hasil)
                    .addBodyParameter("jam_entry", String.valueOf(jamEntry))
//                    .addBodyParameter("jam_checkout", String.valueOf(System.currentTimeMillis()))
                    .addBodyParameter("harga_perjam", String.valueOf(hargaPerjam))
                    .setPriority(Priority.HIGH)
                    .setTag("Test Input Entry")
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean status = response.getBoolean("status");
                                if (status) {
                                    JSONArray jsonArray = response.getJSONArray("data");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject data = jsonArray.getJSONObject(i);

                                        hargaPerjam = data.getInt("harga_perjam");
                                        idPelanggan = data.getInt("id_pelanggan");
                                        idBooking = data.getInt("id_booking");
                                        idParkir = data.getInt("id_parkir");
                                        jamEntry = data.getLong("jam_entry");
                                    }
                                    calculateHasil(hargaPerjam, hitung, idPelanggan, idBooking, idParkir, jamEntry);
                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(getApplicationContext(), message + " input entry", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage() + " input entry", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(getApplicationContext(), anError.getErrorDetail() + " input entry", Toast.LENGTH_LONG).show();
                        }
                    });
        }

    }

    public void calculateHasil(int hargaPerJam, boolean hitung, int idPelanggan, int idBooking, int idParkir, long jamEntry) {
        int i = 1;

        int entry = (int) (jamEntry / 1000L);
        jamCheckout = (int) (System.currentTimeMillis() / 1000L);

        while (hitung) {
            if ((jamCheckout - entry) % 3600 == 0) {
                int selisih = (int) (jamCheckout - entry);
                totalBiaya = hargaPerJam * selisih;
            }
        }
//        transaksiPelanggan();

    }

    private void transaksiPelanggan() {
        AndroidNetworking.post(DBContract.SERVER_INPUT_TRANSAKSI_URL)
                .addHeaders("Content-Type", "application/json")
//                .addBodyParameter("no_parkir", noParkir)
                .addBodyParameter("huruf_acak", hasil)
                .addBodyParameter("jam_entry", String.valueOf(System.currentTimeMillis()))
                .addBodyParameter("jam_checkout", String.valueOf(System.currentTimeMillis()))
                .addBodyParameter("harga_perjam", String.valueOf(hargaPerjam))
                .setPriority(Priority.HIGH)
                .setTag("Test Input Entry")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean status = response.getBoolean("status");
                            if (status) {
                                JSONArray jsonArray = response.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(i);

                                    hargaPerjam = data.getInt("harga_perjam");
                                    idPelanggan = data.getInt("id_pelanggan");
                                    idBooking = data.getInt("id_booking");
                                    idParkir = data.getInt("id_parkir");
                                    jamEntry = data.getLong("jam_entry");
                                }
                                calculateHasil(hargaPerjam, hitung, idPelanggan, idBooking, idParkir, jamEntry);
                            } else {
                                String message = response.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), anError.getErrorDetail(), Toast.LENGTH_LONG).show();
                    }
                });
    }


    @Override
    public void ApplyTexts(String inputParkir) {
        noParkir = inputParkir;
    }
}