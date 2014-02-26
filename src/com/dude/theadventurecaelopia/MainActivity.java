package com.dude.theadventurecaelopia;

import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;

@TargetApi(19) public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.theadventurecaelopia.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	public void sendMessage(View view) {
//	    // Do something in response to button
//		Intent intent = new Intent(this, DisplayMessageActivity.class);
//		EditText editText = (EditText) findViewById(R.id.edit_message);
//		String message = editText.getText().toString();
//		intent.putExtra(EXTRA_MESSAGE, message);
//		startActivity(intent);
//	}
	
	public void newGame(View view) {
		Intent intent = new Intent(this, NewGameActivity.class);
		startActivity(intent);
	}
	public void continueGame(View view) {
		
	}
	public void settings(View view) {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
	public void credits(View view) {
		Intent intent = new Intent(this, CreditsActivity.class);
		startActivity(intent);
	}
	
	@TargetApi(19) @Override
	public void onWindowFocusChanged(boolean hasFocus) {
	        super.onWindowFocusChanged(hasFocus);
	    if (hasFocus) {
	       getWindow().getDecorView().setSystemUiVisibility(
	                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
	                | View.SYSTEM_UI_FLAG_FULLSCREEN
	                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
	}

}
