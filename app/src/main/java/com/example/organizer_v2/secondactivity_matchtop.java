package com.example.organizer_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.organizer_v2.MainActivity.sqLiteHelperTOPS;

public class secondactivity_matchtop extends AppCompatActivity {

    SearchView sv_searchView;
    ListView lv_listView;
    ArrayList<Model> mList;
    adapter_viewtop mAdapter = null;

    ImageView iv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondmatchtop);

        getSupportActionBar().setTitle("Pick a top for this match");

        sv_searchView = findViewById(R.id.sv_searchView);
        lv_listView = findViewById(R.id.lv_listView);
        mList = new ArrayList<>();
        mAdapter = new adapter_viewtop(this, R.layout.row_layout, mList);
        lv_listView.setAdapter(mAdapter);

        sv_searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        sv_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        Cursor cursor = sqLiteHelperTOPS.getData("SELECT * FROM TABLE_NAME");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String tag = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            mList.add(new Model(id, name, tag, image));
        }
        mAdapter.notifyDataSetChanged();

        if (mList.size() == 0) {
            toastMsg("Empty tops");
        }
    }
    private void toastMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
