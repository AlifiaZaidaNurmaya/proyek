package org.proyek.parkirassistant;

/*
* Untuk angka diantara http dan cobaparkir, kalian ganti sama IP Laptop kalian masing masing ya
* Cara cek IP Laptop -> buka cmd terus ketik "ipconfig"
* terus liaten bagian Wireless LAN adapter Wi-Fi, di situ ada Ipv4 Address, nah itu copas en ke angka di URL
* kalo masih error, coba cek di AndroidManifest.xml bagian android:usesCleartextTraffic pastiin nilainya true
* Pastiin juga laptop sama hp kalian tersambung di satu jaringan (WiFi / hotspot yang sama). Itu salah satu syarat biar bisa akses database
* XAMPP juga nyalain jangan lupa.*/

public class DBContract {
    public static final String SERVER_LOGIN_URL = "http://192.168.100.5/cobaparkir/api/Login_Api/auth";
    public static final String SERVER_SIGNUP_URL = "http://192.168.100.5/cobaparkir/api/Signup_Api/auth";
    public static final String SERVER_UPDATE_URL = "http://192.168.100.5/cobaparkir/api/Updateprofile_Api/auth";
}
