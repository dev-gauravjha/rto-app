package com.rankexam.rtoinfo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.rankexam.rtoinfo.Adapter.ResultLstAdpter;
import com.facebook.ads.InterstitialAd;
import com.rankexam.rtoinfo.DataBase.DBHandler;
import com.rankexam.rtoinfo.Extra.QueConstructor;
import com.rankexam.rtoinfo.R;
import java.util.ArrayList;
import java.util.HashMap;


public class ResultCardsActivity extends AppCompatActivity {
    public static String KEY_CANS = "canswer";
    public static String KEY_Photo = "Pphoto";
    public static String KEY_QUES = "questions";
    public static String KEY_YANS = "yanswer";
    public static String key_image;
    public static String key_int;
    ResultLstAdpter adapter;
    Button btnHome;
    Button btnRetry;
    private InterstitialAd interstitialAd;
    ListView lvAnswers;
    ArrayList<QueConstructor> questionList;
    String str_language;
    ArrayList<HashMap<String, Object>> originalValues = new ArrayList<>();
    ArrayList<HashMap<String, Integer>> originalValues1 = new ArrayList<>();
    HashMap<String, Object> temp = new HashMap<>();
    HashMap<String, Integer> temp2 = new HashMap<>();

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_result_cards);
        Bundle extras = getIntent().getExtras();

        ArrayList<String> stringArrayList = extras.getStringArrayList("myanswerlist");
        ArrayList<String> stringArrayList2 = extras.getStringArrayList("Questionnumbers");
        ArrayList<String> stringArrayList3 = extras.getStringArrayList("Correct");
        ArrayList<Integer> integerArrayList = extras.getIntegerArrayList("image");
        ArrayList<String> stringArrayList4 = extras.getStringArrayList("photo");
        ArrayList<Integer> integerArrayList2 = extras.getIntegerArrayList("numbers");
        this.str_language = extras.getString("language");
        this.lvAnswers = (ListView) findViewById(R.id.lvAnwers);
        this.btnHome = (Button) findViewById(R.id.btnHome);
        this.btnRetry = (Button) findViewById(R.id.btnRetry);
        if (this.str_language.equals("gujarati")) {
            this.btnHome.setText("હોમ");
            this.btnRetry.setText("ફરી પ્રયાસ ક્વિઝ");
        } else if (this.str_language.equals("hindi")) {
            this.btnHome.setText("होम");
            this.btnRetry.setText("फिर से प्रयास करें");
        } else if (this.str_language.equals("english")) {
            this.btnHome.setText("Home");
            this.btnRetry.setText("Try again Quiz");
        }
        this.questionList = new DBHandler(this).getAllQuestions2();
        for (int i = 0; i < stringArrayList2.size(); i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            this.temp = hashMap;
            hashMap.put(KEY_QUES, stringArrayList2.get(i));
            this.temp.put(KEY_CANS, stringArrayList3.get(i));
            this.temp.put(KEY_YANS, stringArrayList.get(i));
            this.temp.put(KEY_Photo, stringArrayList4.get(i));
            this.temp2.put(key_image, integerArrayList.get(i));
            this.temp2.put(key_int, integerArrayList2.get(i));
            this.originalValues.add(this.temp);
            this.originalValues1.add(this.temp2);
        }
        ResultLstAdpter m_rtoResultLstAdpter = new ResultLstAdpter(this, R.layout.view_ans_custom, this.originalValues, this.originalValues1, integerArrayList, integerArrayList2, this.str_language);
        this.adapter = m_rtoResultLstAdpter;
        this.lvAnswers.setAdapter((ListAdapter) m_rtoResultLstAdpter);
        this.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResultCardsActivity.this.onBackPressed();
            }
        });
        this.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultCardsActivity.this.getApplicationContext(), QuizActivity.class);
                intent.putExtra("language", ResultCardsActivity.this.str_language);
                ResultCardsActivity.this.startActivity(intent);
                ResultCardsActivity.this.finish();
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
