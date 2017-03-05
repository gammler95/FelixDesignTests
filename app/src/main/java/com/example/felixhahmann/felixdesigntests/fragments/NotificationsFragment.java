package com.example.felixhahmann.felixdesigntests.fragments;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.felixhahmann.felixdesigntests.R;
import com.example.felixhahmann.felixdesigntests.activities.MainActivity;

public class NotificationsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.notifications));

        buttonNormalNotification(view);

        return view;
    }

    public void buttonNormalNotification(View view)
    {
        Button bNormalNotification = (Button) view.findViewById(R.id.normal_notification_button);
        bNormalNotification.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                EditText notificationText = (EditText) getView().findViewById(R.id.notification_text);
                String notificationTextString =  notificationText.getText().toString();
                if(notificationTextString.isEmpty())
                {
                    //nothing
                }
                else
                {
                    triggerNormalNotification(notificationTextString);
                }
            }
        });
    }

    public void triggerNormalNotification(String notificationTextString)
    {
        Intent intent = new Intent(getContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(getContext());

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_notifications_active_black_48px)
                .setTicker("Hearty365")
                .setContentTitle(getString(R.string.notification_header))
                .setContentText(notificationTextString)
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent)
                .setContentInfo(getString(R.string.info));

        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
    }

}
