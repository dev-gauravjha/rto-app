package com.rankexam.rtoinfo.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.rankexam.rtoinfo.AdsManager;
import com.google.android.material.tabs.TabLayout;
import com.rankexam.rtoinfo.Adapter.PagerAdapter;
import com.rankexam.rtoinfo.Fragment.QuestFragment;
import com.rankexam.rtoinfo.Fragment.SignFragment;
import com.rankexam.rtoinfo.R;

public class SelectExamsPrepareActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    String str_language;
    TabLayout tabLayout;
    ViewPager vp;

    @Override
    public void onPageScrollStateChanged(int i) {
    }

    @Override
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override
    public void onPageSelected(int i) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_select_exam_prepare);
        AdsManager.getInstance().loadBanner(this);

        ((ImageView) findViewById(R.id.iv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectExamsPrepareActivity.this.onBackPressed();
            }
        });
        this.str_language = getIntent().getStringExtra("language");
        this.vp = (ViewPager) findViewById(R.id.mViewpager_ID);
        addPages();
        TabLayout tabLayout = (TabLayout) findViewById(R.id.mTab_ID);
        this.tabLayout = tabLayout;
        tabLayout.setTabGravity(0);
        this.tabLayout.setupWithViewPager(this.vp);
        this.tabLayout.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);
    }

    private void addPages() {
        PagerAdapter m_rtoPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        m_rtoPagerAdapter.addFragment(new QuestFragment(this.str_language));
        m_rtoPagerAdapter.addFragment(new SignFragment(this.str_language));
        this.vp.setAdapter(m_rtoPagerAdapter);
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        this.vp.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
