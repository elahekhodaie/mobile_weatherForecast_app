package com.example.mobile_weatherforecast_app;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;


public class FetchWeather{

    private static final String API ="the key after we get it from the site ";


    public static JSONObject getJSON(Context context, String nav){
        try {

            URL url = new URL(String.format((API),nav));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            return data;
        }catch(Exception e){
            return null;
        }
    }
}