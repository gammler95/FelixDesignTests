package com.example.felixhahmann.felixdesigntests.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felixhahmann.felixdesigntests.R;
import com.example.felixhahmann.felixdesigntests.activities.MainActivity;


public class SensorFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.sensor));

        return view;
    }
}
