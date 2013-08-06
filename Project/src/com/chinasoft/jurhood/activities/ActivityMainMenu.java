package com.chinasoft.jurhood.activities;

import org.fodlife.library.LayoutAdjuster;
import org.fodlife.library.UIUtils;

import com.chinasoft.jurhood.AppDataManager;
import com.chinasoft.jurhood.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityMainMenu extends Activity {
	
	private boolean focusChangeProcessed = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mainmenu);
		
		setFonts();
		setListeners();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus && !focusChangeProcessed) {
			focusChangeProcessed = true;
			
			FrameLayout container = (FrameLayout) findViewById(R.id.framelayout_container);
			
			LayoutAdjuster.sharedInstance().fitToScreen(this, container, 
										AppDataManager.sharedInstance().getXRate(), 
										AppDataManager.sharedInstance().getYRate(), 
										AppDataManager.sharedInstance().getWRate(), 
										AppDataManager.sharedInstance().getYRate());
		}
	}
	
	private void setFonts() {
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_01)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_02)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_03)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_04)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_05)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_06)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_07)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_08)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_09)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_10)), "fonts/Franklin Gothic Book.ttf");
		 UIUtils.setFont(((TextView)findViewById(R.id.textview_item_note_11)), "fonts/Franklin Gothic Book.ttf");
	}
	
	private void setListeners() {
		((ImageButton) findViewById(R.id.imagebutton_item_07)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ActivityMainMenu.this, ActivitySweetDealList.class);
				startActivity(intent);
			}
		});
	}
}
