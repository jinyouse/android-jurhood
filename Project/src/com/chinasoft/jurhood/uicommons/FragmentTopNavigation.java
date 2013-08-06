package com.chinasoft.jurhood.uicommons;


import java.util.Vector;

import org.fodlife.library.LayoutAdjuster;
import org.fodlife.library.UIUtils;

import com.chinasoft.jurhood.AppDataManager;
import com.chinasoft.jurhood.R;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class FragmentTopNavigation extends Fragment {
	
	public interface TopNavigationChangedListener {
		public void onTopNavigationSelectionChanged(int newSelectionIdx, String newTitle);
	}
	
    private boolean 						mLayoutAdjusted = false;
    private View							mView;
    private TopNavigationChangedListener 	mListener;
    private TextAdapter						mMainAdapter;
    private String[]						mTitlesArray;
	
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		  	final View view = inflater.inflate(R.layout.fragment_top_navigation_bar, container, false);
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
		    
		    mView = view;
		    
		    Gallery mainGallery = (Gallery) mView.findViewById(R.id.gallery_main);
		    
		    mMainAdapter = new TextAdapter(getActivity());
		    if (mTitlesArray != null) {
		    	mMainAdapter.setTitles(mTitlesArray);
		    }
		    
		    mMainAdapter.setCurrentSelectedNumber(0);
		    
		    mainGallery.setAdapter(mMainAdapter);
		    
		    mainGallery.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					
					mMainAdapter.setCurrentSelectedNumber(arg2);
					
					mMainAdapter.notifyDataSetChanged();
					
					if (mListener != null) {
						if (mTitlesArray != null) {
							mListener.onTopNavigationSelectionChanged(arg2, mTitlesArray[arg2]);
						}
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
				}
		    });
		    
		    viewInitialize(view);
	    	return view;
	  }
	  
	  public void setSelectionChangedListener(TopNavigationChangedListener listener) {
		  mListener = listener;
	  }

	  public void viewInitialize(View view) {
		  
	  }

	  public void configureWithTitles(String...titlesArray) {
		  mTitlesArray = titlesArray;
	  }
	  
	  public void changeGallerySelection(int newSelectedItemIdx) {
		  Gallery mainGallery = (Gallery) mView.findViewById(R.id.gallery_main);
		  mainGallery.setSelection(newSelectedItemIdx);
	  }
	  
	  public void selectPreviousGalleryItem() {
		  Gallery mainGallery = (Gallery) mView.findViewById(R.id.gallery_main);
		  if (mainGallery.getSelectedItemPosition() > 0) {
			  mainGallery.setSelection(mainGallery.getSelectedItemPosition() - 1);
		  }
	  }
	  
	  public void selectNextGalleryItem() {
		  Gallery mainGallery = (Gallery) mView.findViewById(R.id.gallery_main);
		  if (mainGallery.getSelectedItemPosition() < mTitlesArray.length - 1) {
			  mainGallery.setSelection(mainGallery.getSelectedItemPosition() + 1);
		  }
	  }
	  
	  public class TextAdapter extends BaseAdapter {

    	private Context 			ctx;
    	private Vector<String>   	mTitles = new Vector<String>();
    	private int					mCurrentSelectedNumber;
    	
    	public TextAdapter(Context c) {
			ctx = c;
		}

    	public void setCurrentSelectedNumber(int currentSelectedNumber) {
    		mCurrentSelectedNumber = currentSelectedNumber;
    	}
    	
		@Override
    	public int getCount() {
    		return mTitles.size();
    	}

    	@Override
    	public Object getItem(int arg0) {
    		return mTitles.get(arg0);
    	}

    	@Override
    	public long getItemId(int arg0) {
    		return arg0;
    	}
    	
    	public void setTitles(String... array) {
    		mTitles.removeAllElements();
    		for (int idx = 0; idx < array.length; idx++) {
    			mTitles.add(array[idx]);
    		}
    		
    	}

    	@Override
    	public View getView(int arg0, View arg1, ViewGroup arg2) {
    		
    		TextView iv = new TextView(ctx);
    		iv.setTextSize(34 * AppDataManager.sharedInstance().getYRate());
    		UIUtils.setFont(iv, "fonts/Franklin Gothic Demi Cond.ttf");
    		iv.setText(mTitles.get(arg0));
    		iv.setGravity(Gravity.CENTER);
    		
    		if (arg0 == mCurrentSelectedNumber) {
    			iv.setTextColor(Color.WHITE);
    		} else {
    			iv.setTextColor(Color.rgb(143, 188, 219));
    		}

    		Gallery.LayoutParams param = new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT, Gallery.LayoutParams.WRAP_CONTENT);
    		iv.setLayoutParams(param);
    		iv.setPadding(0, 0, 0, 0);
    		
    		return iv;
    	}
    }
}
