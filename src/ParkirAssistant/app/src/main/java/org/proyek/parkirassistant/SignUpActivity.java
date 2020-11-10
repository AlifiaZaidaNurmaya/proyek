package org.proyek.parkirassistant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText input_nama, input_email, input_pw, input_no_id, no_telp, alamat1, no_plat;
    TextView btn_signUp, login;
    ProgressDialog progressDialog;

    String randomCharacters = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        AndroidNetworking.initialize(this);
        input_nama = (EditText) findViewById(R.id.input_nama);
        input_email = (EditText) findViewById(R.id.input_email);
        input_pw = (EditText) findViewById(R.id.input_pw);
        input_no_id = (EditText) findViewById(R.id.input_no_id);
        no_telp = (EditText) findViewById(R.id.no_telp);
        alamat1 = (EditText) findViewById(R.id.alamat1);
        no_plat = (EditText) findViewById(R.id.no_plat);
        btn_signUp = (TextView) findViewById(R.id.btn_signUp);
        login = (TextView) findViewById(R.id.login);
        progressDialog = new ProgressDialog(SignUpActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = input_nama.getText().toString();
                String email = input_email.getText().toString();
                String password = input_pw.getText().toString();
                String no_identitas = input_no_id.getText().toString();
                String nomor_telepon = no_telp.getText().toString();
                String alamat = alamat1.getText().toString();
                String nomor_plat = no_plat.getText().toString();

                String hurufAcak = generateRandomCharacters();

                CheckSignup(nama, email, password, no_identitas, nomor_telepon, alamat, nomor_plat, hurufAcak);
            }
        });
    }

    private String generateRandomCharacters(){
        Random random = new Random();

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 4; i++) {
            char huruf = characters.charAt(random.nextInt(characters.length()));
            randomCharacters += huruf;
        }
        return randomCharacters;
    }

    private void CheckSignup(String input_nama, String input_email, String input_pw, String input_no_id, String no_telp, String alamat1, String no_plat, String hurufAcak) {
        if (checkNetworkConnection()) {
            AndroidNetworking.post(DBContract.SERVER_SIGNUP_URL)
                    .addBodyParameter("nama", input_nama)
                    .addBodyParameter("email", input_email)
                    .addBodyParameter("password", input_pw)
                    .addBodyParameter("no_identitas", input_no_id)
                    .addBodyParameter("nomor_telepon", no_telp)
                    .addBodyParameter("alamat", alamat1)
                    .addBodyParameter("nomor_plat", no_plat)
                    .addBodyParameter("huruf_acak", hurufAcak)
                    .addHeaders("Content-Type", "application/json")
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean status = response.getBoolean("status");
                                String message = response.getString("message");
                                if (status) {
                                    finish();
                                    Toast.makeText(getApplicationContext(), "Sign Up Success", Toast.LENGTH_SHORT).show();
                                    Intent intenLogin = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intenLogin);
                                } else {
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
                                Toast.makeText(getApplicationContext(),
                                        "onError errorCode : " + anError.getErrorCode() + "\n"
                                                + "onError errorBody : " + anError.getErrorBody() + "\n"
                                                + "onError errorDetail : " + anError.getErrorDetail() + "\n"
                                        , Toast.LENGTH_SHORT).show();
                                Log.d("errorCode", "onError errorCode : " + anError.getErrorCode());
                                Log.d("errorBody", "onError errorBody : " + anError.getErrorBody());
                                Log.d("errorDetail", "onError errorDetail : " + anError.getErrorDetail());
                            } else {
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

    public void onClickBack(View view) {
        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);
        finish();
    }

    public void onClickLogin(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}