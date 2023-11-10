package com.example.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    private TextView textKeres;
    private Button bttnKeresVissza;
    private SharedPreferences sharedPreferences;
    private DBHelper dbHelper;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        init();


        bttnKeresVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


    public void init(){
        textKeres = findViewById(R.id.textKeres);
        bttnKeresVissza = findViewById(R.id.bttnKeresVissza);
        dbHelper = new DBHelper(SearchResultActivity.this);

        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String seged = sharedPreferences.getString("adat", "Nem található a rekord a következő adattal: ");
        editor= sharedPreferences.edit();

        Cursor adatok =dbHelper.adatlekerdezes(seged);
        if (adatok.getCount()==0){
            textKeres.setText("A megadott adatok nem található: " + seged );
        } else {
            StringBuilder builder = new StringBuilder();
            while (adatok.moveToNext()){
                builder.append(adatok.getString(0))
                        .append("\n");
            }
            textKeres.setText((builder));
        }























































    }
}