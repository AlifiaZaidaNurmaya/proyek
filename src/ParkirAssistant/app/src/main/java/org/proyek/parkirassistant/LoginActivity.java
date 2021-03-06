package org.proyek.parkirassistant;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

/* Sebelum melakukan login, cek DBContract terlebih dahulu
* */

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail;
    EditText inputPassword;
    TextView btnLogin;
    TextView btnSignUp;

    SharedPrefManager shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        shared = new SharedPrefManager(getApplicationContext());

        AndroidNetworking.initialize(LoginActivity.this);

        inputEmail = (EditText) findViewById(R.id.input_email_Login);
        inputPassword = (EditText) findViewById(R.id.input_pw_Login);
        btnLogin = (TextView) findViewById(R.id.btn_login_Login);
        btnSignUp = (TextView) findViewById(R.id.btn_signUp_Login);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                checkLogin(email, password);
            }
        });

    }

    public void checkLogin(final String email, final String password) {
        if (checkNetworkConnection()) {
            AndroidNetworking.post(DBContract.SERVER_LOGIN_URL)
                    .addBodyParameter("email", email)
                    .addBodyParameter("password", password)
                    .addHeaders("Content-Type", "application/json")
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean status = response.getBoolean("status");
                                String role = response.getString("role");
                                int idPengguna = 0;
                                String em = "";
                                String nama = "";
                                String username = "";
                                String pass = "";
                                String plat = "";
                                String alamat = "";
                                String hurufAcak = "";
                                int noIdentitas = 0;
                                int noTelp = 0;

                                if (status) {
                                    JSONArray jsonArray = response.getJSONArray("data");
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject data = jsonArray.getJSONObject(i);

                                        if(role.equalsIgnoreCase("pelanggan")){
                                            idPengguna = data.getInt("id_pelanggan");
                                            em = data.getString("email");
                                            username = data.getString("username");
                                            pass = data.getString("password");
                                            nama = data.getString("nama");
                                            alamat = data.getString("alamat");
                                            plat = data.getString("nomor_plat");
                                            noIdentitas = data.getInt("no_identitas");
                                            noTelp = data.getInt("nomor_telepon");
                                            hurufAcak = data.getString("huruf_acak");
                                        }else if(role.equalsIgnoreCase("petugas")){
                                            idPengguna = data.getInt("id_petugas");
                                            em = data.getString("email");
                                            username = data.getString("username");
                                            pass = data.getString("password");
                                            nama = data.getString("nama");
                                            alamat = data.getString("alamat");
                                            noTelp = data.getInt("nomor_telepon");
                                        }
                                    }

                                    shared.saveSPBoolean(SharedPrefManager.SP_STATUS, status);
                                    shared.saveSPString(SharedPrefManager.SP_ROLE, role);

                                    shared.saveSPInt(SharedPrefManager.SP_ID_PENGGUNA, idPengguna);
                                    shared.saveSPString(SharedPrefManager.SP_EMAIL, em);
                                    shared.saveSPString(SharedPrefManager.SP_USERNAME, username);
                                    shared.saveSPString(SharedPrefManager.SP_PASSWORD, pass);
                                    shared.saveSPString(SharedPrefManager.SP_NAMA, nama);
                                    shared.saveSPString(SharedPrefManager.SP_ALAMAT, alamat);
                                    shared.saveSPString(SharedPrefManager.SP_HURUF_ACAK, hurufAcak);
                                    shared.saveSPString(SharedPrefManager.SP_PLAT_NOMOR, plat);
                                    shared.saveSPInt(SharedPrefManager.SP_NO_IDENTITAS, noIdentitas);
                                    shared.saveSPInt(SharedPrefManager.SP_NOMOR_TELEPON, noTelp);

                                    Toast.makeText(getApplicationContext(), "Login sukses",
                                            Toast.LENGTH_LONG).show();

                                    if(role.equalsIgnoreCase("pelanggan")){
                                        Toast.makeText(getApplicationContext(), "Selamat datang " + nama,
                                                Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Selamat datang petugas " + nama,
                                                Toast.LENGTH_LONG).show();
                                    }


                                    Intent intentHome = new Intent(getApplicationContext(), HomeScreen.class);
                                    startActivity(intentHome);

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


    public void onClickBack(View view) {
        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);
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