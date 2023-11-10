package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText textOrszag;
    private Button bttnKereses;
    private Button bttnUjfelvetele;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        bttnKereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orszag = textOrszag.getText().toString();
                if (orszag.isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "Ne hagyd üresen a mezőt!", Toast.LENGTH_SHORT).show();
                }
                else {

                    SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("adat",textOrszag.getText().toString());
                    editor.apply();


                    Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        bttnUjfelvetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                    startActivity(intent);
                    finish();


            }
        });
    }

    public void init(){
    textOrszag = findViewById(R.id.textOrszag);
    bttnKereses = findViewById(R.id.bttnKereses);
    bttnUjfelvetele = findViewById(R.id.bttnUjfelvetele);
    dbHelper = new DBHelper(MainActivity.this);

    }

}