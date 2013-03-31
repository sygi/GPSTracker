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
	private int period;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand (Intent intent, int flags, int startId){
		Log.d("sygi", "im here, period:" + intent.getIntExtra("period", 5));
		period = intent.getIntExtra("period", 5);
		if (intent.getStringExtra("command") == null){
			return START_NOT_STICKY;
		}
		if (intent.getStringExtra("command").equals("finish")){
			stopSelf();
		}
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
			public void onProviderEnabled(String provider) {
				Log.d("sygi", provider + " enabled");
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				Log.d("sygi", provider + " disabled");
			}
			
			@Override
			public void onLocationChanged(Location location) {
				loc = location;
				locNotify();
			}
		};
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * period, 0, ll);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * period, 0, ll);
		Toast.makeText(getApplicationContext(), "Starting", Toast.LENGTH_LONG).show();
	}
	
	private void locNotify(){
		Log.d("sygi", "location changed");
		String info = "provider: " + loc.getProvider() + "\n";
		info += Location.convert(loc.getLatitude(), Location.FORMAT_DEGREES) + "N\n";
		info += Location.convert(loc.getLongitude(), Location.FORMAT_DEGREES) + "E\n";
		info += "speed:" + loc.getSpeed();
		Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDestroy (){
		Toast.makeText(getApplicationContext(), "Finishing", Toast.LENGTH_LONG).show();
		lm.removeUpdates(ll);
	}
}
