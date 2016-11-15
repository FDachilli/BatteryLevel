package com.example.batterylever;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BatteryActivity extends Activity {

	private TextView batLevel;
	//BR para algún intent
	private BroadcastReceiver batteryListener = new BroadcastReceiver() {
		//Qué hago cuando llega el intent
		@Override
		public void onReceive(Context context, Intent intent) {
			//Tengo q saber qué viene en el Intent
			int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
			Log.d("BatteryLevel", "Cambio el estado de la batería "+level);
			batLevel.setText(level+"%");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_battery);
		this.batLevel = (TextView) this.findViewById(R.id.battLevel);
		this.batLevel.setText("N/A");
	}

	@Override
	protected void onResume() {
		super.onResume();
		//Esto indica qué intent me interesa
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		//Registro mi BR!!
		this.registerReceiver(this.batteryListener, filter);
	}

	@Override
	protected void onPause() {
		super.onPause();
		//MUY IMPORTANTE: Todo lo que registro, lo desregistro!!!
		this.unregisterReceiver(this.batteryListener);
		this.batLevel.setText("N/A");
	}
	
	

}
