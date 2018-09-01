package com.neko.giangnguyen.dorashop.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.neko.giangnguyen.dorashop.View.Auth.Fragment.Login;
import com.neko.giangnguyen.dorashop.View.Auth.Fragment.Register;

import java.util.ArrayList;
import java.util.List;

public class AuthViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragments;
    private List<String> fragments_title;

    public AuthViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragments_title = new ArrayList<>();

        fragments.add(new Login());
        fragments.add(new Register());

        fragments_title.add("Đăng nhập");
        fragments_title.add("Đăng ký");
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.fragments_title.get(position);
    }
}
