package org.proyek.parkirassistant;

/*
* Untuk angka diantara http dan cobaparkir, kalian ganti sama IP Laptop kalian masing masing ya
* Cara cek IP Laptop -> buka cmd terus ketik "ipconfig"
* terus liaten bagian Wireless LAN adapter Wi-Fi, di situ ada Ipv4 Address, nah itu copas en ke angka di URL
* kalo masih error, coba cek di AndroidManifest.xml bagian android:usesCleartextTraffic pastiin nilainya true
* Pastiin juga laptop sama hp kalian tersambung di satu jaringan (WiFi / hotspot yang sama). Itu salah satu syarat biar bisa akses database
* XAMPP juga nyalain jangan lupa.*/


public class DBContract {
    public static final String SERVER_LOGIN_URL = "http://192.168.1.3/cobaparkir/api/Login_Api/auth";
    public static final String SERVER_SIGNUP_URL = "http://192.168.1.3/cobaparkir/api/Signup_Api/auth";
    public static final String SERVER_UPDATE_URL = "http://192.168.1.3/cobaparkir/api/Updateprofile_Api/auth";
    public static final String SERVER_BOOKING_URL = "http://192.168.1.3/cobaparkir/api/Booking_Api/cek";
    public static final String SERVER_RIWAYAT_TRANSAKSI_URL = "http://192.168.1.3/cobaparkir/api/Transaksi_Api/riwayat";
    public static final String SERVER_INSERT_BOOKING_URL = "http://192.168.1.3/cobaparkir/api/Booking_Api/input";
    public static final String SERVER_DELETE_BOOKING_URL = "http://192.168.1.3/cobaparkir/api/Booking_Api/delete";
    public static final String SERVER_UPDATE_PARKIR_BOOKING_URL = "http://192.168.1.3/cobaparkir/api/Booking_Api/changestatus";
    public static final String SERVER_CEK_ENTRY_URL = "http://192.168.1.3/cobaparkir/api/Entry_Api/cekentry";
    public static final String SERVER_CEK_ENTRY_PELANGGAN_URL = "http://192.168.1.3/cobaparkir/api/Entry_Api/cekentrypelanggan";
    public static final String SERVER_CEK_BOOKING_PELANGGAN_URL = "http://192.168.1.3/cobaparkir/api/Booking_Api/cekbookingpelanggan";
    public static final String SERVER_INPUT_NO_BOOKING_ENTRY_URL = "http://192.168.1.3/cobaparkir/api/Entry_Api/entrynobooking";
    public static final String SERVER_INPUT_BOOKING_ENTRY_URL = "http://192.168.1.3/cobaparkir/api/Entry_Api/entrybooking";
    public static final String SERVER_CHECKOUT_ENTRY_URL = "http://192.168.1.3/cobaparkir/api/Entry_Api/checkout";
    public static final String SERVER_INPUT_TRANSAKSI_URL = "http://192.168.1.3/cobaparkir/api/Transaksi_Api/inputtransaksi";
}
