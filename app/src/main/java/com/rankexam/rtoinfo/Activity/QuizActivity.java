package com.rankexam.rtoinfo.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.rankexam.rtoinfo.DataBase.DBHandler;
import com.rankexam.rtoinfo.Extra.QueConstructor;
import com.rankexam.rtoinfo.Extra.MemsUtil;
import com.rankexam.rtoinfo.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;




public class QuizActivity extends AppCompatActivity {
    private static final float BYTES_PER_PX = 4.0f;
    private static final String FORMAT = "%02d";
    Button btnNext;
    int curr;
    int curr1;
    QueConstructor currentquestion;
    int[] images;
    ImageView ivImage;
    ArrayList<String> myAnsList;
    ArrayList<QueConstructor> quesList;
    RadioButton radioButton;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioGroup rgChoice;
    String str_language;
    TextView tvNegative;
    TextView tvPositive;
    TextView tvQuestion;
    TextView tvTimer;
    ArrayList<String> Correctans = new ArrayList<>();
    ArrayList<String> Photo = new ArrayList<>();
    ArrayList<String> QuestionNumbers = new ArrayList<>();
    int Score = 0;
    int answeredquestno = 0;
    final ArrayList<Integer> array = new ArrayList<>();
    int f16k = 0;
    ArrayList<Integer> image = new ArrayList<>();
    int[] images_english = {1, 2, R.drawable.ic_symbol_1, 4, R.drawable.ic_symbol_70, 6, R.drawable.ic_symbol_3, 8, R.drawable.ic_symbol_81, 10, R.drawable.ic_symbol_8, 12, R.drawable.ic_symbol_6, 14, R.drawable.ic_symbol_7, 16, R.drawable.ic_symbol_72, 18, R.drawable.ic_symbol_9, 20, R.drawable.ic_symbol_10, 22, R.drawable.ic_symbol_71, 24, R.drawable.ic_symbol_83, 26, R.drawable.ic_symbol_79, 28, R.drawable.ic_symbol_11, 30, R.drawable.ic_symbol_12, 32, R.drawable.ic_symbol_84, 34, R.drawable.ic_symbol_14, 36, R.drawable.ic_symbol_15, 38, R.drawable.ic_symbol_4, 40, R.drawable.ic_symbol_75, 42, R.drawable.ic_symbol_17, 44, R.drawable.ic_symbol_85, 46, R.drawable.ic_symbol_69, 48, R.drawable.ic_symbol_86, 50, R.drawable.ic_symbol_21, 52, R.drawable.ic_symbol_22, 54, 55, R.drawable.ic_symbol_24, 57, R.drawable.ic_symbol_25, 59, R.drawable.ic_symbol_26, 61, R.drawable.ic_symbol_87, 63, R.drawable.ic_symbol_88, 65, R.drawable.ic_symbol_19, 67, R.drawable.ic_symbol_20, 69, R.drawable.ic_symbol_16, 71, R.drawable.ic_symbol_2, 73, R.drawable.ic_symbol_5, 75, R.drawable.ic_symbol_29, 77, R.drawable.ic_symbol_30, 79, R.drawable.ic_symbol_31, R.drawable.ic_symbol_32, 82, R.drawable.ic_symbol_33, 84, R.drawable.ic_symbol_89, R.drawable.ic_symbol_18, 87, R.drawable.ic_symbol_90, 89, R.drawable.ic_symbol_37, 91, R.drawable.ic_symbol_38, 93, R.drawable.ic_symbol_78, 95, R.drawable.ic_symbol_40, 97, R.drawable.ic_symbol_39, 99, R.drawable.ic_symbol_34, 108, R.drawable.ic_symbol_27, 103, R.drawable.ic_symbol_41, 105, R.drawable.ic_symbol_42, 107, R.drawable.ic_symbol_43, 109, R.drawable.ic_symbol_44, 111, R.drawable.ic_symbol_45, 113, R.drawable.ic_symbol_46, 117, R.drawable.ic_symbol_47, 119, R.drawable.ic_symbol_56, R.drawable.ic_symbol_49, 120, R.drawable.ic_symbol_50, 122, R.drawable.ic_symbol_51, 124, R.drawable.ic_symbol_52, 126, R.drawable.ic_symbol_53, 128, R.drawable.ic_symbol_54, 130, R.drawable.ic_symbol_55, R.drawable.ic_symbol_80, 133, R.drawable.ic_symbol_57, 135, R.drawable.ic_symbol_58, 137, R.drawable.ic_symbol_60, 139, R.drawable.ic_symbol_59, 141, R.drawable.ic_symbol_63, 143, R.drawable.ic_symbol_64, 145, R.drawable.ic_symbol_65, 147, R.drawable.ic_symbol_66, 149, R.drawable._symbol_91, 151, R.drawable.ic_symbol_92, 153, R.drawable.ic_symbol_93, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, R.drawable.ic_symbol_74, R.drawable.ic_symbol_94, 202, 203, R.drawable.ic_symbol_95, R.drawable.ic_symbol_96, R.drawable.ic_symbol_97, R.drawable.ic_symbol_98, R.drawable.ic_symbol_99, R.drawable.ic_symbol_100, 210, 211, 212, R.drawable.ic_symbol_76, R.drawable.ic_symbol_77, R.drawable.ic_symbol_123, R.drawable.ic_symbol_101, R.drawable.ic_symbol_102, R.drawable.ic_symbol_103, R.drawable.ic_symbol_104, R.drawable.ic_symbol_105, R.drawable.ic_symbol_106, R.drawable.ic_symbol_107, R.drawable.ic_symbol_61, R.drawable.ic_symbol_108, R.drawable.ic_symbol_48, R.drawable.ic_symbol_109, 227, 228, 229, 231, 232, 233, 232, 233, 234, 235, 236, 237, 238, 239, R.drawable.ic_symbol_110, R.drawable.ic_symbol_111, 242, 243, 244, 245, 246, 247, 248, 249, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, R.drawable.ic_symbol_112, R.drawable.ic_symbol_113, R.drawable.ic_symbol_114, R.drawable.ic_symbol_115, 304, 305, 306, 307, 308, 309, 310, R.drawable.ic_symbol_116, R.drawable.ic_symbol_117, R.drawable.ic_symbol_118, 314, 315, R.drawable.ic_symbol_119, R.drawable.ic_symbol_120, R.drawable.ic_symbol_121, R.drawable.ic_symbol_122, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, TypedValues.CycleType.TYPE_CURVE_FIT, TypedValues.CycleType.TYPE_VISIBILITY, TypedValues.CycleType.TYPE_ALPHA, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, TypedValues.CycleType.TYPE_PATH_ROTATE, 417, 418, 419, TypedValues.CycleType.TYPE_EASING, TypedValues.CycleType.TYPE_WAVE_SHAPE, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, TypedValues.CycleType.TYPE_WAVE_PERIOD, TypedValues.CycleType.TYPE_WAVE_OFFSET, TypedValues.CycleType.TYPE_WAVE_PHASE, 426, 427};
    int[] images_gujarati = {0, R.drawable.ic_symbol_2, R.drawable.ic_symbol_1, R.drawable.ic_symbol_48, R.drawable.ic_symbol_42, R.drawable.ic_symbol_43, R.drawable.ic_symbol_40, R.drawable.ic_symbol_39, R.drawable.ic_symbol_10, R.drawable.ic_symbol_38, R.drawable.ic_symbol_12, R.drawable.ic_symbol_11, R.drawable.ic_symbol_13, R.drawable.ic_symbol_18, R.drawable.ic_symbol_77, R.drawable.ic_symbol_32, R.drawable.ic_symbol_33, R.drawable.ic_symbol_31, R.drawable.ic_symbol_30, R.drawable.ic_symbol_72, R.drawable.ic_symbol_8, R.drawable.ic_symbol_23, R.drawable.ic_symbol_22, R.drawable.ic_symbol_16, R.drawable.ic_symbol_15, R.drawable.ic_symbol_27, R.drawable.ic_symbol_17, R.drawable.ic_symbol_7, R.drawable.ic_symbol_35, R.drawable.ic_symbol_46, R.drawable.ic_symbol_37, R.drawable.ic_symbol_34, R.drawable.ic_symbol_80, R.drawable.ic_symbol_81, R.drawable.ic_symbol_49, R.drawable.ic_symbol_50, R.drawable.ic_symbol_70, R.drawable.ic_symbol_19, R.drawable.ic_symbol_71, R.drawable.ic_symbol_73, R.drawable.ic_symbol_134, R.drawable.ic_symbol_75, R.drawable.ic_symbol_51, R.drawable.ic_symbol_14, R.drawable.ic_symbol_3, R.drawable.ic_symbol_4, R.drawable.ic_symbol_5, R.drawable.ic_symbol_29, R.drawable.ic_symbol_9, R.drawable.ic_symbol_6, R.drawable.ic_symbol_24, R.drawable.ic_symbol_79, R.drawable.ic_symbol_78, R.drawable.ic_symbol_45, R.drawable.ic_symbol_20, R.drawable.ic_symbol_74, R.drawable.ic_symbol_47, R.drawable.ic_symbol_55, R.drawable.ic_symbol_54, R.drawable.ic_symbol_41, R.drawable.ic_symbol_21, R.drawable.ic_symbol_69, R.drawable.ic_symbol_53, R.drawable.ic_symbol_56, R.drawable.ic_symbol_52, R.drawable.ic_symbol_44, R.drawable.ic_symbol_57, R.drawable.ic_symbol_61, R.drawable.ic_symbol_60, R.drawable.ic_symbol_59, R.drawable.ic_symbol_58, R.drawable.ic_symbol_45, R.drawable.ic_symbol_26, R.drawable.ic_symbol_25, R.drawable.ic_symbol_66, R.drawable.ic_symbol_67, R.drawable.ic_symbol_65, R.drawable.ic_symbol_63, R.drawable.ic_symbol_64, R.drawable.ic_symbol_204, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION};
    int[] images_hindi = {1, 2, R.drawable.ic_symbol_1, 4, R.drawable.ic_symbol_70, 6, R.drawable.ic_symbol_3, 8, R.drawable.ic_symbol_81, 10, R.drawable.ic_symbol_8, 12, R.drawable.ic_symbol_6, 14, R.drawable.ic_symbol_7, 16, R.drawable.ic_symbol_72, 18, R.drawable.ic_symbol_9, 20, R.drawable.ic_symbol_10, 22, R.drawable.ic_symbol_71, 24, R.drawable.ic_symbol_83, 26, R.drawable.ic_symbol_79, 28, R.drawable.ic_symbol_11, 30, R.drawable.ic_symbol_12, 32, R.drawable.ic_symbol_84, 34, R.drawable.ic_symbol_14, 36, R.drawable.ic_symbol_15, 38, R.drawable.ic_symbol_4, 40, R.drawable.ic_symbol_75, 42, R.drawable.ic_symbol_17, 44, R.drawable.ic_symbol_85, 46, R.drawable.ic_symbol_69, 48, R.drawable.ic_symbol_86, 50, R.drawable.ic_symbol_21, 52, R.drawable.ic_symbol_22, 54, 55, R.drawable.ic_symbol_24, 57, R.drawable.ic_symbol_25, 59, R.drawable.ic_symbol_26, 61, R.drawable.ic_symbol_87, 63, R.drawable.ic_symbol_88, 65, R.drawable.ic_symbol_19, 67, R.drawable.ic_symbol_20, 69, R.drawable.ic_symbol_16, 71, R.drawable.ic_symbol_2, 73, R.drawable.ic_symbol_5, 75, R.drawable.ic_symbol_29, 77, R.drawable.ic_symbol_30, 79, R.drawable.ic_symbol_31, R.drawable.ic_symbol_32, 82, R.drawable.ic_symbol_33, 84, R.drawable.ic_symbol_89, R.drawable.ic_symbol_18, 87, R.drawable.ic_symbol_90, 89, R.drawable.ic_symbol_37, 91, R.drawable.ic_symbol_38, 93, R.drawable.ic_symbol_78, 95, R.drawable.ic_symbol_40, 97, R.drawable.ic_symbol_39, 99, R.drawable.ic_symbol_34, 101, R.drawable.ic_symbol_27, 103, R.drawable.ic_symbol_41, 105, R.drawable.ic_symbol_42, 107, R.drawable.ic_symbol_43, 109, R.drawable.ic_symbol_44, 111, R.drawable.ic_symbol_45, 113, R.drawable.ic_symbol_46, 115, R.drawable.ic_symbol_47, 117, R.drawable.ic_symbol_56, R.drawable.ic_symbol_49, 120, R.drawable.ic_symbol_50, 122, R.drawable.ic_symbol_51, 124, R.drawable.ic_symbol_52, 126, R.drawable.ic_symbol_53, 128, R.drawable.ic_symbol_54, 130, R.drawable.ic_symbol_55, R.drawable.ic_symbol_80, 133, R.drawable.ic_symbol_57, 135, R.drawable.ic_symbol_58, 137, R.drawable.ic_symbol_60, 139, R.drawable.ic_symbol_59, 141, R.drawable.ic_symbol_63, 143, R.drawable.ic_symbol_64, 145, R.drawable.ic_symbol_65, 147, R.drawable.ic_symbol_66, 149, R.drawable._symbol_91, 151, R.drawable.ic_symbol_92, 153, R.drawable.ic_symbol_93, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 1, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, R.drawable.ic_symbol_74, R.drawable.ic_symbol_94, 202, 203, R.drawable.ic_symbol_95, R.drawable.ic_symbol_96, R.drawable.ic_symbol_97, R.drawable.ic_symbol_98, R.drawable.ic_symbol_99, R.drawable.ic_symbol_100, 210, 211, 212, R.drawable.ic_symbol_76, R.drawable.ic_symbol_77, R.drawable.ic_symbol_123, R.drawable.ic_symbol_101, R.drawable.ic_symbol_102, R.drawable.ic_symbol_103, R.drawable.ic_symbol_104, R.drawable.ic_symbol_105, R.drawable.ic_symbol_106, R.drawable.ic_symbol_107, R.drawable.ic_symbol_61, R.drawable.ic_symbol_108, R.drawable.ic_symbol_48, R.drawable.ic_symbol_109, 227, 228, 229, 231, 232, 233, 232, 233, 234, 235, 236, 237, 238, 239, R.drawable.ic_symbol_110, R.drawable.ic_symbol_111, 242, 243, 244, 245, 246, 247, 248, 249, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, R.drawable.ic_symbol_112, R.drawable.ic_symbol_113, R.drawable.ic_symbol_114, R.drawable.ic_symbol_115, 304, 305, 306, 307, 308, 309, 310, R.drawable.ic_symbol_116, R.drawable.ic_symbol_117, R.drawable.ic_symbol_118, 314, 315, R.drawable.ic_symbol_119, R.drawable.ic_symbol_120, R.drawable.ic_symbol_121, R.drawable.ic_symbol_122, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, TypedValues.CycleType.TYPE_CURVE_FIT, TypedValues.CycleType.TYPE_VISIBILITY, TypedValues.CycleType.TYPE_ALPHA, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, TypedValues.CycleType.TYPE_PATH_ROTATE, 417, 418, 419, TypedValues.CycleType.TYPE_EASING, TypedValues.CycleType.TYPE_WAVE_SHAPE, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, TypedValues.CycleType.TYPE_WAVE_PERIOD, TypedValues.CycleType.TYPE_WAVE_OFFSET, TypedValues.CycleType.TYPE_WAVE_PHASE, 426, 427};
    int negative = 0;
    String nophoto = "nophoto";
    ArrayList<Integer> numbers = new ArrayList<>();
    String photo = "photo";
    int positive = 0;
    int questionId = 0;
    final CounterClass timer = new CounterClass(31000, 1000);

    @Override
    public void onCreate(Bundle bundle) {
        int c = 0;
        super.onCreate(bundle);
        setContentView(R.layout.activity_quizes);
        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizActivity.this.onBackPressed();
            }
        });
        this.str_language = getIntent().getStringExtra("language");
        Log.d("R_Quiz", "Language is = " + this.str_language);
        String str = this.str_language;
        str.hashCode();
        int hashCode = str.hashCode();

            if (str.equals("english")) {
                c = 0;
            }
          //  c = CharCompanionObject.MAX_VALUE;
         else if (str.equals("gujarati")) {
            c = 1;
        } else if (str.equals("hindi")) {
                c = 2;
            }


        if (c == 0) {
            this.images = this.images_english;
        } else if (c == 1) {
            this.images = this.images_gujarati;
        } else if (c == 2) {
            this.images = this.images_hindi;
        }
        this.tvTimer = (TextView) findViewById(R.id.tvTimer);
        this.tvPositive = (TextView) findViewById(R.id.tvPositive);
        this.tvNegative = (TextView) findViewById(R.id.tvNegative);
        this.ivImage = (ImageView) findViewById(R.id.ivImage);
        this.tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        this.radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        this.radioButton = (RadioButton) findViewById(R.id.radioButton);
        this.radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        this.btnNext = (Button) findViewById(R.id.btnNext);
        if (this.str_language.equals("gujarati")) {
            this.btnNext.setText("આગામી પ્રશ્ન");
        } else if (this.str_language.equals("hindi")) {
            this.btnNext.setText("अगला सवाल");
        } else if (this.str_language.equals("english")) {
            this.btnNext.setText("Next Question");
        }
        this.myAnsList = new ArrayList<>();
        ArrayList<QueConstructor> allQuestions2 = new DBHandler(this).getAllQuestions2();
        this.quesList = allQuestions2;
        Collections.shuffle(allQuestions2);
        this.currentquestion = this.quesList.get(this.questionId);
        setQuestionsView();
        curr();
        this.timer.start();
        ButtonEnable();
        this.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizActivity.this.timer.cancel();
                QuizActivity.this.tvTimer.setText("");
                QuizActivity m_rtoQuizes = QuizActivity.this;
                m_rtoQuizes.rgChoice = (RadioGroup) m_rtoQuizes.findViewById(R.id.rgChoice);
                QuizActivity m_rtoQuizes2 = QuizActivity.this;
                RadioButton radioButton = (RadioButton) m_rtoQuizes2.findViewById(m_rtoQuizes2.rgChoice.getCheckedRadioButtonId());
                if (radioButton != null) {
                    QuizActivity.this.myAnsList.add("" + ((Object) radioButton.getText()));
                    if (QuizActivity.this.currentquestion.getAnswer().equals(radioButton.getText())) {
                        QuizActivity.this.Score++;
                        QuizActivity.this.positive++;
                        QuizActivity.this.tvPositive.setText("" + QuizActivity.this.positive);
                    } else {
                        QuizActivity.this.negative++;
                        QuizActivity.this.tvNegative.setText("" + QuizActivity.this.negative);
                    }
                    if (QuizActivity.this.Score == 9) {
                        QuizActivity.this.timer.cancel();
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("score", QuizActivity.this.Score);
                        bundle2.putStringArrayList("questionnumbers", QuizActivity.this.QuestionNumbers);
                        bundle2.putStringArrayList("myanswer", QuizActivity.this.myAnsList);
                        bundle2.putStringArrayList("Correct", QuizActivity.this.Correctans);
                        bundle2.putIntegerArrayList("Image", QuizActivity.this.image);
                        bundle2.putStringArrayList("photo", QuizActivity.this.Photo);
                        bundle2.putIntegerArrayList("Numbers", QuizActivity.this.numbers);
                        bundle2.putString("language", QuizActivity.this.str_language);
                        intent.putExtras(bundle2);
                        QuizActivity.this.startActivity(intent);
                        QuizActivity.this.finish();
                    } else if (QuizActivity.this.array.size() != 15) {
                        QuizActivity m_rtoQuizes3 = QuizActivity.this;
                        m_rtoQuizes3.currentquestion = m_rtoQuizes3.quesList.get(QuizActivity.this.questionId);
                        QuizActivity.this.setQuestionsView();
                        QuizActivity.this.curr();
                        QuizActivity.this.timer.start();
                    } else {
                        QuizActivity.this.timer.cancel();
                        Intent intent2 = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle bundle3 = new Bundle();
                        bundle3.putStringArrayList("questionnumbers", QuizActivity.this.QuestionNumbers);
                        bundle3.putStringArrayList("myanswer", QuizActivity.this.myAnsList);
                        bundle3.putStringArrayList("Correct", QuizActivity.this.Correctans);
                        bundle3.putIntegerArrayList("Image", QuizActivity.this.image);
                        bundle3.putStringArrayList("photo", QuizActivity.this.Photo);
                        bundle3.putIntegerArrayList("Numbers", QuizActivity.this.numbers);
                        bundle3.putInt("score", QuizActivity.this.Score);
                        bundle3.putString("language", QuizActivity.this.str_language);
                        intent2.putExtras(bundle3);
                        QuizActivity.this.startActivity(intent2);
                        QuizActivity.this.finish();
                    }
                    QuizActivity.this.rgChoice.clearCheck();
                } else if (radioButton == null) {
                    QuizActivity.this.timer.cancel();
                    QuizActivity.this.tvTimer.setText("");
                    if (QuizActivity.this.str_language.equals("gujarati")) {
                        QuizActivity.this.myAnsList.add("તમે આ પ્રશ્નનો જવાબ આપ્યો નથી");
                    } else if (QuizActivity.this.str_language.equals("hindi")) {
                        QuizActivity.this.myAnsList.add("आपने इस प्रश्न का उत्तर नहीं दिया है");
                    } else if (QuizActivity.this.str_language.equals("english")) {
                        QuizActivity.this.myAnsList.add("You have not answered this question");
                    }
                    QuizActivity.this.negative++;
                    QuizActivity.this.tvNegative.setText("" + QuizActivity.this.negative);
                    if (QuizActivity.this.Score == 9) {
                        QuizActivity.this.timer.cancel();
                        Intent intent3 = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle bundle4 = new Bundle();
                        bundle4.putInt("score", QuizActivity.this.Score);
                        bundle4.putStringArrayList("questionnumbers", QuizActivity.this.QuestionNumbers);
                        bundle4.putStringArrayList("myanswer", QuizActivity.this.myAnsList);
                        bundle4.putStringArrayList("Correct", QuizActivity.this.Correctans);
                        bundle4.putIntegerArrayList("Image", QuizActivity.this.image);
                        bundle4.putIntegerArrayList("Numbers", QuizActivity.this.numbers);
                        bundle4.putStringArrayList("photo", QuizActivity.this.Photo);
                        bundle4.putString("language", QuizActivity.this.str_language);
                        intent3.putExtras(bundle4);
                        QuizActivity.this.startActivity(intent3);
                        QuizActivity.this.finish();
                    } else if (QuizActivity.this.array.size() != 15) {
                        QuizActivity m_rtoQuizes4 = QuizActivity.this;
                        m_rtoQuizes4.currentquestion = m_rtoQuizes4.quesList.get(QuizActivity.this.questionId);
                        QuizActivity.this.setQuestionsView();
                        QuizActivity.this.curr();
                        QuizActivity.this.timer.start();
                    } else {
                        QuizActivity.this.timer.cancel();
                        Intent intent4 = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle bundle5 = new Bundle();
                        bundle5.putStringArrayList("questionnumbers", QuizActivity.this.QuestionNumbers);
                        bundle5.putStringArrayList("myanswer", QuizActivity.this.myAnsList);
                        bundle5.putStringArrayList("Correct", QuizActivity.this.Correctans);
                        bundle5.putIntegerArrayList("Image", QuizActivity.this.image);
                        bundle5.putStringArrayList("photo", QuizActivity.this.Photo);
                        bundle5.putInt("score", QuizActivity.this.Score);
                        bundle5.putIntegerArrayList("Numbers", QuizActivity.this.numbers);
                        bundle5.putString("language", QuizActivity.this.str_language);
                        intent4.putExtras(bundle5);
                        QuizActivity.this.startActivity(intent4);
                        QuizActivity.this.finish();
                    }
                }
                QuizActivity.this.rgChoice.clearCheck();
            }
        });
    }


    public class CounterClass extends CountDownTimer {
        public CounterClass(long j, long j2) {
            super(j, j2);
        }

        @Override
        public void onTick(long j) {
            TextView textView = QuizActivity.this.tvTimer;
            textView.setText("" + String.format("%02d", Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j)))));
        }

        @Override
        public void onFinish() {
            if (QuizActivity.this.array.size() != 15) {
                final Dialog dialog = new Dialog(QuizActivity.this);
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.dialog_exam_time_over);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setCanceledOnTouchOutside(false);
                dialog.setCancelable(false);
                dialog.show();
                TextView textView = (TextView) dialog.findViewById(R.id.tv_header);
                TextView textView2 = (TextView) dialog.findViewById(R.id.tv_sub_text);
                Button button = (Button) dialog.findViewById(R.id.btn_dia_next);
                if (QuizActivity.this.str_language.equals("gujarati")) {
                    textView.setText("સમય પુરો!!!");
                    textView2.setText(QuizActivity.this.getResources().getString(R.string.str_30_sec_over_txt_gujarati));
                    button.setText("આગળ");
                } else if (QuizActivity.this.str_language.equals("hindi")) {
                    textView.setText("समय समाप्त!!!");
                    textView2.setText(QuizActivity.this.getResources().getString(R.string.str_30_sec_over_txt_hindi));
                    button.setText("अगला सवाल");
                } else if (QuizActivity.this.str_language.equals("english")) {
                    textView.setText("Time Over!!!");
                    textView2.setText(QuizActivity.this.getResources().getString(R.string.str_30_sec_over_txt_english));
                    button.setText("Next Question");
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String str;
                        QuizActivity.this.rgChoice = (RadioGroup) QuizActivity.this.findViewById(R.id.rgChoice);
                        RadioButton radioButton = (RadioButton) QuizActivity.this.findViewById(QuizActivity.this.rgChoice.getCheckedRadioButtonId());
                        if (QuizActivity.this.str_language.equals("gujarati")) {
                            str = "તમે આ પ્રશ્નનો જવાબ આપ્યો નથી";
                        } else if (QuizActivity.this.str_language.equals("hindi")) {
                            str = "आपने इस प्रश्न का उत्तर नहीं दिया है";
                        } else {
                            str = QuizActivity.this.str_language.equals("english") ? "You have not answered this question" : "";
                        }
                        if (radioButton != null) {
                            QuizActivity.this.myAnsList.add(str);
                            QuizActivity.this.tvTimer.setText("");
                            QuizActivity.this.negative++;
                            QuizActivity.this.tvNegative.setText("" + QuizActivity.this.negative);
                            CounterClass.this.start();
                            QuizActivity.this.currentquestion = QuizActivity.this.quesList.get(QuizActivity.this.questionId);
                            QuizActivity.this.curr();
                            QuizActivity.this.setQuestionsView();
                            dialog.cancel();
                            return;
                        }
                        QuizActivity.this.myAnsList.add(str);
                        QuizActivity.this.tvTimer.setText("");
                        QuizActivity.this.negative++;
                        QuizActivity.this.tvNegative.setText("" + QuizActivity.this.negative);
                        CounterClass.this.start();
                        QuizActivity.this.currentquestion = QuizActivity.this.quesList.get(QuizActivity.this.questionId);
                        QuizActivity.this.curr();
                        QuizActivity.this.setQuestionsView();
                        dialog.cancel();
                    }
                });
                QuizActivity.this.rgChoice.clearCheck();
            } else {
                QuizActivity.this.array.size();
                final Dialog dialog2 = new Dialog(QuizActivity.this);
                dialog2.requestWindowFeature(1);
                dialog2.setContentView(R.layout.dialog_exam_over);
                dialog2.setCanceledOnTouchOutside(false);
                dialog2.setCancelable(false);
                dialog2.show();
                TextView textView3 = (TextView) dialog2.findViewById(R.id.tv_header);
                TextView textView4 = (TextView) dialog2.findViewById(R.id.tv_sub_text);
                Button button2 = (Button) dialog2.findViewById(R.id.btn_dia_next);
                if (QuizActivity.this.str_language.equals("gujarati")) {
                    textView3.setText("15 પ્રશ્નો સમાપ્ત થાય છે!!!");
                    textView4.setText(QuizActivity.this.getResources().getString(R.string.str_exam_over_txt_gujarati));
                    button2.setText("");
                } else if (QuizActivity.this.str_language.equals("hindi")) {
                    textView3.setText("15 सवाल खत्म हो चुके हो चुके है!!!");
                    textView4.setText(QuizActivity.this.getResources().getString(R.string.str_exam_over_txt_hindi));
                    button2.setText("अगला सवाल");
                } else if (QuizActivity.this.str_language.equals("english")) {
                    textView3.setText("15 questions are Over!!!");
                    textView4.setText(QuizActivity.this.getResources().getString(R.string.str_exam_over_txt_english));
                    button2.setText("Next Question");
                }
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String str;
                        QuizActivity.this.rgChoice = (RadioGroup) QuizActivity.this.findViewById(R.id.rgChoice);
                        RadioButton radioButton = (RadioButton) QuizActivity.this.findViewById(QuizActivity.this.rgChoice.getCheckedRadioButtonId());
                        if (QuizActivity.this.str_language.equals("gujarati")) {
                            str = "તમે આ પ્રશ્નનો જવાબ આપ્યો નથી";
                        } else if (QuizActivity.this.str_language.equals("hindi")) {
                            str = "आपने इस प्रश्न का उत्तर नहीं दिया है";
                        } else {
                            str = QuizActivity.this.str_language.equals("english") ? "You have not answered this question" : "";
                        }
                        if (radioButton != null) {
                            QuizActivity.this.timer.cancel();
                            QuizActivity.this.myAnsList.add(str);
                            Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("score", QuizActivity.this.Score);
                            bundle.putStringArrayList("myanswer", QuizActivity.this.myAnsList);
                            bundle.putStringArrayList("questionnumbers", QuizActivity.this.QuestionNumbers);
                            bundle.putStringArrayList("Correct", QuizActivity.this.Correctans);
                            bundle.putIntegerArrayList("Image", QuizActivity.this.image);
                            bundle.putIntegerArrayList("Numbers", QuizActivity.this.numbers);
                            bundle.putStringArrayList("photo", QuizActivity.this.Photo);
                            bundle.putString("language", QuizActivity.this.str_language);
                            intent.putExtras(bundle);
                            QuizActivity.this.startActivity(intent);
                            QuizActivity.this.finish();
                            dialog2.cancel();
                            return;
                        }
                        QuizActivity.this.timer.cancel();
                        QuizActivity.this.myAnsList.add(str);
                        Intent intent2 = new Intent(QuizActivity.this, ResultActivity.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt("score", QuizActivity.this.Score);
                        bundle2.putStringArrayList("myanswer", QuizActivity.this.myAnsList);
                        bundle2.putStringArrayList("questionnumbers", QuizActivity.this.QuestionNumbers);
                        bundle2.putStringArrayList("Correct", QuizActivity.this.Correctans);
                        bundle2.putIntegerArrayList("Image", QuizActivity.this.image);
                        bundle2.putIntegerArrayList("Numbers", QuizActivity.this.numbers);
                        bundle2.putStringArrayList("photo", QuizActivity.this.Photo);
                        bundle2.putString("language", QuizActivity.this.str_language);
                        intent2.putExtras(bundle2);
                        QuizActivity.this.startActivity(intent2);
                        QuizActivity.this.finish();
                        dialog2.cancel();
                    }
                });
            }
            QuizActivity.this.rgChoice.clearCheck();
        }
    }

    public void curr() {
        int id = this.currentquestion.getId();
        this.curr = id;
        this.array.add(Integer.valueOf(id));
    }

    public void ButtonEnable() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rgChoice);
        this.rgChoice = radioGroup;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup2, int i) {
                if (QuizActivity.this.radioButton.isChecked()) {
                    QuizActivity.this.btnNext.setEnabled(true);
                } else if (QuizActivity.this.radioButton2.isChecked()) {
                    QuizActivity.this.btnNext.setEnabled(true);
                } else if (QuizActivity.this.radioButton3.isChecked()) {
                    QuizActivity.this.btnNext.setEnabled(true);
                }
            }
        });
    }

    public void setQuestionsView() {
        this.radioButton2.setChecked(false);
        this.radioButton.setChecked(false);
        this.radioButton3.setChecked(false);
        this.f16k++;
        this.answeredquestno = this.questionId + 1;
        StringBuilder sb = new StringBuilder();
        if (this.str_language.equals("gujarati")) {
            this.btnNext.setText("આગામી પ્રશ્ન");
        } else if (this.str_language.equals("hindi")) {
            this.btnNext.setText("अगला सवाल");
        } else if (this.str_language.equals("english")) {
            this.btnNext.setText("Next Question");
        }
        sb.append("");
        sb.append(this.f16k);
        sb.append(" ");
        sb.append(this.currentquestion.getQuestion());
        this.tvQuestion.setText(sb.toString());
        this.radioButton3.setText(this.currentquestion.getOption3());
        this.radioButton.setText(this.currentquestion.getOption1());
        this.radioButton2.setText(this.currentquestion.getOption2());
        this.QuestionNumbers.add("" + this.currentquestion.getQuestion());
        this.Correctans.add("" + this.currentquestion.getAnswer());
        this.Photo.add("" + this.currentquestion.getPhoto());
        int id = this.currentquestion.getId();
        this.curr1 = id;
        this.image.add(Integer.valueOf(id));
        this.numbers.add(Integer.valueOf(this.f16k));
        if (this.photo.equals(this.currentquestion.getPhoto())) {
            this.ivImage.setVisibility(View.VISIBLE);
            loadImage();
        } else if (this.nophoto.equals(this.currentquestion.getPhoto())) {
            this.ivImage.setVisibility(View.GONE);
        }
        this.questionId++;
    }

    private void loadImage() {
        if (readBitmapInfo() > MemsUtil.megabytesFree()) {
            subSampleImage(32);
        } else {
            this.ivImage.setImageResource(this.images[this.currentquestion.getId()]);
        }
    }

    private float readBitmapInfo() {
        Resources resources = getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, this.images[this.currentquestion.getId()], options);
        String str = options.outMimeType;
        return ((options.outWidth * options.outHeight) * BYTES_PER_PX) / 1048576.0f;
    }

    private void subSampleImage(int i) {
        if (i < 1 || i > 32) {
            return;
        }
        Resources resources = getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        this.ivImage.setImageBitmap(BitmapFactory.decodeResource(resources, this.images[this.currentquestion.getId()], options));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.timer.cancel();
    }
}
