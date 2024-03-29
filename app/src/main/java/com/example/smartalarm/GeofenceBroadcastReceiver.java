package com.example.smartalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "GeofenceBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        GeoNotificationHelper notificationHelper = new GeoNotificationHelper(context);

        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
       // throw new UnsupportedOperationException("Not yet implemented");
       // Toast.makeText(context, "Geofence triggered...", Toast.LENGTH_SHORT).show();
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.d(TAG, "onReceive: Error receiving geofence event...");
            return;
        }
        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
        for (Geofence geofence: geofenceList) {
            Log.d(TAG, "onReceive: " + geofence.getRequestId());
        }
//        Location location = geofencingEvent.getTriggeringLocation();
        int transitionType = geofencingEvent.getGeofenceTransition();
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context, "GEOFENCE_TRANSITION_ENTER", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("My Reminder", "Hey! You have a task in this" +
                        "location.", MapsActivity.class);
                break;
           case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show();
               notificationHelper.sendHighPriorityNotification("GEOFENCE_TRANSITION_DWELL", "", MapsActivity.class);
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, "GEOFENCE_TRANSITION_EXIT", Toast.LENGTH_SHORT).show();
                notificationHelper.sendHighPriorityNotification("My Reminder", "Hey! You have a task in this" +
                        "location", MapsActivity.class);
                break;
        }





    }
}