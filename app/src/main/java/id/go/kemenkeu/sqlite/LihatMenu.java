package id.go.kemenkeu.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LihatMenu extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Cursor cursor;
    Button kembali;
    TextView nama, harga, jenis, deskripsi, gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_menu);

        databaseHelper = new DatabaseHelper(this);
        nama = (TextView)findViewById(R.id.textnama);
        harga = (TextView)findViewById(R.id.textharga);
        jenis = (TextView)findViewById(R.id.textjenis);
        deskripsi = (TextView)findViewById(R.id.textdeskripsi);
        gambar = (TextView)findViewById(R.id.textgambar);
        kembali = (Button)findViewById(R.id.tombolkembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM menu WHERE nama = '"
        + getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(1).toString());
            harga.setText(cursor.getString(2).toString());
            jenis.setText(cursor.getString(3).toString());
            deskripsi.setText(cursor.getString(4).toString());
            gambar.setText(cursor.getString(5).toString());
        }
    }
}
