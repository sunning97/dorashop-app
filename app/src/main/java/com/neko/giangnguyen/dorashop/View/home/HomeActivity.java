package com.neko.giangnguyen.dorashop.View.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.neko.giangnguyen.dorashop.Adapter.ViewPagerAdapter;
import com.neko.giangnguyen.dorashop.R;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        tabLayout = findViewById(R.id.home_tablayout);
        viewPager = findViewById(R.id.home_view_pager);
        toolbar = (Toolbar) findViewById(R.id.home_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
