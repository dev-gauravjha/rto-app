package com.rankexam.rtoinfo.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.rankexam.rtoinfo.Adapter.FuelCityAdapter;
import com.rankexam.rtoinfo.R;
import com.rankexam.rtoinfo.databinding.ActivityFuelBinding;
import com.rankexam.rtoinfo.Model.StateListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class FuelCityActivity extends AppCompatActivity {

    FuelCityAdapter adapter;
    ActivityFuelBinding binding;
    SearchView editText;
    ArrayList<StateListModel> stateList;

    
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityFuelBinding inflate = ActivityFuelBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.stateList = new ArrayList<>();
        SearchView searchView = (SearchView) findViewById(R.id.editTextTextPersonName);
        this.editText = searchView;
        searchView.clearFocus();
        this.editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /* class com.deshkabijlibill.co.screens.fuel.FuelCityActivity.AnonymousClass1 */

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                FuelCityActivity.this.filterText(str);
                return true;
            }
        });

        findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                FuelCityActivity.this.onBackPressed();
            }
        });
        try {
            getdata();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fuelCityRec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FuelCityAdapter fuelCityAdapter = new FuelCityAdapter(this, this.stateList);
        this.adapter = fuelCityAdapter;
        recyclerView.setAdapter(fuelCityAdapter);
    }

    public String readFile(String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open(str), StandardCharsets.UTF_8));
        String str2 = "";
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return str2;
            }
            str2 = str2 + readLine;
        }
    }

    public void getdata() throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject(readFile("allcitystate.json"));
        for (int i = 0; i < jSONObject.length(); i++) {
            String valueOf = String.valueOf(jSONObject.names().get(i));
            this.stateList.add(new StateListModel(valueOf, "null"));
            JSONArray jSONArray = jSONObject.getJSONArray(valueOf);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                this.stateList.add(new StateListModel(jSONObject2.getString("cityName"), jSONObject2.getString("id")));
            }
        }
    }

    private void filterText(String str) {
        ArrayList<StateListModel> arrayList = new ArrayList<>();
        Iterator<StateListModel> it = this.stateList.iterator();
        while (it.hasNext()) {
            StateListModel next = it.next();
            if (next.getStateName().toLowerCase().contains(str.toLowerCase())) {
                arrayList.add(next);
            }
        }
        if (!arrayList.isEmpty()) {
            this.adapter.setFilteredList(arrayList);
        }
    }
}
