package com.example.administrator.shopping.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.shopping.R;

public class PercentRelativeLayout extends LinearLayout{

	public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public PercentRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PercentRelativeLayout(Context context) {
		super(context);
	}
	//测量容器宽高
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width=View.MeasureSpec.getSize(widthMeasureSpec);
		int height=View.MeasureSpec.getSize(heightMeasureSpec);
		int ChildCount=this.getChildCount();
		//测量出子控件进行改变
		for(int i=0;i<ChildCount;i++){
			View child=this.getChildAt(i);//每一个子控件
			ViewGroup.LayoutParams layoutParams=child.getLayoutParams();
			//解析自定义的宽高，进行替换
			float widthPercent=0;
			float heightPercent=0;
			if (layoutParams instanceof PercentRelativeLayout.LayoutParams) {
				widthPercent=((PercentRelativeLayout.LayoutParams) layoutParams).getWidthPercent();
				heightPercent=((PercentRelativeLayout.LayoutParams) layoutParams).getHeightPercent();
			}
			if (widthPercent!=0) {
				layoutParams.width=(int)(width*widthPercent);
			}
			if(heightPercent!=0){
				layoutParams.height=(int)(height*heightPercent);
			}
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	//用来对子控件进行布局
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);

	}
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new LayoutParams(getContext(),attrs);
	}
	public static class LayoutParams extends LinearLayout.LayoutParams{
		float widthPercent;
		float heightPercent;

		public float getWidthPercent() {
			return widthPercent;
		}

		public void setWidthPercent(float widthPercent) {
			this.widthPercent = widthPercent;
		}

		public float getHeightPercent() {
			return heightPercent;
		}

		public void setHeightPercent(float heightPercent) {
			this.heightPercent = heightPercent;
		}

		public LayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);
			TypedArray array=c.obtainStyledAttributes(attrs,R.styleable.percentRelativelayout);
			widthPercent=array.getFloat(R.styleable.percentRelativelayout_layout_widthPercent, 0);
			heightPercent=array.getFloat(R.styleable.percentRelativelayout_layout_heightPercent, 0);
			array.recycle();
		}

		public LayoutParams(int w, int h) {
			super(w, h);
			// TODO Auto-generated constructor stub
		}

		public LayoutParams(android.view.ViewGroup.LayoutParams source) {
			super(source);
			// TODO Auto-generated constructor stub
		}

		@SuppressLint("NewApi")
		public LayoutParams(android.widget.RelativeLayout.LayoutParams source) {
			super(source);
			// TODO Auto-generated constructor stub
		}

		public LayoutParams(MarginLayoutParams source) {
			super(source);
			// TODO Auto-generated constructor stub
		}

	}
}
