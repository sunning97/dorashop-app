package com.neko.giangnguyen.dorashop.View.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.neko.giangnguyen.dorashop.Adapter.ExpandAdapter;
import com.neko.giangnguyen.dorashop.Adapter.ViewPagerAdapter;
import com.neko.giangnguyen.dorashop.Model.ObjectClass.Category;
import com.neko.giangnguyen.dorashop.Presenter.Home.DrawMenuProcess.DrawMenuProcess;
import com.neko.giangnguyen.dorashop.R;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements IShowMenuProcess {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        drawerLayout = findViewById(R.id.home_draw_layout);


        tabLayout = findViewById(R.id.home_tablayout);
        viewPager = findViewById(R.id.home_view_pager);
        toolbar = findViewById(R.id.home_bar);

        expandableListView = findViewById(R.id.home_list_draw);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        DrawMenuProcess menuProcess = new DrawMenuProcess(this);

        menuProcess.getDataMenu();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMenu(List<Category> listCategory) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this,listCategory);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }
}
