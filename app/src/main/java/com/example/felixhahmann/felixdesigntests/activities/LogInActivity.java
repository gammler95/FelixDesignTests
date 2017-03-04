package com.example.felixhahmann.felixdesigntests.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.felixhahmann.felixdesigntests.R;

public class LogInActivity extends AppCompatActivity
{
    long sleepTimeInMills = 2000;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.login);

        Button bLogIn = (Button) findViewById(R.id.button_login);
        bLogIn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        showProgressBar(true);

                        threadSleep(sleepTimeInMills);

                        showProgressBar(false);

                        Intent myIntent = new Intent(LogInActivity.this, MainActivity.class);
                        LogInActivity.this.startActivity(myIntent);
                    }
                }.start();
            }
        });
    }

    public void showProgressBar(final boolean b)
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);

                if (b)
                {
                    pb.setVisibility(View.VISIBLE);
                }
                else
                {
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void threadSleep(long sleepTimeInMills)
    {
        try {
            Thread.sleep(sleepTimeInMills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.end_app)
                .setMessage(R.string.end_app_text)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        System.exit(0);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
