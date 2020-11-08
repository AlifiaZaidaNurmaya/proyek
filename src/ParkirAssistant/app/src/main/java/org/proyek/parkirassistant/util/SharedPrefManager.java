package org.proyek.parkirassistant.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    //    boolean status = response.getBoolean("status");
//    String em = "";
//    String nama = "";
//    String username = "";
//    String pass = "";
//    String plat = "";
//    String alamat = "";
//    String hurufAcak = "";
//    int noIdentitas = 0;
//    int noTelp = 0;
    public static final String SP_PARKIR_ASSISTANT = "spParkirAssistant";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_USERNAME = "spUsername";
    public static final String SP_NAMA = "spNama";
    public static final String SP_PASSWORD = "spPassword";
    public static final String SP_ALAMAT = "spAlamat";
    public static final String SP_PLAT_NOMOR = "spPlatNomor";
    public static final String SP_NO_IDENTITAS = "spNoIdentitas";
    public static final String SP_NOMOR_TELEPON = "spNomorTelepon";
    public static final String SP_HURUF_ACAK = "spHurufAcak";
    public static final String SP_ROLE = "spRole";

    public static final String SP_STATUS = "spStatus";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context) {
        this.sp = context.getSharedPreferences(SP_PARKIR_ASSISTANT, Context.MODE_PRIVATE);
        this.spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value) {
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNama() {
        return sp.getString(SP_NAMA, "");
    }

    public boolean getSPStatus() {
        return sp.getBoolean(SP_STATUS, false);
    }

    public String getSPEmail() {
        return sp.getString(SP_EMAIL, "");
    }

    public String getSPUsername() {
        return sp.getString(SP_USERNAME, "");
    }

    public String getSPPassword() {
        return sp.getString(SP_PASSWORD, "");
    }

    public String getSPAlamat() {
        return sp.getString(SP_ALAMAT, "");
    }

    public String getSPNomorPlat() {
        return sp.getString(SP_PLAT_NOMOR, "");
    }

    public int getSPNoIdentitas() {
        return sp.getInt(SP_NO_IDENTITAS, 0);
    }

    public int getSPNomorTelepon() {
        return sp.getInt(SP_NOMOR_TELEPON, 0);
    }

    public String getSPHurufAcak() {
        return sp.getString(SP_HURUF_ACAK, "");
    }
    public String getSPRole() {
        return sp.getString(SP_ROLE, "");
    }

    public void clearSPLogin(){
        spEditor.clear();
        spEditor.commit();
    }

}
