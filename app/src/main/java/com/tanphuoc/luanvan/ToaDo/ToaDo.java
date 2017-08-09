package com.tanphuoc.luanvan.ToaDo;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.tanphuoc.luanvan.Moudle.DirectionFinderListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ToaDo {
    private static final String URLLINK ="http://maps.google.com/maps/api/geocode/json?address=" ;
    private static final String SENSOR="&sensor=false";
    private DirectionFinderListener listener;
    private String diachi;
    private boolean loai;

    public ToaDo(DirectionFinderListener listener, String diachi,boolean loai) {
        this.listener = listener;
        this.diachi = diachi;
        this.loai = loai;
    }

    public com.google.android.gms.maps.model.LatLng execute() throws UnsupportedEncodingException {
        listener.onDirectionFinderStarttimtram();
        new DownloadRawData().execute(createUrl());
        return null;
    }

    private String createUrl() throws UnsupportedEncodingException {
        String toado = URLEncoder.encode(diachi, "utf-8");

        return URLLINK + toado + SENSOR;
    }

    private class DownloadRawData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String link = params[0];
            try {
                URL url = new URL(link);
                InputStream is = url.openConnection().getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String res) {
            JSONObject jsonData = null;
            try {
                jsonData = new JSONObject(res);
                JSONArray jsonRoutes = jsonData.getJSONArray("results");
                JSONObject JSonKinhDo = null;
                JSONObject data = null;

                data = jsonRoutes.getJSONObject(0);
                JSonKinhDo = data.getJSONObject("geometry");
                JSONObject toado= JSonKinhDo.getJSONObject("location");

                com.google.android.gms.maps.model.LatLng kinhdo =new LatLng(toado.getDouble("lat"), toado.getDouble("lng"));
                listener.AddMarketVitriKhac(kinhdo,diachi,loai);

//                Toast.makeText(context,kinhdo.toString() , Toast.LENGTH_LONG).show();
            } catch(JSONException e){
                e.printStackTrace();
            }

        }
    }
}
