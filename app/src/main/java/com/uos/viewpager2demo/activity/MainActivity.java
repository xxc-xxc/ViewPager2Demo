package com.uos.viewpager2demo.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.uos.viewpager2demo.R;
import com.uos.viewpager2demo.adapter.MainFragmentPagerAdapter;
import com.uos.viewpager2demo.fragment.ClassifyFragment;
import com.uos.viewpager2demo.fragment.RecommendFragment;
import com.uos.viewpager2demo.fragment.TopicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tl_navigation)
    TabLayout tlNavigation;
    @BindView(R.id.vp2_content)
    ViewPager2 vp2Content;

    private List<Fragment> mFragments;
    private ArrayList<String> mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        setData();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setListener() {
        vp2Content.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void setData() {
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(this, mFragments);
        vp2Content.setOffscreenPageLimit(1);
        vp2Content.setAdapter(adapter);

        // ViewPager2关联TabLayout
        new TabLayoutMediator(tlNavigation, vp2Content,
                (tab, position) -> tab.setText(mTitles.get(position))).attach();
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new RecommendFragment());
        mFragments.add(new ClassifyFragment());
        mFragments.add(new TopicFragment());

        mTitles = new ArrayList<>();
        mTitles.add("推荐");
        mTitles.add("分类");
        mTitles.add("专题");
    }
}