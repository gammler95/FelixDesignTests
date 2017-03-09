package com.example.felixhahmann.felixdesigntests.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.felixhahmann.felixdesigntests.R;
import com.example.felixhahmann.felixdesigntests.activities.MainActivity;
import com.google.zxing.integration.android.IntentIntegrator;

public class BarcodeScanFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_barcode_scan, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.barcode_scan));

        Button bStart = (Button) view.findViewById(R.id.scan);
        bStart.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                scanBarcode();
            }
        });

        getScanResult(view);

        return view;
    }

    public void scanBarcode()
    {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt(getResources().getString(R.string.barcode_camera_text));
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    public void getScanResult(View view)
    {
        TextView tv = (TextView) view.findViewById(R.id.result);
        tv.setText(((MainActivity)getActivity()).getBarcodeScanResult());
    }
}













