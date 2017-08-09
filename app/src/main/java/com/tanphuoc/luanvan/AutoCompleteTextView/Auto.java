package com.tanphuoc.luanvan.AutoCompleteTextView;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Auto extends ArrayAdapter implements Filterable {
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    private static final String API_KEY = "AIzaSyAUOx1zt79BHU9g0uFA00OydezvAd3UU1Q";


    String diachi;
    private Context listener;
    private boolean a;
    private ArrayList resultList;
    public Auto(Context listener, int result) {
        super(listener, result);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public String getItem(int index) {
        return (String) resultList.get(index);
    }
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // Retrieve the autocomplete results.
                    resultList = autocomplete(constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
    public static ArrayList autocomplete(String input) {
        ArrayList resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            sb.append("&components=country:vn");
//            sb.append("&types=address");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {

            return resultList;
        } catch (IOException e) {

            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                System.out.println(predsJsonArray.getJSONObject(i).getString("description"));
                System.out.println("============================================================");
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }

        } catch (JSONException e) {
          e.printStackTrace();
        }

        return resultList;
    }

//    public void execute() throws UnsupportedEncodingException {
//        new DownloadRawData().execute(createUrl());
//    }
//
//    private String createUrl() throws UnsupportedEncodingException {
//        diachi = URLEncoder.encode(diachi, "utf-8");
//
//
//        return "https://maps.googleapis.com/maps/api/place/autocomplete/json?key="
//                + API_KEY
//                + "&components=country:vn"//chỉ hiện ở VN
//                + "&language=vn"//ngôn ngữ VN
//                + "&types=address"
//                + "&input="
//                + diachi;
//    }
//    private class DownloadRawData extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String link = params[0];
//            try {
//                URL url = new URL(link);
//                InputStream is = url.openConnection().getInputStream();
//                StringBuffer buffer = new StringBuffer();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line + "\n");
//                }
//                publishProgress(buffer.toString());
//
//                return buffer.toString();
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String res) {
//           try {
//                parseJSon(res);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//            try {
//                parseJSon(values[0]);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void parseJSon(String data) throws JSONException{
//        List<String> list = new ArrayList<>();
//        //Toast.makeText((Context) listener,data.toString(), Toast.LENGTH_SHORT).show();
//        if (data == null)
//            return;
//        JSONObject jsonData = new JSONObject(data);
//        JSONArray jsonRoutes = jsonData.getJSONArray("predictions");
//        for (int i = 0; i < jsonRoutes.length(); i++) {
//            list.add(jsonRoutes.getJSONObject(i).getString("description"));
//            System.out.println(jsonRoutes.getJSONObject(i).getString("description"));
//        }
//        if(a==true) {
//            listener.ChuyenATM(list);
//        }else{
//            listener.ChuyenTX(list);
//        }
//    }
}
