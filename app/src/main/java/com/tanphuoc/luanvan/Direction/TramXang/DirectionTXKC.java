package com.tanphuoc.luanvan.Direction.TramXang;

import android.location.Location;
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

public class DirectionTXKC {
//    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place/nearbysearch";
//    private static final String OUT_JSON = "/json";
//    private static final String LOCATION = "?location=";
//    private static final String RADIUS = "&radius=";
//    private static final String TYPE = "&type=";
//    private static final String API_KEY = "&key=AIzaSyAUOx1zt79BHU9g0uFA00OydezvAd3UU1Q";
    String linkTramXang="http://192.168.1.51/luanvan/GetTramXang.php";
//    String linkTramXang="http://172.16.1.105/luanvan/GetTramXang.php";


    private DirectionFinderListener listener;
    int khoangcach;
    static List<TramXang> routes;
    double lat,lng;

    public DirectionTXKC(DirectionFinderListener listener,double lat,double lng,int khoangcach) {
        this.listener = listener;
        this.khoangcach=khoangcach;
        this.lat=lat;
        this.lng=lng;
    }

    public void execute() throws UnsupportedEncodingException {
        listener.onDirectionFinderStarttimtram();
        new DownloadRawData().execute(createUrl());
    }

    private String createUrl() throws UnsupportedEncodingException {

//        return PLACES_API_BASE + OUT_JSON + LOCATION + 10.738124 + "," + 106.677976 + RADIUS + khoangcach + TYPE + "gas_station" + API_KEY;
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
//        routes = new ArrayList<TramXang>();
//
//        JSONObject jsonData = new JSONObject(data);
//        JSONArray jsonRoutes = jsonData.getJSONArray("results");
//        for (int i = 0; i < jsonRoutes.length(); i++) {
//            JSONObject jsonXang = jsonRoutes.getJSONObject(i);
//
//            JSONObject geometry=jsonXang.getJSONObject("geometry");
//            JSONObject location=geometry.getJSONObject("location");
//
//            double rating =0.0;
//            if (jsonXang.has("rating")) {
//                rating=Double.valueOf(String.valueOf(jsonXang.get("rating")));
//            }
//            else {
//                rating=0.0;
//            }
//
//            TramXang tramATM = new TramXang(
//                    jsonXang.getString("name"),
//                    jsonXang.getString("vicinity"),
//                    rating,
//                    (Double) location.get("lat"),
//                    (Double) location.get("lng"));
//            routes.add(tramATM);
//        }
//
//        listener.onDirectionFinderSuccessTX(routes,false,khoangcach);
//        listener.SendDataXang(routes);
        if (data == null)
            return;

        routes = new ArrayList<TramXang>();
        JSONArray jsonArray=new JSONArray(data);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonTX = jsonArray.getJSONObject(i);
            TramXang tramXang = new TramXang();
            float result[]=new float[3];


                tramXang.setMaTramXang(jsonTX.getInt("id"));
                tramXang.setTenTram(jsonTX.getString("ten"));
                tramXang.setDiachi(jsonTX.getString("diachi"));
                tramXang.setRating(jsonTX.getDouble("rating"));
                tramXang.setLat(jsonTX.getDouble("lat"));
                tramXang.setLng(jsonTX.getDouble("lng"));
                tramXang.setMaQuan(jsonTX.getInt("maquan"));
            Location.distanceBetween(lat, lng,tramXang.getLat(),tramXang.getLng(),result);
            if(result[0] < khoangcach || result[0] == khoangcach) {
                routes.add(tramXang);
            }
        }

        listener.onDirectionFinderSuccessTX(routes,khoangcach);
        listener.SendDataXang(routes);
    }
}
