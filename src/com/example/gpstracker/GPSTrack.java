package com.example.gpstracker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GPSTrack extends Service {

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
		if (intent.getStringExtra("command").equals("notify")){
			Log.d("sygi", "nofity");
			Toast.makeText(getApplicationContext(), "Notification " + numb, Toast.LENGTH_LONG).show();
			numb++;
		}
		//TODO check if it is ok
		return START_NOT_STICKY;
	}
	
	@Override
	public void onCreate (){
		numb = 1;
		Toast.makeText(getApplicationContext(), "Starting", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDestroy (){
		Toast.makeText(getApplicationContext(), "Finishing", Toast.LENGTH_LONG).show();
		
	}
}
