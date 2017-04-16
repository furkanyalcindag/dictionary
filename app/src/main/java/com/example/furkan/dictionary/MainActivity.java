package com.example.furkan.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private Button training;
    private Button add;
    private Button quiz;
    Database mydb;
    ArrayList<dictionary> array_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        training=(Button)findViewById(R.id.button);
        add=(Button)findViewById(R.id.button7);
        quiz=(Button)findViewById(R.id.button8);
        String[] English = {"name","surname","father"};
        String[] Turkish = {"isim","soyisim","baba"};



        mydb = new Database(this);
        array_list = mydb.getAllCotacts();
        if(array_list.size()==0){
            Database db = new Database(getApplicationContext());
for(int i =0;i<English.length;i++){

            db.insertContact(English[i].toString(), Turkish[i].toString());//kitap ekledik
     }
            db.close();
        }
        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), listWord.class);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), addWord.class);
                startActivity(i);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), quizActivity.class);
                startActivity(i);
            }
        });



    }

}
