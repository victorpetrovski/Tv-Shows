package com.viktorpetrovski.moviesgo.util.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by Victor on 3/15/18.
 */

public class MyNestedScrollView extends NestedScrollView {
    @SuppressWarnings("unused")
    private int slop;
    @SuppressWarnings("unused")
    private float mInitialMotionX;
    @SuppressWarnings("unused")
    private float mInitialMotionY;
    public MyNestedScrollView(Context context) {
        super(context);
        init(context);
    }
    private void init(Context context) {
        ViewConfiguration config = ViewConfiguration.get(context);
        slop = config.getScaledEdgeSlop();
    }
    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public MyNestedScrollView(Context context, AttributeSet attrs,
                              int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private float xDistance, yDistance, lastX, lastY;
    @SuppressWarnings("unused")
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                lastX = ev.getX();
                lastY = ev.getY();
                // This is very important line that fixes
                computeScroll();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - lastX);
                yDistance += Math.abs(curY - lastY);
                lastX = curX;
                lastY = curY;
                if (xDistance > yDistance) {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
    public interface OnScrollChangedListener {
        void onScrollChanged(NestedScrollView who, int l, int t, int oldl,
                             int oldt);
    }
    private OnScrollChangedListener mOnScrollChangedListener;
    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        mOnScrollChangedListener = listener;
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}
