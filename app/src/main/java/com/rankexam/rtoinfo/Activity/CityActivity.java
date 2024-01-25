package com.rankexam.rtoinfo.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.rankexam.rtoinfo.AdsManager;
import com.rankexam.rtoinfo.Adapter.ListAdapter;
import com.rankexam.rtoinfo.DataBase.DBHelper;
import com.rankexam.rtoinfo.Model.CitiesModel;
import com.rankexam.rtoinfo.R;
import java.io.IOException;
import java.util.ArrayList;


public class CityActivity extends AppCompatActivity {
    public static ArrayList<CitiesModel> arrlist;
    ListView lv_cities;
    SQLiteDatabase myDB;
    String str_field2;
    String str_field3;
    String str_field4;
    String str_field5;
    String str_states_name;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_offices_city);
        AdsManager.getInstance().loadBanner(this);

        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityActivity.this.onBackPressed();
            }
        });
        this.str_states_name = getIntent().getStringExtra("states_name");
        this.lv_cities = (ListView) findViewById(R.id.lv_cities);
        fill_category(this.str_states_name);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @SuppressLint("Range")
    private void fill_category(String str) {
        DBHelper m_rtoDBHelper = new DBHelper(this);
        try {
            m_rtoDBHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            m_rtoDBHelper.close();
            throw th;
        }
        m_rtoDBHelper.close();
        try {
            this.myDB = m_rtoDBHelper.openDataBase();
            arrlist = new ArrayList<>();
            SQLiteDatabase sQLiteDatabase = this.myDB;
            Cursor rawQuery = sQLiteDatabase.rawQuery("select * from rto_data where field2 LIKE '%" + str + "%'", null);
            if (rawQuery.getCount() > 0) {
                rawQuery.moveToFirst();
                do {
                    this.str_field2 = rawQuery.getString(rawQuery.getColumnIndex("field2"));
                    this.str_field3 = rawQuery.getString(rawQuery.getColumnIndex("field3"));
                    this.str_field4 = rawQuery.getString(rawQuery.getColumnIndex("field4"));
                    this.str_field5 = rawQuery.getString(rawQuery.getColumnIndex("field5"));
                    CitiesModel m_rtoCities = new CitiesModel();
                    m_rtoCities.setField2(this.str_field2);
                    m_rtoCities.setField3(this.str_field3);
                    m_rtoCities.setField4(this.str_field4);
                    m_rtoCities.setField5(this.str_field5);
                    arrlist.add(m_rtoCities);
                } while (rawQuery.moveToNext());

            }
        } catch (SQLException unused) {
        }
        this.lv_cities.setAdapter((android.widget.ListAdapter) new ListAdapter(this, arrlist));
        this.lv_cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(CityActivity.this, OfficeDetailsActivity.class);
                intent.putExtra("states_name", CityActivity.this.str_states_name);
                intent.putExtra("position", i);
                CityActivity.this.startActivity(intent);


            }
        });
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }


}
