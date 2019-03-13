package com.currencyconverter;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.CorrectionInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    public void clearClick(View view)
    {
        ((EditText)findViewById(R.id.edittext) ).setText("");
    }

    public void swapClick(View view)
    {
        Spinner fromSpinner = (Spinner)findViewById(R.id.fromSpinner);
        Spinner toSpinner = (Spinner)findViewById(R.id.toSpinner);
        //swap index
        int temp = Currency.fromCountryIndex;
        Currency.fromCountryIndex = Currency.toCountryIndex;
        Currency.toCountryIndex = temp;

        //swap spinner
        int tempPosition = toSpinner.getSelectedItemPosition();
        toSpinner.setSelection(fromSpinner.getSelectedItemPosition());
        fromSpinner.setSelection(tempPosition);

        String text = Integer.toString(tempPosition);
        Toast.makeText(MainActivity.this,text, Toast.LENGTH_SHORT).show();


    }
    public void convertClick(View view)
    {

        String str = ((EditText)findViewById(R.id.edittext) ).getText().toString();
        if(str.equals("")){
            return;
        }
        double  amount = Currency.convert(Double.parseDouble(str));
        TextView label = (TextView)findViewById(R.id.result);
        label.setText(Double.toString(amount));


    }


    void spinnerHandler()
    {
        //set fromSpinner value
        Spinner fromSpinner = (Spinner)findViewById(R.id.fromSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Arrays.asList(Currency.indexToCountry));

        fromSpinner.setAdapter(adapter);

        //listener
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
                int countryIndex = Currency.getCountryToIndex(item);
                if(countryIndex != -1){
                    Currency.fromCountryIndex = countryIndex;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner toSpinner = (Spinner)findViewById(R.id.toSpinner);
        toSpinner.setAdapter(adapter);
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
                int countryIndex = Currency.getCountryToIndex(item);
                if(countryIndex != -1){
                    Currency.toCountryIndex = countryIndex;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Currency.fromCountryIndex = 0;
        Currency.toCountryIndex = 0;
        spinnerHandler();


    }
}
