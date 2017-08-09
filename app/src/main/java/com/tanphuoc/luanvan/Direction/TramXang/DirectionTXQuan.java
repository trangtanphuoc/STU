package com.tanphuoc.luanvan.Direction.TramXang;

import android.os.AsyncTask;

import com.tanphuoc.luanvan.Moudle.DirectionFinderListener;
import com.tanphuoc.luanvan.Moudle.TramXang;

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
import java.util.ArrayList;
import java.util.List;


public class DirectionTXQuan {
//    String linkTramXang="http://172.16.1.105/luanvan/GetTramXang.php";
    String linkTramXang="http://192.168.1.51/luanvan/GetTramXang.php";

    static List<TramXang> routes;
    private DirectionFinderListener listener;
    private List<Integer> vitriQuan;

    public DirectionTXQuan(DirectionFinderListener listener, List<Integer> vitriQuan) {
        this.listener = listener;
        this.vitriQuan = vitriQuan;
    }

    public void execute() throws UnsupportedEncodingException {
        listener.onDirectionFinderStarttimtram();
        new DownloadRawData().execute(createUrl());
    }

    private String createUrl() throws UnsupportedEncodingException {

        return linkTramXang;
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
            try {
                parseJSon(res);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseJSon(String data) throws JSONException {
        if (data == null)
            return;

        routes = new ArrayList<TramXang>();
        JSONArray jsonArray=new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonTX = jsonArray.getJSONObject(i);
            TramXang tramXang = new TramXang();

            int quan = jsonTX.getInt("maquan");
            for(int k=0;k<vitriQuan.size();k++){
                    if(quan == vitriQuan.get(k)){
                        tramXang.setMaTramXang(jsonTX.getInt("id"));
                        tramXang.setTenTram(jsonTX.getString("ten"));
                        tramXang.setDiachi(jsonTX.getString("diachi"));
                        tramXang.setRating(jsonTX.getDouble("rating"));
                        tramXang.setLat(jsonTX.getDouble("lat"));
                        tramXang.setLng(jsonTX.getDouble("lng"));
                        tramXang.setMaQuan(jsonTX.getInt("maquan"));

                        routes.add(tramXang);
                }
                else{ continue; }
            }
        }
        listener.onDirectionFinderSuccessTX(routes,0);
        listener.SendDataXang(routes);
    }
}
