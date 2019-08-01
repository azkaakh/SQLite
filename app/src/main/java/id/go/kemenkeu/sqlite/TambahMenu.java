package id.go.kemenkeu.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahMenu extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Cursor cursor;
    EditText nomor, nama, harga, jenis, deskripsi, gambar;
    Button simpan, kembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);

        databaseHelper = new DatabaseHelper(this);
        nomor = (EditText)findViewById(R.id.idedit);
        nama = (EditText)findViewById(R.id.namaedit);
        harga = (EditText)findViewById(R.id.hargaedit);
        jenis = (EditText)findViewById(R.id.jenisedit);
        deskripsi = (EditText)findViewById(R.id.deskripsiedit);
        gambar = (EditText)findViewById(R.id.gambaredit);
        simpan = (Button)findViewById(R.id.tombolsimpan);
        kembali = (Button)findViewById(R.id.tombolkembali);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.execSQL("insert into menu (id, nama, harga, jenis, deskripsi, " +
                        "gambar) values ('"+ nomor.getText().toString() +"'," +
                        "'"+ nama.getText().toString() +"'," +
                        "'"+ harga.getText().toString() +"'," +
                        "'"+ jenis.getText().toString() +"'," +
                        "'"+ deskripsi.getText().toString() +"'," +
                        "'"+ gambar.getText().toString() +"' )");
                Toast.makeText(TambahMenu.this,"Sukses",
                        Toast.LENGTH_LONG).show();
                DaftarMenu.daftarMenu.getList();
                finish();
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
