package com.example.mobile_weatherforecast_app;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWeather();


    }


// this function is to get the text field info and start fetching info

public void getWeather(){
    final EditText editText = (EditText) findViewById(R.id.cityName);
    editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            boolean handled = false;

            if(i == EditorInfo.IME_ACTION_SEND){
                //TODO  fetch city location from map box api and pass it to dark sky API
                handled = true;
                getInfoTextField(editText);
            }

            return handled;
        }
    });

}
   public void getInfoTextField(EditText editText){
        String cityName = editText.getText().toString();
        //TODO  use the cityName and map box api to find location


   }




}


