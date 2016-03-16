package com.example.pageadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jectjian on 2016/3/14.
 * Email : jectjian@126.com
 * ViewPager适配器
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter{

    private List<Fragment> list;

    public MyFragmentPageAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
