package com.example.mobile_weatherforecast_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


// the mapBox geo coding api will be used in this project which the forwarding geo coding will takes
// a String which here is a city name and turns it into coordinates


//----------------------------------------------------------------------------------------------------
// keys :
// geo coding map box secret key is : pk.eyJ1IjoiZWxhaGVraG9kYWllIiwiYSI6ImNrOGJ0YzFsMzBmdHAza3BpNHR1dmdzZnoifQ.Hq12JjRjpv90sGEjOMyy0g
// dark sky api secret key is :3bed00074e5a1aef2e2cbd65e6dafb77

//----------------------------------------------------------------------------------------------------
public class MainActivity extends AppCompatActivity {

    String textCoordx;
    String textCoordy;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        getWeather();
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText simpleEditText = (EditText) findViewById(R.id.cityName);//get the id for edit text
        Button showCoordButton = (Button) findViewById(R.id.angry_btn);//get the id for button

        showCoordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetCoordinates().execute(simpleEditText.getText().toString());
            }
        });

//        showCoordButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (simpleEditText.getText().toString() != null)//check whether the entered text is not null
//                {
//                    Toast.makeText(getApplicationContext(), simpleEditText.getText().toString(), Toast.LENGTH_LONG).show();//display the text that you entered in edit text
//                }
//            }
//        });
    }
// this function is to get the text field info and start fetching info


//public void getWeather(){
//    final EditText editText = (EditText) findViewById(R.id.cityName);
//    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//        @Override
//        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//            boolean handled = false;
//
//            if(i == EditorInfo.IME_ACTION_SEND){
//                //TODO  fetch city location from map box api and pass it to dark sky API
//                handled = true;
//                getInfoTextField(editText);
//            }
//
//            return handled;
//        }
//    });
//
//}
//   public void getInfoTextField(EditText editText){
//        String cityName = editText.getText().toString();
//        //TODO  use the cityName and map box api to find location
//
//
//   }
//



   @SuppressLint("StaticFieldLeak")
   private class  GetCoordinates extends AsyncTask<String, Void, String >{


        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog.setMessage("Please wait ....");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
       @Override
       protected String doInBackground(String...  strings) {
            String response;
            try{
                String address = strings[0];
                HttpDataHandler http = new HttpDataHandler();
                String url = String.format("https://api.mapbox.com/geocoding/v5/mapbox.places/%s.json?access_token=%s", address);
                response = http.getHttpData(url);
                return response;

            }
            catch (Exception e){
                e.printStackTrace();
            }
           return null;
       }



       @Override
       protected void onPostExecute(String s){
            try {
                JSONObject jsonObject = new JSONObject(s);
                String lat = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").
                        getJSONObject("location").get("lat").toString();

                String lng = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").
                        getJSONObject("location").get("lng").toString();
                textCoordx = lat;
                textCoordy = lng;
                if(progressDialog.isShowing())
                    progressDialog.dismiss();

                System.out.println("the city coordination is :" + lat + "-" + lng);

            }catch (Exception e){
                e.printStackTrace();
            }
       }

   }


}







