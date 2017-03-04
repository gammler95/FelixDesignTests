package com.example.felixhahmann.felixdesigntests.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.Locale;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.felixhahmann.felixdesigntests.R;
import com.example.felixhahmann.felixdesigntests.activities.LogInActivity;
import com.example.felixhahmann.felixdesigntests.activities.MainActivity;


public class SettingsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.settings));

        final CheckBox german_checkBox = (CheckBox)view.findViewById( R.id.german_checkBox);
        final CheckBox english_checkBox = (CheckBox)view.findViewById( R.id.english_checkBox);


        //***********************

        /*
        int language = ((LogInActivity) this.getActivity()).getLanguage();
        switch (language)
        {
            case 0:
                german_checkBox.setChecked(true);
                break;

            case 1:
                english_checkBox.setChecked(true);
                break;
        }
        */

        german_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    //((LogInActivity) getActivity()).setLanguage(0);

                    Locale myLocale = new Locale("de_DE");
                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    Configuration conf = res.getConfiguration();
                    conf.locale = myLocale;
                    res.updateConfiguration(conf, dm);
                    Intent refresh = new Intent(getActivity(), MainActivity.class);
                    //refresh.putExtra("language", true);
                    startActivity(refresh);
                }
            }
        });

        english_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    //((LogInActivity) getActivity()).setLanguage(1);

                    Locale myLocale = new Locale("en_US");
                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    Configuration conf = res.getConfiguration();
                    conf.locale = myLocale;
                    res.updateConfiguration(conf, dm);
                    Intent refresh = new Intent(getActivity(), MainActivity.class);
                    //refresh.putExtra("language", false);
                    startActivity(refresh);
                }
            }
        });

        return view;
    }
}
