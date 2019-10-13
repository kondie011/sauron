package com.transendance.sauron;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kondie on 2018/02/11.
 */

public class SendSms extends AsyncTask <String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        try{
            URL url = new URL("https://api.simwood.com/v3/messaging/931247/sms");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("05ce9aec47995757b9299da3ff3889c245861955", "d1972d09fe5e2c9d38db74c8d7d750667eaff283".toCharArray());
                }
            });
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder().appendQueryParameter("to", "27662742276")
                    .appendQueryParameter("from", "447537149251")
                    .appendQueryParameter("message", params[0]);
            String query = builder.build().getEncodedQuery();

            OutputStream outStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));

            writer.write(query);
            writer.flush();
            writer.close();
            outStream.close();
            conn.connect();

            int responseCode = conn.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){

                InputStream inStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                StringBuilder result = new StringBuilder();
                String line;

                while((line=reader.readLine()) != null){

                    result.append(line);
                }
                inStream.close();
                return result.toString();
            }else{
                return "Response code Prob";
            }

        }catch (Exception e){
            return "Oooops "+e.toString();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try{
//            Toast.makeText(MainActivity.activity, s, Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(MainActivity.activity, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
