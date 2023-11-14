package com.perfbrainstorming.happyguessing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.perfbrainstorming.happyguessing.R;

public class ThirdFragment extends Fragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_third_fragment, container, false);

        return view;
    }


}
