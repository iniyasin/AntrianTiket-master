package com.kilasbalik.antriantiket;

public class Konfigurasi {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP
    public static final String URL_LOGIN="http://192.168.1.2/antriantiket/login.php";
    public static final String URL_DAFTAR="http://192.168.1.2/antriantiket/daftaruser.php";
    public static final String URL_ADD_LOKET_SATU="http://192.168.1.2/antriantiket/daftarAntrianLoketSatu.php";
    public static final String URL_ADD_LOKET_DUA="http://192.168.1.2/antriantiket/daftarAntrianLoketDua.php";
    public static final String URL_GET_DATA_LOKET_SATU = "http://192.168.1.2/antriantiket/tampilDataLoketSatu.php?id=";
    public static final String URL_GET_DATA_LOKET_DUA = "http://192.168.1.2/antriantiket/tampilDataLoketDua.php?id=";
    public static final String URL_GET_DATA_ANTRIAN_SEBELUM = "http://192.168.1.2/antriantiket/tampilDataAntrianSebelum.php?id=";
    public static final String URL_GET_DATA_ANTRIAN_SEBELUM_2 = "http://192.168.1.2/antriantiket/tampilDataAntrianSebelum_2.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_ID = "id";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_NOTELP = "notelp";
    public static final String KEY_ALAMAT = "alamat";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_ANTRIAN_SEBELUM = "antrian";
    public static final String TAG_ANTRIAN_SEBELUM_2 = "antrian_2";
    public static final String TAG_NAMA = "name";
    public static final String TAG_NOTELP = "notelp";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_PASSWORD = "password";
    public static final String LOGIN_SUCCESS = "success";
    public static final String DAFTAR_SUCCESS = "berhasil";

    //ID karyawan
    //emp itu singkatan dari Employee
    static final String ANT_ID = "ant_id";
}
