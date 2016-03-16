package com.example.mengyin;

/**
 * 应用程序主入口
 */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.baseactivity.BaseActivity;

public class MainActivity extends BaseActivity {

    private RadioGroup main_tab_rg;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private IndexFragment indexFragment;
    private MusicManFragment musicManFragment;
    private CircleFragment circleFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        viewControl();
    }

    /**
     * view控制逻辑
     */
    private void viewControl() {
        //初始化Fragment管理器
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        //初始化Fragment实例
        indexFragment = new IndexFragment();
        musicManFragment = new MusicManFragment();
        circleFragment = new CircleFragment();
        mineFragment = new MineFragment();

        main_tab_rg.check(R.id.main_tab_index_rb);//默认选中首页
        fragmentTransaction.replace(R.id.main_tab_fragment,indexFragment);
        fragmentTransaction.commit();

        //RadioGroup的选择监听
        main_tab_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (checkedId){
                    case R.id.main_tab_index_rb://首页
                        transaction.replace(R.id.main_tab_fragment,indexFragment);
                        break;
                    case R.id.main_tab_musicman_rb://萌主
                        transaction.replace(R.id.main_tab_fragment,musicManFragment);
                        break;
                    case R.id.main_tab_add_rb://添加
                        break;
                    case R.id.main_tab_circle_rb://圈儿
                        transaction.replace(R.id.main_tab_fragment,circleFragment);
                        break;
                    case R.id.main_tab_mine_rb://我的
                        transaction.replace(R.id.main_tab_fragment,mineFragment);
                        break;
                }
                transaction.commit();
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        main_tab_rg = (RadioGroup) findViewById(R.id.main_tab_rg);
    }
}
