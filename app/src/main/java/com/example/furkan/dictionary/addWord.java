package com.example.furkan.dictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;





import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.furkan.dictionary.Database;

public class addWord extends Activity {
    Button b1;
    EditText e1,e2,e3,e4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);


        b1 = (Button)findViewById(R.id.button1);
        e1 = (EditText)findViewById(R.id.editText1);
        e2 = (EditText)findViewById(R.id.editText2);


        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String adi,yazari,yili,fiyati;
                adi = e1.getText().toString();
                yazari = e2.getText().toString();

                if(adi.matches("") || yazari.matches("")   ){
                    Toast.makeText(getApplicationContext(), "Tüm Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_LONG).show();
                }else{
                    Database db = new Database(getApplicationContext());

                    db.insertContact(adi, yazari);//kitap ekledik
                    db.close();
                    Toast.makeText(getApplicationContext(), "Kelime Başarıyla Eklendi.", Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");

                }


            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }


}

