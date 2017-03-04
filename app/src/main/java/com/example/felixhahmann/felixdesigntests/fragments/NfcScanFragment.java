package com.example.felixhahmann.felixdesigntests.fragments;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.felixhahmann.felixdesigntests.R;
import com.example.felixhahmann.felixdesigntests.activities.MainActivity;


public class NfcScanFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_nfc_scan, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.nfc_scan));

        return view;
    }

    private void checkNfc()
    {
        Context context = getActivity().getApplicationContext();
        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();
        if (adapter == null || !adapter.isEnabled())
        {
            //NFCNotActivatedDialog dialog = new NFCNotActivatedDialog();
            //dialog.show(this.getFragmentManager(), "");
        }
    }
}
