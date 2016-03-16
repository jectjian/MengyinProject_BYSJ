package com.example.index;

/**
 * 首页 --- 乐库Fragment
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mengyin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicStorageFragment extends Fragment {


    public MusicStorageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music_storage, container, false);
    }

}
