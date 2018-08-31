package com.neko.giangnguyen.dorashop.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.neko.giangnguyen.dorashop.View.Home.fragment.Fashion;
import com.neko.giangnguyen.dorashop.View.Home.fragment.HighLights;
import com.neko.giangnguyen.dorashop.View.Home.fragment.Technology;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private List<String> fragments_title;
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments = new ArrayList<>();
        fragments_title = new ArrayList<>();

        fragments.add(new HighLights());
        fragments.add(new Fashion());
        fragments.add(new Technology());

        fragments_title.add("Nổi bật");
        fragments_title.add("Thời trang");
        fragments_title.add("Công nghệ");
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments_title.get(position);
    }
}
