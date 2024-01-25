package com.rankexam.rtoinfo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.rankexam.rtoinfo.DataBase.DBHandler;
import com.rankexam.rtoinfo.Adapter.QuestionListAdapter;
import com.rankexam.rtoinfo.R;
import java.util.ArrayList;


public class QuestFragment extends Fragment {
    ListView ivQuestions;
    ArrayList questionList;
    String str_language;

    public QuestFragment(String str) {
        this.str_language = str;
    }

    @Override
    public String toString() {
        if (this.str_language.equals("gujarati")) {
            return "પ્રશ્નો";
        }
        if (this.str_language.equals("hindi")) {
            return "प्रश्न";
        }
        this.str_language.equals("english");
        return "Questions";
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.question_fragment, viewGroup, false);
        this.ivQuestions = (ListView) inflate.findViewById(R.id.firstListView);
        this.questionList = new DBHandler(getContext()).getAllQuestions();
        this.ivQuestions.setAdapter((ListAdapter) new QuestionListAdapter(getActivity(), this.questionList, this.str_language));
        return inflate;
    }
}
