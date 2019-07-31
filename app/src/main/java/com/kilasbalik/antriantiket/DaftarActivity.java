package com.kilasbalik.antriantiket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class DaftarActivity extends AppCompatActivity {
    private EditText nama;
    private EditText notelp;
    private EditText email;
    private EditText password;

    private Button btn_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        nama = (EditText)findViewById(R.id.reg_nama);
        notelp = (EditText)findViewById(R.id.reg_notelp);
        email = (EditText)findViewById(R.id.reg_email);
        password = (EditText)findViewById(R.id.reg_pass);

        btn_daftar = (Button)findViewById(R.id.btn_daftar);
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Daftar();
            }
        });
    }

    private void Daftar(){
        final String Nama = nama.getText().toString().trim();
        final String Notelp = notelp.getText().toString().trim();
        final String Email = email.getText().toString().trim();
        final String Pass = password.getText().toString().trim();


        class Daftar extends AsyncTask<Void, Void, String> {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DaftarActivity.this, "Menambahkan...", "Tunggu....", false, false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DaftarActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_NAMA, Nama);
                params.put(Konfigurasi.KEY_NOTELP, Notelp);
                params.put(Konfigurasi.KEY_EMAIL, Email);
                params.put(Konfigurasi.KEY_PASSWORD, Pass);

                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(Konfigurasi.URL_DAFTAR, params);
            }
        }
        Daftar da = new Daftar();
        da.execute();
    }
}
