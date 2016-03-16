package com.example.mengyin;

/**
 * 首页Fragment
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.index.MusicListFragment;
import com.example.index.MusicStorageFragment;
import com.example.index.RecommendFragment;
import com.example.pageadapter.MyFragmentPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment {

    private View view;
    private RadioGroup index_tab_rg;
    private RadioButton index_tab_tuijian_rb,index_tab_yueku_rb,index_tab_bangdan_rb;
    private ViewPager index_viewpager;
    private List<Fragment> list;
    private MyFragmentPageAdapter myFragmentPageAdapter;

    public IndexFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index, container, false);

        initView();

        viewControl();

        return view;
    }

    /**
     * view控制逻辑
     */
    private void viewControl() {

        //将三个Fragment对象添加到List集合中并添加到适配器显示
        list = new ArrayList<>();
        list.add(new RecommendFragment());
        list.add(new MusicStorageFragment());
        list.add(new MusicListFragment());
        myFragmentPageAdapter = new MyFragmentPageAdapter(getFragmentManager(),list);
        index_viewpager.setAdapter(myFragmentPageAdapter);

        //默认选中第一项
        setTabTextSize(0);
        index_tab_rg.check(R.id.index_tab_tuijian_rb);
        index_viewpager.setCurrentItem(0);//设置当前显示推荐页

        index_tab_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.index_tab_tuijian_rb://推荐
                        setTabTextSize(0);
                        index_viewpager.setCurrentItem(0);//设置当前显示的Fragment
                        break;
                    case R.id.index_tab_yueku_rb://乐库
                        setTabTextSize(1);
                        index_viewpager.setCurrentItem(1);
                        break;
                    case R.id.index_tab_bangdan_rb://榜单
                        setTabTextSize(2);
                        index_viewpager.setCurrentItem(2);
                        break;
                }
            }
        });

        //ViewPager的监听
        index_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTabTextSize(position);
                index_viewpager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        index_tab_rg = (RadioGroup) view.findViewById(R.id.index_tab_rg);
        index_tab_tuijian_rb = (RadioButton) view.findViewById(R.id.index_tab_tuijian_rb);
        index_tab_yueku_rb = (RadioButton) view.findViewById(R.id.index_tab_yueku_rb);
        index_tab_bangdan_rb = (RadioButton) view.findViewById(R.id.index_tab_bangdan_rb);
        index_viewpager = (ViewPager) view.findViewById(R.id.index_viewpager);
    }

    /**
     * 首页标签高亮显示
     */
    private void setTabTextSize(int index){
        switch (index){
            case 0:
                index_tab_tuijian_rb.setTextSize(18);
                index_tab_yueku_rb.setTextSize(15);
                index_tab_bangdan_rb.setTextSize(15);
                break;
            case 1:
                index_tab_tuijian_rb.setTextSize(15);
                index_tab_yueku_rb.setTextSize(18);
                index_tab_bangdan_rb.setTextSize(15);
                break;
            case 2:
                index_tab_tuijian_rb.setTextSize(15);
                index_tab_yueku_rb.setTextSize(15);
                index_tab_bangdan_rb.setTextSize(18);
                break;
        }
    }

}
