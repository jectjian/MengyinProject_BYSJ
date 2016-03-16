package com.example.baseactivity;

/**
 * 所有Activity的基类
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mengyin.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        getSupportActionBar().hide();//去除所有Activity的ActionBar
    }

}
