package com.example.gpstracker;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GPSTrack extends Service {

	
	private Location loc;
	private LocationManager lm;
	private LocationListener ll;
	private int numb;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand (Intent intent, int flags, int startId){
		Log.d("sygi", "im here " + intent.getStringExtra("command")+ " " + startId);
		if (intent.getStringExtra("command") == null){
			return START_NOT_STICKY;
		}
		if (intent.getStringExtra("command").equals("finish")){
			stopSelf();
		}
		/*if (intent.getStringExtra("command").equals("notify")){
			Log.d("sygi", "nofity");
			Toast.makeText(getApplicationContext(), "Notification " + numb, Toast.LENGTH_LONG).show();
			numb++;
		}*/
		//TODO check if it is ok
		return START_NOT_STICKY;
	}
	
	@Override
	public void onCreate (){
		numb = 1;
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		ll = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {// TODO
			}
			
			@Override
			public void onProviderEnabled(String provider) {// TODO 		
			}
			
			@Override
			public void onProviderDisabled(String provider) {// TODO
			}
			
			@Override
			public void onLocationChanged(Location location) {
				loc = location;
				locNotify();
			}
		};
		//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, ll);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, ll);
		Toast.makeText(getApplicationContext(), "Starting", Toast.LENGTH_LONG).show();
	}
	
	private void locNotify(){
		Log.d("sygi", "location changed");
		String info = "";
		info += Location.convert(loc.getLatitude(), Location.FORMAT_DEGREES);
		info += "\n" + Location.convert(loc.getLongitude(), Location.FORMAT_DEGREES);
		info += "\nspeed:" + loc.getSpeed();
		Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDestroy (){
		Toast.makeText(getApplicationContext(), "Finishing", Toast.LENGTH_LONG).show();
		lm.removeUpdates(ll);
	}
}
