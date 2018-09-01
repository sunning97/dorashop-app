package com.neko.giangnguyen.dorashop.View.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.neko.giangnguyen.dorashop.Adapter.ExpandAdapter;
import com.neko.giangnguyen.dorashop.Adapter.HomeViewPagerAdapter;
import com.neko.giangnguyen.dorashop.Model.ObjectClass.Category;
import com.neko.giangnguyen.dorashop.Presenter.Home.DrawMenuProcess.DrawMenuProcess;
import com.neko.giangnguyen.dorashop.R;
import com.neko.giangnguyen.dorashop.View.Auth.AuthActivity;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements IShowMenuProcess {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    HomeViewPagerAdapter homeViewPagerAdapter;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());

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

        viewPager.setAdapter(homeViewPagerAdapter);
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

        int id = item.getItemId();

        switch (id){
            case R.id.home_menu_item_auth:
            {
                Intent authIntent = new Intent(HomeActivity.this, AuthActivity.class);
                startActivity(authIntent);
            }
                break;

        }

        return  true;
    }

    @Override
    public void showMenu(List<Category> listCategory) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this,listCategory);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }
}
