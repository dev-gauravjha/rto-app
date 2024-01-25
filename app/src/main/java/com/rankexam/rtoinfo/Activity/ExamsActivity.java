package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.rankexam.rtoinfo.AdsManager;
import com.rankexam.rtoinfo.R;


public class ExamsActivity extends AppCompatActivity {
    Button btnStart;

    String str_language;
    TextView tv_header;
    TextView tv_instructions;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_exam);
        AdsManager.getInstance().loadBanner(this);

        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExamsActivity.this.onBackPressed();
            }
        });
        this.tv_header = (TextView) findViewById(R.id.tv_header);
        this.tv_instructions = (TextView) findViewById(R.id.tv_instructions);
        this.btnStart = (Button) findViewById(R.id.btnStart);
        String stringExtra = getIntent().getStringExtra("language");
        this.str_language = stringExtra;
        if (stringExtra.equals("gujarati")) {
            this.btnStart.setText("પ્રારંભ પરીક્ષા");
            this.tv_header.setText("સૂચનાઓ");
            this.tv_instructions.setText(getResources().getString(R.string.bullet_ed_list_gujarati));
        } else if (this.str_language.equals("hindi")) {
            this.btnStart.setText("परीक्षा प्रारंभ करें");
            this.tv_header.setText("सूचनाएं");
            this.tv_instructions.setText(getResources().getString(R.string.bullet_ed_list_hindi));
        } else if (this.str_language.equals("english")) {
            this.btnStart.setText("Start Exam");
            this.tv_header.setText("Instructions");
            this.tv_instructions.setText(getResources().getString(R.string.bullet_ed_list_english));
        }
        this.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExamsActivity.this, QuizActivity.class);
                intent.putExtra("language", "" + ExamsActivity.this.str_language);

                AdsManager.getInstance().showInterstitialAd(ExamsActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        ExamsActivity.this.startActivity(intent);
                        ExamsActivity.this.finish();
                    }
                });
            }
        });
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
