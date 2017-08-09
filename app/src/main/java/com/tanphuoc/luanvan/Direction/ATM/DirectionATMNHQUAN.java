package com.tanphuoc.luanvan.Direction.ATM;

import android.os.AsyncTask;

import com.tanphuoc.luanvan.Moudle.DirectionFinderListener;
import com.tanphuoc.luanvan.Moudle.TramATM;

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

public class DirectionATMNHQUAN {
//    String linkTramATM="http://172.16.1.105/luanvan/GetTramATM.php";
    String linkTramATM="http://192.168.1.51/luanvan/GetTramATM.php";

    static List<TramATM> routes;
    private DirectionFinderListener listener;
    private int vitriNH;
    private List<Integer> vitriQuan;

    public DirectionATMNHQUAN(DirectionFinderListener listener, int vitriNH, List<Integer> vitriQuan) {
        this.listener = listener;
        this.vitriNH = vitriNH;
        this.vitriQuan = vitriQuan;
    }

    public void execute() throws UnsupportedEncodingException {
        listener.onDirectionFinderStarttimtram();
        new DownloadRawData().execute(createUrl());
    }

    private String createUrl() throws UnsupportedEncodingException {

        return linkTramATM;
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

        routes = new ArrayList<TramATM>();
        JSONArray jsonArray=new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonATM = jsonArray.getJSONObject(i);
            TramATM tramATM = new TramATM();

            int nh = jsonATM.getInt("manh");
            int quan = jsonATM.getInt("maquan");

            if(nh==vitriNH){
                for(int k=0 ; k<vitriQuan.size();k++) {
                    if (quan == vitriQuan.get(k)) {

                        tramATM.setMaTramATM(jsonATM.getInt("id"));
                        tramATM.setTenTram(jsonATM.getString("ten"));
                        tramATM.setDiachi(jsonATM.getString("diachi"));
                        tramATM.setRating(jsonATM.getDouble("rating"));
                        tramATM.setLat(jsonATM.getDouble("lat"));
                        tramATM.setLng(jsonATM.getDouble("lng"));
                        tramATM.setMaQuan(jsonATM.getInt("maquan"));
                        tramATM.setMaNH(jsonATM.getInt("manh"));

                        routes.add(tramATM);
                    }
                    else {continue; }
                }
            }
            else{ continue; }

        }

        listener.onDirectionFinderSuccessATM(routes,0);
        listener.SendDataATM(routes);
    }
}
