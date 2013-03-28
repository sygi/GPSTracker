package com.example.gpstracker;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private PendingIntent schedule;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent inn = new Intent(this, GPSTrack.class);
		inn.putExtra("command", "notify");
		schedule = PendingIntent.getService(getApplicationContext(), 12, inn, 0);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	
	public void sendSomething(View view){
		Intent i = new Intent(this, GPSTrack.class);
		startService(i);
	}
	
	public void startNotify(View view){
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		long time = SystemClock.elapsedRealtime();
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, time + 10, 5000, schedule);
	}
	
	public void endNotify(View view){
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(schedule);
		Intent i = new Intent(this, GPSTrack.class);
		i.putExtra("command", "finish");
		startService(i);
	}

}
