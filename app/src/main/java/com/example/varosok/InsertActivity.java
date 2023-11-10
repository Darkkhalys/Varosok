package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText textNev;
    private EditText textOrszagInsert;
    private EditText textLakossag;
    private Button bttnFelvetel;
    private Button bttnInsertVissza;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();


        bttnInsertVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        bttnFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    String nev = textNev.getText().toString();
                    String orszag = textOrszagInsert.getText().toString();
                    String lakossag = textLakossag.getText().toString();

                    if (nev.isEmpty() || orszag.isEmpty() || lakossag.isEmpty()) {
                        Toast.makeText(InsertActivity.this,
                                "Mindent meg kell adni", Toast.LENGTH_SHORT).show();
                    } else {
                        int lakossagInt = Integer.parseInt(lakossag);

                        if (dbHelper.adatRogzites(nev,orszag,lakossagInt)){
                            Toast.makeText(InsertActivity.this,
                                    "Sikeres adatfelvétel", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(InsertActivity.this,
                                    "Sikertelen adatfelvétel", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });



    }


    public void init(){
        textNev = findViewById(R.id.textNev);
        textOrszagInsert = findViewById(R.id.textOrszagInsert);
        textLakossag = findViewById(R.id.textLakossag);
        bttnFelvetel = findViewById(R.id.bttnFelvetel);
        bttnInsertVissza = findViewById(R.id.bttnInsertVissza);
        dbHelper = new DBHelper(InsertActivity.this);
    }
}