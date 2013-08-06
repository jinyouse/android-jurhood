package com.chinasoft.jurhood.fragments.firstpages;


import org.fodlife.library.LayoutAdjuster;
import org.fodlife.library.UIUtils;

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
import android.widget.TextView;

public class FragmentCreateAccount extends Fragment {
	
	private boolean mLayoutAdjusted = false;
	private View	mView;
	
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		  	final View view = inflater.inflate(R.layout.fragment_firstpages_create_account, container, false);
		  	mView = view;
	    	ViewTreeObserver vto = view.getViewTreeObserver();
		    vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		        @Override
		        public void onGlobalLayout() {
		        	if (!mLayoutAdjusted) {
		        		
		        		mLayoutAdjusted = true;
		        		
		    			LayoutAdjuster.sharedInstance().fitToScreen(getActivity(), (ViewGroup)view, 
		    					AppDataManager.sharedInstance().getXRate(), 
		    					AppDataManager.sharedInstance().getXRate(), 
		    					AppDataManager.sharedInstance().getXRate(), 
		    					AppDataManager.sharedInstance().getXRate());
		        	}
		        }
		    });
		    
		    viewInitialize(view);
	    	return view;
	  }

	  public void viewInitialize(View view) {
		  setFonts(view);
		  ((ImageButton)view.findViewById(R.id.imagebutton_back)).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UIUtils.hideKeyboardInActivity(getActivity(), (ViewGroup)mView);
				((ActivityFirstPages) getActivity()).replaceFragment(new FragmentLoginMenus(), Constants.TRANSITION_ANIM_TO_RIGHT_LEFT);
			}
		  });
	  }
	  
	  private void setFonts(View view) {
		  UIUtils.setFont(((TextView)view.findViewById(R.id.textview_note_1)), "fonts/Franklin Gothic Demi Cond.ttf");
		  UIUtils.setFont(((TextView)view.findViewById(R.id.textview_note_2)), "fonts/Franklin Gothic Book.ttf");
		  UIUtils.setFont(((TextView)view.findViewById(R.id.textview_note_3)), "fonts/Franklin Gothic Book.ttf");
		  UIUtils.setFont(((TextView)view.findViewById(R.id.textview_note_4)), "fonts/Franklin Gothic Book.ttf");
		  UIUtils.setFont(((TextView)view.findViewById(R.id.textview_note_5)), "fonts/Franklin Gothic Book.ttf");
		  UIUtils.setFont(((TextView)view.findViewById(R.id.textview_privacy)), "fonts/Franklin Gothic Book.ttf");
		  UIUtils.setFont(((TextView)view.findViewById(R.id.textview_signup_title)), "fonts/Franklin Gothic Demi Cond.ttf");
	  }
}
