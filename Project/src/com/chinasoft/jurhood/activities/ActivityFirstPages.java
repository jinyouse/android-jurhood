package com.chinasoft.jurhood.activities;

import org.fodlife.library.LayoutAdjuster;

import com.chinasoft.jurhood.AppDataManager;
import com.chinasoft.jurhood.Constants;
import com.chinasoft.jurhood.R;
import com.chinasoft.jurhood.fragments.firstpages.FragmentLoginMenus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.FrameLayout;

public class ActivityFirstPages extends FragmentActivity {
	
	private boolean focusChangeProcessed = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_firstpages);
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
					AppDataManager.sharedInstance().getHRate());
			
			replaceFragment(new FragmentLoginMenus(), Constants.TRANSITION_ANIM_TO_RIGHT_LEFT);
		}
	}
	
	public void replaceFragment(Fragment newFragment, int animType) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		
		if (animType == Constants.TRANSITION_ANIM_TO_LEFT_LEFT) {
			ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right,  R.anim.slide_out_left);
		} else if (animType == Constants.TRANSITION_ANIM_TO_RIGHT_RIGHT) {
			ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left,  R.anim.slide_out_right);
		}  else if (animType == Constants.TRANSITION_ANIM_TO_RIGHT_LEFT) {
			ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_out_right,  R.anim.slide_in_right);
		}  else if (animType == Constants.TRANSITION_ANIM_TO_LEFT_RIGHT) {
			ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_out_left,  R.anim.slide_in_left);
		}
		
		ft.replace(R.id.framelayout_fragment_container, newFragment);
		ft.commit();
	}
}
