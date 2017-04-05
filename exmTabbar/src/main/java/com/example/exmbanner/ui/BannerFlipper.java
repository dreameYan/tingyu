package com.example.exmbanner.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.exmtabbar.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

@SuppressLint({ "InflateParams", "ClickableViewAccessibility" })
public class BannerFlipper extends RelativeLayout {
	private static final String TAG = "BannerFlipper";

	private Context mContext;
	private BannerClickListener mListener;
	private ViewFlipper mFlipper;
	private LinearLayout mLayout;
	private int mCount;
	private List<Button> mButtonList = new ArrayList<Button>();
	private LayoutInflater mInflater;
	private GestureDetector mGesture;
	private float mFlipGap = 20f;

	public BannerFlipper(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public BannerFlipper(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public void setOnBannerListener(BannerClickListener listener) {
		mListener = listener;
	}

	 public void start() {
		 startFlip();
	 }

	public void setImage(ArrayList<Integer> imageList) {
		for (int i = 0; i < imageList.size(); i++) {
			Integer imageID = ((Integer) imageList.get(i)).intValue();
			ImageView ivNew = new ImageView(mContext);
			ivNew.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			ivNew.setScaleType(ImageView.ScaleType.FIT_XY);
			ivNew.setImageResource(imageID);
			mFlipper.addView(ivNew);
			Log.d(TAG, "i=" + i + ",image_id=" + imageID);
		}

		mCount = imageList.size();
		for (int i = 0; i < mCount; i++) {
			View view = mInflater.inflate(R.layout.banner_flipper_button, null);
			Button button = (Button) view.findViewById(R.id.banner_btn_num);
			mLayout.addView(view);
			mButtonList.add(button);
		}
		mFlipper.setDisplayedChild(mCount - 1);
		startFlip();
	}

	private void setButton(int position) {
		if (mCount > 1) {
			for (int m = 0; m < mCount; m++) {
				if (m == position % mCount) {
					mButtonList.get(m).setBackgroundResource(R.drawable.icon_point_c);
				} else {
					mButtonList.get(m).setBackgroundResource(R.drawable.icon_point_n);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void init() {
		mInflater = ((Activity) mContext).getLayoutInflater();
		View view = mInflater.inflate(R.layout.banner_flipper, null);
		mFlipper = (ViewFlipper) view.findViewById(R.id.banner_flipper);
		mLayout = (LinearLayout) view.findViewById(R.id.banner_flipper_num);
		addView(view);
		// 该手势的onSingleTapUp事件是点击时进入广告页
		mGesture = new GestureDetector(new BannerGestureListener(this));
	}

	public boolean dispatchTouchEvent(MotionEvent event) {
		mGesture.onTouchEvent(event);
		return true;
	}

	final class BannerGestureListener implements GestureDetector.OnGestureListener {
		private BannerGestureListener(BannerFlipper bannerFlipper) {
		}

		@Override
		public final boolean onDown(MotionEvent event) {
			return true;
		}

		@Override
		public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			if (e1.getX() - e2.getX() > mFlipGap) {
				startFlip();
				return true;
			}
			if (e1.getX() - e2.getX() < -mFlipGap) {
				backFlip();
				return true;
			}
			return false;
		}

		@Override
		public final void onLongPress(MotionEvent event) {
		}

		@Override
		public final boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			boolean result = false; // true表示要继续处理
			if (Math.abs(distanceY) < Math.abs(distanceX)) {
				BannerFlipper.this.getParent().requestDisallowInterceptTouchEvent(false);
				result = true;
			}
			return result;
		}

		@Override
		public final void onShowPress(MotionEvent event) {
		}

		@Override
		public boolean onSingleTapUp(MotionEvent event) {
			int position = mFlipper.getDisplayedChild();
			mListener.onBannerClick(position);
			return false;
		}

	}

	private void startFlip() {
		mFlipper.startFlipping();
		mFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_left_in));
		mFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_left_out));
		mFlipper.getOutAnimation().setAnimationListener(new BannerAnimationListener(this));
		mFlipper.showNext();
	}

	private void backFlip() {
		mFlipper.startFlipping();
		mFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_right_in));
		mFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_right_out));
		mFlipper.getOutAnimation().setAnimationListener(new BannerAnimationListener(this));
		mFlipper.showPrevious();
		mFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_left_in));
		mFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.push_left_out));
		mFlipper.getOutAnimation().setAnimationListener(new BannerAnimationListener(this));
	}

	private class BannerAnimationListener implements Animation.AnimationListener {
		private BannerAnimationListener(BannerFlipper bannerFlipper) {
		}

		@Override
		public final void onAnimationEnd(Animation paramAnimation) {
			int position = mFlipper.getDisplayedChild();
			setButton(position);
		}

		@Override
		public final void onAnimationRepeat(Animation paramAnimation) {
		}

		@Override
		public final void onAnimationStart(Animation paramAnimation) {
		}
	}

}
