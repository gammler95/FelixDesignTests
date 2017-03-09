package com.example.felixhahmann.felixdesigntests.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.felixhahmann.felixdesigntests.R;
import com.example.felixhahmann.felixdesigntests.fragments.QrCodeScanFragment;
import com.example.felixhahmann.felixdesigntests.fragments.DashboardFragment;
import com.example.felixhahmann.felixdesigntests.fragments.InfoFragment;
import com.example.felixhahmann.felixdesigntests.fragments.NfcScanFragment;
import com.example.felixhahmann.felixdesigntests.fragments.NotificationsFragment;
import com.example.felixhahmann.felixdesigntests.fragments.SensorFragment;
import com.example.felixhahmann.felixdesigntests.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
{
    /*ToDo:

    *** PUSH & PULL nicht vergessen!! ***

    -Bei Klick auf normale Notification --> man soll wieder im Notification Frag landen, nicht im Dashboard
    -Settings: bei Language change wieder im Settingsfragment landen
    -Settings: LogIn Act. soll auch in jeweiliger Sprache sein
    -Settings: weiter implementieren (NightMode implementieren)
    -Dashboard mit Inhalt füllen (Hard-/Software)
    -Sensor Fragment mit Inhalt füllen (Texte dynamisieren!!)
    -Sensors Class implementieren
    -Infoseite erstellen
    -NFC Chips auslesen programmieren
    -Übersetzen der restlichen Wörter im String.xml
    -Weitere freshe Dinge ausdenken

    */


    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    String qrCodeScanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        qrCodeScanResult = getString(R.string.result);

        loadLayout();

        getFirstFragment();

        setNavigationView();
    }

    public void loadLayout()
    {
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    public void getFirstFragment()
    {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new DashboardFragment(), null);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle(R.string.dashboard);
    }

    public void setNavigationView()
    {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                clickOnNavigationItem(item);
                return true;
            }
        });
    }

    public void clickOnNavigationItem(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.dashboard_id:
                openNewFragment(new DashboardFragment(), item);
                break;

            case R.id.sensor_id:
                openNewFragment(new SensorFragment(), item);
                break;

            case R.id.barcode_scan_id:
                openNewFragment(new QrCodeScanFragment(), item);
                break;

            case R.id.nfc_scan_id:
                openNewFragment(new NfcScanFragment(), item);
                break;

            case R.id.notification:
                openNewFragment(new NotificationsFragment(), item);
                break;

            case R.id.info_id:
                openNewFragment(new InfoFragment(), item);
                break;

            case R.id.settings_id:
                openNewFragment(new SettingsFragment(), item);
                break;

            case R.id.logout_id:
                logOut();
                break;
        }
    }

    public void logOut()
    {
        finish();
    }

    public void openNewFragment(Fragment fragment, MenuItem item)
    {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
        item.setCheckable(true);
        drawerLayout.closeDrawers();
    }

    public void openNewFragment(Fragment fragment)
    {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    public void setActionBarTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null)
        {
            qrCodeScanResult = data.getStringExtra("SCAN_RESULT");
            openNewFragment(new QrCodeScanFragment());
        }
    }

    public String getQrCodeScanResult()
    {
        return qrCodeScanResult;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putString("Qr_Code_Scan_Result", qrCodeScanResult);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        qrCodeScanResult = savedInstanceState.getString("Qr_Code_Scan_Result");
    }

    public void onBackPressed()
    {
        //nothing
    }
}
