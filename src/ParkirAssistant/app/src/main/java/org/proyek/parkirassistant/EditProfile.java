package org.proyek.parkirassistant;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class EditProfile extends AppCompatActivity {

    EditText input_nama, input_email, input_pw, input_no_id, no_telp, alamat1, no_plat;
    TextView btn_confirm;
    ProgressDialog progressDialog;

    SharedPrefManager shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        shared = new SharedPrefManager(getApplicationContext());

        AndroidNetworking.initialize(this);
//        id_pg = (EditText) findViewById(R.id.id_pg);
        input_nama = (EditText) findViewById(R.id.input_nama);
        input_email = (EditText) findViewById(R.id.input_email);
        input_pw = (EditText) findViewById(R.id.input_pw);
        input_no_id = (EditText) findViewById(R.id.input_no_id);
        no_telp = (EditText) findViewById(R.id.no_telp);
        alamat1 = (EditText) findViewById(R.id.alamat1);
        no_plat = (EditText) findViewById(R.id.no_plat);
        btn_confirm = (TextView) findViewById(R.id.btn_confirm);
        progressDialog = new ProgressDialog(EditProfile.this);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String id_pelanggan = id_pg.getText().toString();
                String nama = input_nama.getText().toString();
                String email = input_email.getText().toString();
                String password = input_pw.getText().toString();
                String no_identitas = input_no_id.getText().toString();
                String nomor_telepon = no_telp.getText().toString();
                String alamat = alamat1.getText().toString();
                String nomor_plat = no_plat.getText().toString();

                checkUpdate( nama, email, password, no_identitas, nomor_telepon, alamat, nomor_plat);
            }
        });
    }

    private void checkUpdate( String input_nama, String input_email, String input_pw, String input_no_id, String no_telp, String alamat1, String no_plat) {
        if (checkNetworkConnection()) {
            AndroidNetworking.post(DBContract.SERVER_UPDATE_URL)
                    .addBodyParameter("id_pelanggan", SharedPrefManager.SP_ID_PENGGUNA)
                    .addBodyParameter("nama", input_nama)
                    .addBodyParameter("email", input_email)
                    .addBodyParameter("password", input_pw)
                    .addBodyParameter("no_identitas", input_no_id)
                    .addBodyParameter("nomor_telepon", no_telp)
                    .addBodyParameter("alamat", alamat1)
                    .addBodyParameter("nomor_plat", no_plat)
                    .addBodyParameter("huruf_acak", "gdhd")
                    .addHeaders("Content-Type", "application/json")
                    .setTag("Update Data")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                boolean status = response.getBoolean("status");
                                String message = response.getString("message");
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


                                    Toast.makeText(getApplicationContext(), "Edit Profile Success", Toast.LENGTH_SHORT).show();
                                    Intent intenEdit = new Intent(getApplicationContext(), ProfileActivity.class);
                                    startActivity(intenEdit);
                                    finish();
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

    private boolean checkNetworkConnection () {

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

    public void onClickBack (View view){
        Intent intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intentProfile);
        finish();
    }

    public void onClickCancel (View view){
        Intent intentProfile = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intentProfile);
        finish();
    }
}