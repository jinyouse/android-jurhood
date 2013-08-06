package org.fodlife.library;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class OnSwipeTouchListener implements OnTouchListener {

	public interface SwipeListener {
		public void onSwipeToLeft();
		public void onSwipeToRight();
	}
	
	private float 			mPrevX = -1;
	private SwipeListener 	mListener;
	
	public OnSwipeTouchListener(SwipeListener listener) {
		mListener = listener;
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		
		float eventX = event.getX();
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (eventX - mPrevX > 100) {
				if (mListener != null) {
					mListener.onSwipeToRight();
				}
			} else if (mPrevX - eventX > 100) {
				if (mListener != null) {
					mListener.onSwipeToLeft();
				}
			}
		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {
			mPrevX = eventX;
		}
		
		return true;
	}
}