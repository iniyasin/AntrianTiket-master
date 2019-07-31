package com.kilasbalik.antriantiket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoketSatu extends AppCompatActivity {
    private TextView nomorAntrian;
    private TextView antrianSebelum;
    private Button btnFinish;

    private String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_antrian);

        Intent intent = getIntent();
        id = intent.getStringExtra(Konfigurasi.ANT_ID);

        nomorAntrian = (TextView) findViewById(R.id.nomorAntrian);
        antrianSebelum = (TextView) findViewById(R.id.antrian_sebelum);
        btnFinish = (Button)findViewById(R.id.ok);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getData();
        getDataSebelum();
    }

    private void getData() {
        class GetData extends AsyncTask<Void, Void, String> {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoketSatu.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showData(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                return rh.sendGetRequestParam(Konfigurasi.URL_GET_DATA_LOKET_SATU, id);
            }
        }
        GetData ge = new GetData();
        ge.execute();
    }


    private void getDataSebelum() {
        class GetDataSebelum extends AsyncTask<Void, Void, String> {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoketSatu.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showDataSebelum(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                return rh.sendGetRequestParam(Konfigurasi.URL_GET_DATA_ANTRIAN_SEBELUM, id);
            }
        }
        GetDataSebelum ge = new GetDataSebelum();
        ge.execute();
    }

    private void showData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String id = c.getString(Konfigurasi.TAG_ID);

            nomorAntrian.setText(id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDataSebelum(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String antrian = c.getString(Konfigurasi.TAG_ANTRIAN_SEBELUM);

            antrianSebelum.setText(antrian);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
