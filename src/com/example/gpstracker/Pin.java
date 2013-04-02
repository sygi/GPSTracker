package com.example.gpstracker;

import org.json.JSONException;
import org.json.JSONObject;

public class Pin {
	public Double longt, latit;
	public long time;
	Pin(Double longt, Double latit, long time){
		this.longt = longt;
		this.latit = latit;
		this.time = time;
	}
	public JSONObject toJson(){
		JSONObject job = new JSONObject();
		try {
			job.put("longtitude", longt);
			job.put("latitude", latit);
			job.put("time", time);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return job;
	}
}
