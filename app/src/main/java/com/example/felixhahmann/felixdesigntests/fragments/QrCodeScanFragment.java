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

public class QrCodeScanFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_qr_code_scan, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.qr_code_scan));

        buttonScanQrCode(view);

        getScanResult(view);

        return view;
    }

    public void buttonScanQrCode(View view)
    {
        Button bSimpleBarcode = (Button) view.findViewById(R.id.qr_code_scan);
        bSimpleBarcode.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                scanQrCode();
            }
        });
    }

    public void scanQrCode()
    {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt(getResources().getString(R.string.qr_code_camera_text));
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }

    public void getScanResult(View view)
    {
        TextView tv = (TextView) view.findViewById(R.id.result);
        tv.setText(((MainActivity)getActivity()).getQrCodeScanResult());
    }
}













