package com.billspillstore.android;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class GPSTracker implements LocationListener {

    Context context;

    public GPSTracker(Context c) {
        this.context = c;
    }

    public Location getLocation() {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Toast.makeText(context,"Permission not granted",Toast.LENGTH_LONG).show();
            return null;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isgpson = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (isgpson) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 5000, this);
            //Toast.makeText(context,"location found null",Toast.LENGTH_LONG).show();

            if(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!=null){
            return  locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 5000, this);
                return  locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
        else{
            Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
        // Toast.makeText(context,"default value",Toast.LENGTH_LONG).show();
        return  null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}