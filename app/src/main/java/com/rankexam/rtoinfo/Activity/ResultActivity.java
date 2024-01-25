package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.rankexam.rtoinfo.AdsManager;
import com.facebook.ads.InterstitialAd;
import com.rankexam.rtoinfo.R;
import java.util.ArrayList;


public class ResultActivity extends AppCompatActivity {
    Button btnHome;
    Button btnViewResult;
    private InterstitialAd interstitialAd;
    ImageView ivSmiley;
    String str_language;
    TextView tvResult;
    TextView tvScore;
    TextView tv_title;

    @Override
    public void onCreate(Bundle bundle) {
        ArrayList<Integer> arrayList;
        ArrayList<String> arrayList2;
        super.onCreate(bundle);
        setContentView(R.layout.activity_results);

        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.tvResult = (TextView) findViewById(R.id.tvResult);
        this.tvScore = (TextView) findViewById(R.id.tvScore);
        this.ivSmiley = (ImageView) findViewById(R.id.ivSmiley);
        this.btnHome = (Button) findViewById(R.id.btnHome);
        this.btnViewResult = (Button) findViewById(R.id.btnViewResult);
        Bundle extras = getIntent().getExtras();
        int i = extras.getInt("score");
        final ArrayList<String> stringArrayList = extras.getStringArrayList("myanswer");
        final ArrayList<String> stringArrayList2 = extras.getStringArrayList("questionnumbers");
        final ArrayList<String> stringArrayList3 = extras.getStringArrayList("Correct");
        final ArrayList<Integer> integerArrayList = extras.getIntegerArrayList("Image");
        ArrayList<Integer> integerArrayList2 = extras.getIntegerArrayList("Numbers");
        ArrayList<String> stringArrayList4 = extras.getStringArrayList("photo");
        String string = extras.getString("language");
        this.str_language = string;
        if (string.equals("gujarati")) {
            this.btnHome.setText("હોમ");
            this.btnViewResult.setText("જવાબો જુઓ");
        } else if (this.str_language.equals("hindi")) {
            this.btnHome.setText("होम");
            this.btnViewResult.setText("जवाब देखिए");
        } else if (this.str_language.equals("english")) {
            this.btnHome.setText("Home");
            this.btnViewResult.setText("See Answers");
        }
        if (i >= 9) {
            if (this.str_language.equals("gujarati")) {
                this.tv_title.setText("પરિણામ");
                this.tvResult.setText("અભિનંદન, તમે પરીક્ષા પાસ કરી છે");
            } else if (this.str_language.equals("hindi")) {
                this.tv_title.setText("परिणाम");
                this.tvResult.setText("बधाई हो, आपने परीक्षा उत्तीर्ण कर ली है");
            } else if (this.str_language.equals("english")) {
                this.tv_title.setText("Result");
                this.tvResult.setText("Congratulations , You have passed the exam");
            }
            this.ivSmiley.setImageResource(R.drawable.emojis_happy);
            this.tvResult.setTextColor(Color.parseColor("#ff99cc00"));
            this.tvScore.setTextColor(Color.parseColor("#ff99cc00"));
            arrayList2 = stringArrayList4;
            arrayList = integerArrayList2;
        } else {
            arrayList = integerArrayList2;
            arrayList2 = stringArrayList4;
            if (this.str_language.equals("gujarati")) {
                this.tv_title.setText("પરિણામ");
                this.tvResult.setText("તમે નિષ્ફળ ગયા છો");
            } else if (this.str_language.equals("hindi")) {
                this.tv_title.setText("परिणाम");
                this.tvResult.setText("आप असफल हुए है");
            } else if (this.str_language.equals("english")) {
                this.tv_title.setText("Result");
                this.tvResult.setText("You have not cleared exam");
            }
            this.ivSmiley.setImageResource(R.drawable.emojis_sad);
            if (this.str_language.equals("gujarati")) {
                this.tvResult.setText("તમે નિષ્ફળ ગયા છો");
            } else if (this.str_language.equals("hindi")) {
                this.tvResult.setText("आप असफल हुए है");
            } else if (this.str_language.equals("english")) {
                this.tvResult.setText("You have not cleared exam");
            }
            this.tvResult.setTextColor(Color.parseColor("#ffff4444"));
            this.tvScore.setTextColor(Color.parseColor("#ffff4444"));
        }
        if (this.str_language.equals("gujarati")) {
            TextView textView = this.tvScore;
            textView.setText("તમારો સ્કોર : " + i + "\n\n પાસ સ્કોર : 9");
        } else if (this.str_language.equals("hindi")) {
            TextView textView2 = this.tvScore;
            textView2.setText("आपका स्कोर : " + i + "\n\n पास स्कोर : 9");
        } else if (this.str_language.equals("english")) {
            TextView textView3 = this.tvScore;
            textView3.setText("Your Score : " + i + "\n\n Pass score : 9");
        }
        this.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultActivity.this.onBackPressed();
            }
        });
        final ArrayList<String> arrayList3 = arrayList2;
        final ArrayList<Integer> arrayList4 = arrayList;
        this.btnViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, ResultCardsActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putStringArrayList("myanswerlist", stringArrayList);
                bundle2.putStringArrayList("Questionnumbers", stringArrayList2);
                bundle2.putStringArrayList("Correct", stringArrayList3);
                bundle2.putIntegerArrayList("image", integerArrayList);
                bundle2.putStringArrayList("photo", arrayList3);
                bundle2.putIntegerArrayList("numbers", arrayList4);
                bundle2.putString("language", ResultActivity.this.str_language);
                intent.putExtras(bundle2);

                AdsManager.getInstance().showInterstitialAd(ResultActivity.this, new AdsManager.AdCloseListener() {
                    @Override
                    public void onAdClosed() {
                        ResultActivity.this.startActivity(intent);
                        ResultActivity.this.finish();
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
