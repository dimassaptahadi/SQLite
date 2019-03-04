package com.dimassaptahadi1501828.utsmobprog.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DbPembayaran {
    //class untuk menyimpan record
    public static class Pembayaran {
        public String kode;
        public String jumlah;
    }


    private  SQLiteDatabase db;
    private final OpenHelper dbHelper;

    public DbPembayaran(Context c) {
        dbHelper =  new OpenHelper(c);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public long insertPembayaran(String kode, String jumlah) {
        ContentValues newValues = new ContentValues();
        newValues.put("KODE", kode);
        newValues.put("JUMLAH", jumlah);
        return db.insert("PEMBAYARAN", null, newValues);
    }

    //ambil data pembayaran berdasarkan KODE
    public Pembayaran getPembayaran(String kode) {
        Cursor cur = null;
        Pembayaran M = new Pembayaran();

        //kolom yang diambil
        String[] cols = new String [] {"ID", "KODE", "JUMLAH"};
        //parameter, akan mengganti ? pada KODE=?
        String[] param  = {kode};

        cur = db.query("PEMBAYARAN",cols,"KODE=?",param,null,null,null);

        if (cur.getCount()>0) {  //ada data? ambil
            cur.moveToFirst();
            M.kode = cur.getString(1);
            M.jumlah = cur.getString(2);
        }
        cur.close();
        return M;
    }

    //ambil semua data mahasiswa (dibatasi 10)
    //menggunakan raw query
    public ArrayList<Pembayaran> getAllPembayaran() {
        Cursor cur = null;
        ArrayList<Pembayaran> out = new ArrayList<>();
        cur = db.rawQuery("SELECT kode,jumlah FROM Pembayaran Limit 1", null);
        if (cur.moveToFirst()) {
            do {
                Pembayaran oke = new Pembayaran();
                oke.kode = cur.getString(0);
                oke.jumlah = cur.getString(1);
                out.add(oke);
            } while (cur.moveToNext());
        }
        cur.close();
        return out;
    }


}
