package com.example.furkan.dictionary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

public class listWord extends AppCompatActivity {
    private ListView words;
    ArrayAdapter adapter;
    ArrayList<HashMap<String, String>> kitap_liste;
    String kitap_adlari[];
    int kitap_idler[];
    int id_To_Update = 0;
    Database mydb;
    ArrayList<dictionary> a;
    public ArrayList<String> english ;
    public ArrayList<String> turkish ;
    private int dictionary;

    public void fill( String[] english,ArrayList<dictionary> a){


        for(int i=0;i<a.size();i++){
            english[i]=a.get(i).getEnglish();
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_word);


        words = (ListView)findViewById(R.id.listword);
        mydb = new Database(this);
        ArrayList<dictionary> array_list = mydb.getAllCotacts();
        english= new ArrayList<String>();
        turkish= new ArrayList<String>();
        for(int i=0;i<array_list.size();i++){
            english.add(array_list.get(i).getEnglish().toString());
            turkish.add(array_list.get(i).getTurkish().toString());
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, english);



           // System.out.println(a.size()+"qqq");
            //fill(english,a);





        //dictionary = R.array.dictionary;
       // ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
              //  (this, android.R.layout.simple_list_item_1, android.R.id.text1, english);


        words.setAdapter(arrayAdapter);







        //(C) adımı


        words.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                AlertDialog.Builder diyalogOlusturucu =
                        new AlertDialog.Builder(listWord.this);
                String[] a = new String[10000];
                for(int i=0;i<turkish.size();i++){
                    a[i]=turkish.get(i).toString();
                }
                diyalogOlusturucu.setTitle("Turkish").setMessage(a[position])
                        .setCancelable(false)
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                diyalogOlusturucu.create().show();

            }
        });




    }





    /*public void onResume()
    {   //neden onResume metodu kullandığımı ders içinde anlattım.
        super.onResume();
        Database db = new Database(getApplicationContext()); // Db bağlantısı oluşturuyoruz. İlk seferde database oluşturulur.
        System.out.println(db.getRowCount()+"sdsds");
        kitap_liste = db.kitaplar();//kitap listesini alıyoruz
        if(kitap_liste.size()==0){//kitap listesi boşsa
            Toast.makeText(getApplicationContext(), "Henüz Kitap Eklenmemiş.\nYukarıdaki + Butonundan Ekleyiniz", Toast.LENGTH_LONG).show();
        }else{
            kitap_adlari = new String[kitap_liste.size()]; // kitap adlarını tutucamız string arrayi olusturduk.
            kitap_idler = new int[kitap_liste.size()]; // kitap id lerini tutucamız string arrayi olusturduk.
            for(int i=0;i<kitap_liste.size();i++){
                kitap_adlari[i] = kitap_liste.get(i).get("english");
                //kitap_liste.get(0) bize arraylist içindeki ilk hashmap arrayini döner. Yani tablomuzdaki ilk satır değerlerini
                //kitap_liste.get(0).get("kitap_adi") //bize arraylist içindeki ilk hashmap arrayin anahtarı kitap_adi olan value döner

                kitap_idler[i] = Integer.parseInt(kitap_liste.get(i).get("id"));
                //Yukarıdaki ile aynı tek farkı değerleri integer a çevirdik.
            }
            //Kitapları Listeliyoruz ve bu listeye listener atıyoruz
            words = (ListView) findViewById(R.id.listword);

            adapter = new ArrayAdapter(this, R.layout.list_item, R.id.kitap_adi, kitap_adlari);
            words.setAdapter(adapter);

            words.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    //Listedeki her hangibir yere tıklandıgında tıklanan satırın sırasını alıyoruz.
                    //Bu sıra id arraydeki sırayla aynı oldugundan tıklanan satırda bulunan kitapın id sini alıyor ve kitap detaya gönderiyoruz.
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("id", (int)kitap_idler[arg2]);
                    startActivity(intent);

                }
            });
        }

    }*/
}
