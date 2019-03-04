package com.dimassaptahadi1501828.utsmobprog.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<DbPembayaran.Pembayaran> aloke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    DbPembayaran dbKasir;
    public void bClick(View v) {
        //insert data,
        dbKasir = new DbPembayaran(getApplicationContext());
        dbKasir.open();
        dbKasir.insertPembayaran("1","1000");


        //coba cari pembayaran
        DbPembayaran.Pembayaran m = dbKasir.getPembayaran("1");
        Toast.makeText(getApplicationContext(),
        String.format ("Kode_Bayar: %s ; Jumlah_Bayar: %s",m.kode,m.jumlah), Toast.LENGTH_LONG).show();
    }


    //  Karena membuka database merupakan operasi yang berat, maka database
    // harus selama mungkin dibuka. Tutup database di method onDestroy() milik Activity.
    @Override
    protected void onDestroy() {
        dbKasir.close();
        super.onDestroy();
    }




}
