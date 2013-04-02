package com.example.gpstracker;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private PendingIntent schedule;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	
	public void startNotify(View view){
		EditText et = (EditText) findViewById(R.id.editText1);
		Intent inn = new Intent(this, GPSTrack.class);
		if (!(et.getText().toString().equals(""))){
			inn.putExtra("period", Integer.parseInt(et.getText().toString()));
		}
	//	schedule = PendingIntent.getService(getApplicationContext(), 12, inn, 0);
		startService(inn);
		/*AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		long time = SystemClock.elapsedRealtime();
		am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, time + 10, 5000, schedule);*/
	}
	
	public void endNotify(View view){
	//	AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//		am.cancel(schedule);
		Intent i = new Intent(this, GPSTrack.class);
		i.putExtra("command", "finish");
		startService(i);
	}
	
	public void send(View view){
		EditText edit = (EditText) findViewById(R.id.address);
		String costam = edit.getText().toString();
		if (costam.equals(""))
			costam = "http://192.168.1.101:8080/submit/";
		Data.sendTo(costam);
	}

}
