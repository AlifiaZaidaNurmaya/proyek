package org.proyek.parkirassistant;

/*
* Untuk angka diantara http dan cobaparkir, kalian ganti sama IP Laptop kalian masing masing ya
* Cara cek IP Laptop -> buka cmd terus ketik "ipconfig"
* terus liaten bagian Wireless LAN adapter Wi-Fi, di situ ada Ipv4 Address, nah itu copas en ke angka di URL
* kalo masih error, coba cek di AndroidManifest.xml bagian android:usesCleartextTraffic pastiin nilainya true
* Pastiin juga laptop sama hp kalian tersambung di satu jaringan (WiFi / hotspot yang sama). Itu salah satu syarat biar bisa akses database
* XAMPP juga nyalain jangan lupa.*/

// ip di kos yusril = 192.168.1.4
// ip di vibes = 192.168.1.66
// ip di rumah yusril = 192.168.100.5

public class DBContract {
    public static final String SERVER_LOGIN_URL = "http://192.168.1.4/cobaparkir/api/Login_Api/auth";
    public static final String SERVER_SIGNUP_URL = "http://192.168.1.4/cobaparkir/api/Signup_Api/auth";
    public static final String SERVER_UPDATE_URL = "http://192.168.1.4/cobaparkir/api/Updateprofile_Api/auth";
    public static final String SERVER_BOOKING_URL = "http://192.168.1.4/cobaparkir/api/Booking_Api/cek";
    public static final String SERVER_INSERT_BOOKING_URL = "http://192.168.1.4/cobaparkir/api/Booking_Api/input";
}
