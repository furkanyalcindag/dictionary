package com.example.furkan.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class quizActivity extends AppCompatActivity {



    Database mydb;
    ArrayList<dictionary> ax;
    public ArrayList<String> english ;
    public ArrayList<String> turkish ;
    public TextView soruSira;
    public TextView soru;
    public TextView puan;
    public EditText answer;
    public Button okButton;
    public Button next;
    public String question;
    public String cevap;
    public double point =0;
    public int b=0;
    int a=0;
    Random r=new Random();
    ArrayList<dictionary> array_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        soruSira=(TextView)findViewById(R.id.textSira);
        soru=(TextView)findViewById(R.id.textSoru);
        puan=(TextView)findViewById(R.id.textPuan);
        answer=(EditText) findViewById(R.id.editAnswer);
        okButton =(Button) findViewById(R.id.buttonAnswer);
        next =(Button) findViewById(R.id.next);
        puan.setText(String.valueOf(point));
        mydb = new Database(this);
        array_list = mydb.getAllCotacts();
        if(array_list.size()==0){

        }
        english= new ArrayList<String>();
        turkish= new ArrayList<String>();
        for(int i=0;i<array_list.size();i++){
            english.add(array_list.get(i).getEnglish().toString());
            turkish.add(array_list.get(i).getTurkish().toString());
        }
        okButton.setEnabled(false);



next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        okButton.setEnabled(true);
        next.setText("Next Question>>");
        a=r.nextInt(array_list.size());
        System.out.println(a+"dsds");
        b++;
        soruSira.setText(String.valueOf(b));
        soru.setText(english.get(a));
    }
});
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okButton.setText("OK");
                 //random sınıfı


                if(answer.getText().toString().toLowerCase().equals(turkish.get(a).toLowerCase())){
                    point=point+1;
                    puan.setText(String.valueOf(point));
                    Toast.makeText(getApplicationContext(), "True!!!", Toast.LENGTH_LONG).show();
//Toast.LENGTH_LONG yerine 2000 girersek 2 sn gösterecektir.
                }
                else{
                    point=point-0.5;
                    puan.setText(String.valueOf(point));
                    Toast.makeText(getApplicationContext(), "False!!!", Toast.LENGTH_LONG).show();

                }

                if(point<0){
                    Toast.makeText(getApplicationContext(), "You need some training", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(v.getContext(),listWord.class);
                    startActivity(intent);
                }

                answer.setText("");
                soru.setText("");
                okButton.setEnabled(false);

            }
        });




    }
}
