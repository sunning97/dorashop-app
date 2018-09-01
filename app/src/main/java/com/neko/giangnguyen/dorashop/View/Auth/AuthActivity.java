package com.neko.giangnguyen.dorashop.View.Auth;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.neko.giangnguyen.dorashop.Adapter.AuthViewPagerAdapter;
import com.neko.giangnguyen.dorashop.R;

public class AuthActivity extends AppCompatActivity {
    TabLayout tabAuth;
    ViewPager viewPagerAuth;
    Toolbar authToolbar;
    AuthViewPagerAdapter authViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        authToolbar = findViewById(R.id.auth_toolbar);
        tabAuth = findViewById(R.id.auth_tab);
        viewPagerAuth = findViewById(R.id.auth_view_pager);

        authToolbar.setTitle("Đăng nhập/Đăng ký");
        setSupportActionBar(authToolbar);

        authViewPagerAdapter = new AuthViewPagerAdapter(getSupportFragmentManager());
        viewPagerAuth.setAdapter(authViewPagerAdapter);
        authViewPagerAdapter.notifyDataSetChanged();
        tabAuth.setupWithViewPager(viewPagerAuth);
    }
}
