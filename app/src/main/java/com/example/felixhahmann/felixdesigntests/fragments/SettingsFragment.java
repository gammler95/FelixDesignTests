package com.example.felixhahmann.felixdesigntests.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Switch;
import 	android.app.UiModeManager;

import com.example.felixhahmann.felixdesigntests.R;
import com.example.felixhahmann.felixdesigntests.activities.MainActivity;


public class SettingsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.settings));

        Switch nightmode_switch = (Switch)view.findViewById(R.id.nightmode_switch);
        CheckBox german_checkBox = (CheckBox)view.findViewById(R.id.german_checkBox);
        CheckBox english_checkBox = (CheckBox)view.findViewById(R.id.english_checkBox);

        setCurrentNightmodeSwitch(nightmode_switch);
        setCurrentCheckBoxes(german_checkBox, english_checkBox);


        //Nightmode geht noch nicht. Er aktiviert sich nicht richtig. Siehe neues color.xml
        nightmode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    //Nightmode aktivieren
                    UiModeManager umm = (UiModeManager) getContext().getSystemService(Context.UI_MODE_SERVICE);
                    umm.setNightMode(UiModeManager.MODE_NIGHT_YES);
                    refreshActivity();
                }
                else
                {
                    //Nightmode deaktivieren
                    UiModeManager umm = (UiModeManager) getContext().getSystemService(Context.UI_MODE_SERVICE);
                    umm.setNightMode(UiModeManager.MODE_NIGHT_NO);
                    refreshActivity();
                }
            }
        });




        buttonGerman(german_checkBox);

        buttonEnglish(english_checkBox);

        return view;
    }

    public void setCurrentNightmodeSwitch(Switch nightmode_switch)
    {
        UiModeManager umm = (UiModeManager) getContext().getSystemService(Context.UI_MODE_SERVICE);
        int currentNightMode = umm.getNightMode();

        if(currentNightMode == 0 || currentNightMode == 1)
        {
            nightmode_switch.setChecked(false);
        }
        else if(currentNightMode == 2)
        {
            nightmode_switch.setChecked(true);
        }
    }

    public void setCurrentCheckBoxes(CheckBox german_checkBox, CheckBox english_checkBox)
    {
        Locale current = getResources().getConfiguration().locale;

        if (current.toString().equals("en"))
        {
            german_checkBox.setChecked(false);
            english_checkBox.setChecked(true);
        }
        else
        {
            german_checkBox.setChecked(true);
            english_checkBox.setChecked(false);
        }
    }

    public void buttonGerman(CheckBox german_checkBox)
    {
        german_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    setLocale("de");
                }
            }
        });
    }

    public void buttonEnglish(CheckBox english_checkBox)
    {
        english_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    setLocale("en");
                }
            }
        });
    }

    public void setLocale(String lang)
    {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        refreshActivity();
    }

    public void refreshActivity()
    {
        Intent refresh = new Intent(getContext(), MainActivity.class);
        startActivity(refresh);
        getActivity().finish();
    }
}
