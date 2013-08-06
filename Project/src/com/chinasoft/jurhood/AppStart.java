package com.chinasoft.jurhood;

import org.fodlife.library.LayoutAdjuster;
import com.chinasoft.jurhood.activities.ActivityFirstPages;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import android.widget.FrameLayout;

public class AppStart extends Activity {

	private boolean focusChangeProcessed = false;
	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_app_start);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.app_start, menu);
		return true;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		mHandler.postDelayed(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AppStart.this, ActivityFirstPages.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		}, 2000);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus && !focusChangeProcessed) {
			focusChangeProcessed = true;
			
			FrameLayout container = (FrameLayout) findViewById(R.id.framelayout_container);
			AppDataManager.sharedInstance().setScreenSize(container.getWidth(), container.getHeight());
			
			float scaleXRate = container.getWidth() / (float)640.0f;
			float scaleYRate = container.getHeight() / 1100.0f;
			
			AppDataManager.sharedInstance().saveScaleRates(scaleXRate, scaleYRate, scaleXRate, scaleXRate);
			LayoutAdjuster.sharedInstance().fitToScreen(this, container, scaleXRate, scaleYRate, scaleXRate, scaleXRate);
		}
	}
}
