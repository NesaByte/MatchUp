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

import static com.example.organizer_v2.MainActivity.sqLiteHelperMATCHUP;

public class activity_viewmatchup extends AppCompatActivity {

    SearchView sv_searchView;
    ListView lv_listView;
    ArrayList<Model_matched> mList;
    adapter_viewmatch mAdapter = null;

    ImageView iv_phototop;
    ImageView iv_photobottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmatchup);

        MainActivity.fixmatch();

        sv_searchView = findViewById(R.id.sv_searchView);
        lv_listView = findViewById(R.id.lv_listView);
        mList = new ArrayList<>();
        mAdapter = new adapter_viewmatch(this, R.layout.row_layout_matchup, mList);
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

        Cursor cursor = sqLiteHelperMATCHUP.getDataM("SELECT * FROM DB_MATCHED");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name_m = cursor.getString(1);
            byte[] image_t = cursor.getBlob(2);
            byte[] image_b = cursor.getBlob(3);

            mList.add(new Model_matched(id, name_m, image_t, image_b));
/**/             toastMsg("match name is>> " + name_m);
        }
       mAdapter.notifyDataSetChanged();

        if (mList.size() == 0) {
            toastMsg("Empty matchups");
        }
    }
    private void toastMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
