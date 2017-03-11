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
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.dashboard));

        TextView device = (TextView) view.findViewById(R.id.device);
        TextView cpu = (TextView) view.findViewById(R.id.cpu);
        TextView cpu_speed = (TextView) view.findViewById(R.id.cpu_speed);
        TextView ramSize = (TextView) view.findViewById(R.id.ram_size);

        device.setText(getDeviceName());
        cpu.setText(getCpuInfo());
        cpu_speed.setText(getCpuSpeed());
        ramSize.setText(getRamSize());

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

    public String capitalize(String s)
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

    public String getCpuInfo()
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

    public String getCpuSpeed()
    {
        for (int i = 0; i < 10; i++)
        {
            try
            {
                BufferedReader freqBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/sys/devices/system/cpu/cpu" + i + "/cpufreq/scaling_max_freq"))));
                String freq = String.valueOf(Integer.valueOf(freqBufferedReader.readLine()).intValue() / 1000);
                freqBufferedReader.close();
                return freq + "mHz";
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getRamSize()
    {
        RandomAccessFile reader = null;
        String load = null;
        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        double totRam = 0;
        String lastValue = "";
        try
        {
            reader = new RandomAccessFile("/proc/meminfo", "r");
            load = reader.readLine();

            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find())
            {
                value = m.group(1);
            }
            reader.close();

            totRam = Double.parseDouble(value);

            double mb = totRam / 1024.0;
            double gb = totRam / 1048576.0;
            double tb = totRam / 1073741824.0;

            if (tb > 1)
            {
                lastValue = twoDecimalForm.format(tb).concat(" TB");
            }
            else if (gb > 1)
            {
                lastValue = twoDecimalForm.format(gb).concat(" GB");
            }
            else if (mb > 1)
            {
                lastValue = twoDecimalForm.format(mb).concat(" MB");
            }
            else
            {
                lastValue = twoDecimalForm.format(totRam).concat(" KB");
            }

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return lastValue;
    }
}
