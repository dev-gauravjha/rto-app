package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.rankexam.rtoinfo.ListenerData.TaskHandler;
import com.google.gson.Gson;
import com.rankexam.rtoinfo.Model.VehiclseDetailRespondModel;
import com.rankexam.rtoinfo.R;
import org.json.JSONObject;


public class SearchVehicleDetailActivity extends AppCompatActivity {
    private String actionName;
    private String registrationNo;
    private String type;
    public VehiclseDetailRespondModel vehicleDetailsResponse;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_search_results_loader);
        this.registrationNo = getIntent().getStringExtra("REGISTRATION_NO");
        this.actionName = getIntent().getStringExtra("ACTION");
        this.type = getIntent().getStringExtra("TYPE");
        loadWebServerData(true);
    }

    private void loadWebServerData(boolean z) {
        TaskHandler.newInstance().fetchVehicleDetails(this, this.registrationNo, false, z, false, new TaskHandler.ResponseHandler<JSONObject>() {
            @Override
            public void onError(String str) {
                Log.i("loadWebServerData",str);
                Toast.makeText(SearchVehicleDetailActivity.this, "web Data Not Found!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(JSONObject jSONObject) {
                SearchVehicleDetailActivity.this.manipulateJsonResponse(jSONObject);
            }
        });
    }

    public void manipulateJsonResponse(JSONObject jSONObject) {
        Log.i("manipulateJsonResponse",new Gson().toJson(jSONObject).toString());
        try {
            VehiclseDetailRespondModel m_rtoVehiclseDetailRespond = (VehiclseDetailRespondModel) new Gson().fromJson(jSONObject.toString(), VehiclseDetailRespondModel.class);
            this.vehicleDetailsResponse = m_rtoVehiclseDetailRespond;
            if (m_rtoVehiclseDetailRespond.getStatusCode() != 200) {
                finish();
                Toast.makeText(this, "Vehicle Details Not Found!!", Toast.LENGTH_SHORT).show();
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        startActivity(new Intent(this, VehiclesDetailActivity.class).putExtra("detail", (Parcelable) this.vehicleDetailsResponse));
        finish();
    }
}
