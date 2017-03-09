package com.example.felixhahmann.felixdesigntests.fragments;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.felixhahmann.felixdesigntests.R;
import com.example.felixhahmann.felixdesigntests.activities.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DashboardFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.dashboard));

        final TextView device = (TextView) view.findViewById(R.id.device);
        final TextView cpu = (TextView) view.findViewById(R.id.cpu);

        device.setText(getDeviceName());
        cpu.setText(getCpuInfo());

        return view;
    }

    public String getDeviceName()
    {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer))
        {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s)
    {
        if (s == null || s.length() == 0)
        {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first))
        {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


    private String getCpuInfo()
    {
        StringBuffer sb = new StringBuffer();
        if (new File("/proc/cpuinfo").exists())
        {
            try
            {
                BufferedReader br = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                sb.append(br.readLine());
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        String string = sb.toString();
        String substr = string.substring(12);

        return substr;
    }
}
