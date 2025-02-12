package com.natasaandzic.domaci1.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.natasaandzic.domaci1.R;
import com.natasaandzic.domaci1.adapter.PagerAdapter;
import com.natasaandzic.domaci1.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        PagerAdapter adapter;
        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(),"PRVI");
        //adapter.addFragment(new TabFragment2(), "DRUGI");
        //adapter.addFragment(new TabFragment3(), "TRECI");
        viewPager.setAdapter(adapter);
    }
}

