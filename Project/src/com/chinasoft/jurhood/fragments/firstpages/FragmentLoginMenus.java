package com.chinasoft.jurhood.fragments.firstpages;


import org.fodlife.library.LayoutAdjuster;

import com.chinasoft.jurhood.AppDataManager;
import com.chinasoft.jurhood.Constants;
import com.chinasoft.jurhood.R;
import com.chinasoft.jurhood.activities.ActivityFirstPages;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageButton;

public class FragmentLoginMenus extends Fragment {
	
	private boolean mLayoutAdjusted = false;
	
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		  	final View view = inflater.inflate(R.layout.fragment_firstpages_loginmenu, container, false);
	    	ViewTreeObserver vto = view.getViewTreeObserver();
		    vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		        @Override
		        public void onGlobalLayout() {
		        	if (!mLayoutAdjusted) {
		        		
		        		mLayoutAdjusted = true;
		        		
		    			LayoutAdjuster.sharedInstance().fitToScreen(getActivity(), (ViewGroup)view, 
		    					AppDataManager.sharedInstance().getXRate(), 
		    					AppDataManager.sharedInstance().getYRate(), 
		    					AppDataManager.sharedInstance().getWRate(), 
		    					AppDataManager.sharedInstance().getHRate());
		        	}
		        }
		    });
		    
		    viewInitialize(view);
	    	return view;
	  }

	  public void viewInitialize(View view) {
		  ((ImageButton) view.findViewById(R.id.imagebutton_normal_login)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((ActivityFirstPages) getActivity()).replaceFragment(new FragmentNormalSignIn(), Constants.TRANSITION_ANIM_TO_RIGHT_LEFT);
			}
		  });
		  
		  ((ImageButton) view.findViewById(R.id.imagebutton_create_account)).setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((ActivityFirstPages) getActivity()).replaceFragment(new FragmentCreateAccount(), Constants.TRANSITION_ANIM_TO_RIGHT_LEFT);
				}
			  });
	  }
}
