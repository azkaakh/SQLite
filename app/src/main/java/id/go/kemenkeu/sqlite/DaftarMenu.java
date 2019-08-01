package id.go.kemenkeu.sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class DaftarMenu extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Cursor cursor;
    String[] daftar;
    ListView listView;
    public static DaftarMenu daftarMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_menu);

        Button button = (Button)findViewById(R.id.tambahmenu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        databaseHelper = new DatabaseHelper(this);
        daftarMenu = this;
        getList();
    }
    public void getList(){
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM menu", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int a = 0; a < cursor.getCount(); a++){
            cursor.moveToPosition(a);
            daftar[a] = cursor.getString(1).toString();
        }
        listView = (ListView)findViewById(R.id.listmenu);
        listView.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String Selection = daftar[position];
                final CharSequence[] dialog = {"Lihat Menu", "Update Menu"
                ,"Hapus Menu"};
                AlertDialog.Builder builder = new AlertDialog.Builder(DaftarMenu.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent intent = new Intent(DaftarMenu.this,LihatMenu.class);
                                intent.putExtra("nama", Selection);
                                startActivity(intent);
                                break;
                            case 2:
                                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                                db.execSQL("delete from menu where nama = '"+Selection+"'");
                                getList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }

        });
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
}
