package com.kilasbalik.antriantiket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView tv_regis;
    private Button login;
    private ProgressDialog pDialog;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;

        pDialog = new ProgressDialog(context);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.pass);
        tv_regis = (TextView)findViewById(R.id.tv_daftar);
        login = (Button)findViewById(R.id.btn_login);

        tv_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DaftarActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        final String EMAIL = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        pDialog.setMessage("Memulai Login....");
        pDialog.show();

        //membuat String request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains(Konfigurasi.LOGIN_SUCCESS)) {
                            hideDialog();
                            gotoDashboard();
                        } else {
                            hideDialog();
                            //menampilkan error message pada toast
                            Toast.makeText(context, "invalid email or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //untuk menghandle error
                        hideDialog();
                        Toast.makeText(context, "the server unreacheable", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //mengambil parameter untuk request
                params.put(Konfigurasi.KEY_EMAIL, EMAIL);
                params.put(Konfigurasi.KEY_PASSWORD, pass);

                //return parameter
                return params;
            }
        };
        //menambah string request ke antrian
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void gotoDashboard() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
