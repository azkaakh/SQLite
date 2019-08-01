package id.go.kemenkeu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAMA = "masakan.db";
    private static final int DATABASE_VERSI = 1;
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAMA, null, DATABASE_VERSI);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table menu (id integer primary key, " +
                "nama text not null, harga integer not null, " +
                "jenis text, deskripsi text, gambar text)";
        db.execSQL(sql);
        String sql2 = "insert into menu (id, nama, harga, jenis, " +
                "deskripsi, gambar) values ('1', 'Pisang', '10000'" +
                ", 'makanan', 'menu pembuka sarapan', " +
                "'gambar_pisang')";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
