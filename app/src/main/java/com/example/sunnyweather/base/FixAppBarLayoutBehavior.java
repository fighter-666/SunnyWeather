package com.example.sunnyweather.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;

import java.lang.reflect.Field;

/**
 * @说明：
 * @作者: zhangyz
 * @创建时间：2018/11/6 10:57
 * @版本：
 * @-----修改记录----
 * @修改人：
 * @修改内容：
 * @版本：
 */
public class FixAppBarLayoutBehavior extends AppBarLayout.Behavior {
    private static final String TAG = "FixBehavior";
    private static final int TYPE_FLING = 1;
    private boolean isFlinging;
    private boolean shouldBlockNestedScroll;


    private static AppBarLayout appBarLayout;

    // 滑动状态监听
    private OnScrollChangeListener onScrollChangeListener;

    // 是否正在滑动中
    private boolean mScrolling = false;

    // scrollView 滑动距离是否够小
    private boolean isNestedPreScrollStop = false;

    // 使用 Handler 来处理滑动停止的检测
    private Handler scrollHandler = new Handler(Looper.getMainLooper());
    private Runnable scrollStopRunnable = () -> onScrollState(false);

    // Static method to retrieve the Behavior instance from a View
    public static FixAppBarLayoutBehavior from(AppBarLayout view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
            if (behavior instanceof FixAppBarLayoutBehavior) {
                setAppbarLayout(view);
                return (FixAppBarLayoutBehavior) behavior;
            }
        }
        throw new IllegalArgumentException("The view is not associated with FixAppBarLayoutBehavior");
    }

    private static void setAppbarLayout(AppBarLayout view) {
        appBarLayout = view;
    }

    private AppBarLayout.OnOffsetChangedListener offsetChangedListener;

    private void addOnOffsetChangedListener(AppBarLayout view) {
        if (offsetChangedListener == null) {
            offsetChangedListener = (appBarLayout, verticalOffset) -> {
                scrollHandler.removeCallbacks(scrollStopRunnable);
                scrollHandler.postDelayed(scrollStopRunnable, 200);
            };
            appBarLayout.addOnOffsetChangedListener(offsetChangedListener);
        }
    }


    public FixAppBarLayoutBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
        shouldBlockNestedScroll = isFlinging;
        Log.d(TAG, "onInterceptTouchEvent:" + child.getTotalScrollRange() + ",shouldBlockNestedScroll:" + shouldBlockNestedScroll);
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //手指触摸屏幕的时候停止fling事件
                stopAppbarLayoutFling(child);
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onTouchEvent(@NonNull CoordinatorLayout parent, @NonNull AppBarLayout child, @NonNull MotionEvent ev) {
        Log.d(TAG, "onTouchEvent: action:" + ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                scrollHandler.removeCallbacks(scrollStopRunnable);
                onScrollState(true);
                break;
            case MotionEvent.ACTION_UP:
                scrollHandler.postDelayed(scrollStopRunnable, 200);
                if (appBarLayout != null) {
                    addOnOffsetChangedListener(appBarLayout);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(parent, child, ev);
    }


    /**
     * 反射获取私有的flingRunnable 属性，考虑support 28以后变量名修改的问题
     *
     * @return Field
     */
    private Field getFlingRunnableField() throws NoSuchFieldException {
        Class<?> superclass = this.getClass().getSuperclass();
        try {
            // support design 27及一下版本
            Class<?> headerBehaviorType = null;
            if (superclass != null) {
                headerBehaviorType = superclass.getSuperclass();
            }
            if (headerBehaviorType != null) {
                return headerBehaviorType.getDeclaredField("mFlingRunnable");
            } else {
                return null;
            }
        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
            // 可能是28及以上版本
            Class<?> headerBehaviorType = superclass.getSuperclass().getSuperclass();
            if (headerBehaviorType != null) {
                return headerBehaviorType.getDeclaredField("flingRunnable");
            } else {
                return null;
            }
        }
    }

    /**
     * 反射获取私有的scroller 属性，考虑support 28以后变量名修改的问题
     *
     * @return Field
     */
    private Field getScrollerField() throws NoSuchFieldException {
        Class<?> superclass = this.getClass().getSuperclass();
        try {
            // support design 27及一下版本
            Class<?> headerBehaviorType = null;
            if (superclass != null) {
                headerBehaviorType = superclass.getSuperclass();
            }
            if (headerBehaviorType != null) {
                return headerBehaviorType.getDeclaredField("mScroller");
            } else {
                return null;
            }
        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
            // 可能是28及以上版本
            Class<?> headerBehaviorType = superclass.getSuperclass().getSuperclass();
            if (headerBehaviorType != null) {
                return headerBehaviorType.getDeclaredField("scroller");
            } else {
                return null;
            }
        }
    }

    /**
     * 停止appbarLayout的fling事件
     *
     * @param appBarLayout
     */
    private void stopAppbarLayoutFling(AppBarLayout appBarLayout) {
        //通过反射拿到HeaderBehavior中的flingRunnable变量
        try {
            Field flingRunnableField = getFlingRunnableField();
            Runnable flingRunnable;
            if (flingRunnableField != null) {
                flingRunnableField.setAccessible(true);
                flingRunnable = (Runnable) flingRunnableField.get(this);
                if (flingRunnable != null) {
                    Log.d(TAG, "存在flingRunnable");
                    appBarLayout.removeCallbacks(flingRunnable);
                    flingRunnableField.set(this, null);
                }
            }

            Field scrollerField = getScrollerField();
            if (scrollerField != null) {
                scrollerField.setAccessible(true);
                OverScroller overScroller = (OverScroller) scrollerField.get(this);
                if (overScroller != null && !overScroller.isFinished()) {
                    overScroller.abortAnimation();
                }
            }
        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
        } catch (IllegalAccessException e) {
//            e.printStackTrace();
        }
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child,
                                       View directTargetChild, View target,
                                       int nestedScrollAxes, int type) {
        Log.d(TAG, "onStartNestedScroll");
        stopAppbarLayoutFling(child);
        return super.onStartNestedScroll(parent, child, directTargetChild, target,
                nestedScrollAxes, type);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout,
                                  AppBarLayout child, View target,
                                  int dx, int dy, int[] consumed, int type) {
        Log.d(TAG, "onNestedPreScroll:" + child.getTotalScrollRange()
                + " ,dx:" + dx + " ,dy:" + dy + " ,type:" + type);
        //type返回1时，表示当前target处于非touch的滑动，
        //该bug的引起是因为appbar在滑动时，CoordinatorLayout内的实现NestedScrollingChild2接口的滑动
        //子类还未结束其自身的fling
        //所以这里监听子类的非touch时的滑动，然后block掉滑动事件传递给AppBarLayout
        if (type == TYPE_FLING) {
            isFlinging = true;
        }
        isNestedPreScrollStop = false;
        if (Math.abs(dy) <= 6) {
            isNestedPreScrollStop = true;
            // 距离过小，
        } else {
            // 距离够大，认为在滑动中
            scrollHandler.removeCallbacks(scrollStopRunnable);
            onScrollState(true);
        }
        if (!shouldBlockNestedScroll) {
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child,
                               View target, int dxConsumed, int dyConsumed, int
                                       dxUnconsumed, int dyUnconsumed, int type) {
        Log.d(TAG, "onNestedScroll: target:" + target.getClass() + " ,"
                + child.getTotalScrollRange() + " ,dxConsumed:"
                + dxConsumed + " ,dyConsumed:" + dyConsumed + " " + ",type:" + type);
        if (!shouldBlockNestedScroll) {
            super.onNestedScroll(coordinatorLayout, child, target, dxConsumed,
                    dyConsumed, dxUnconsumed, dyUnconsumed, type);
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl,
                                   View target, int type) {
        Log.d(TAG, "onStopNestedScroll");
        super.onStopNestedScroll(coordinatorLayout, abl, target, type);
        isFlinging = false;
        shouldBlockNestedScroll = false;
        if (isNestedPreScrollStop) {
            isNestedPreScrollStop = false;
            scrollHandler.postDelayed(scrollStopRunnable, 200);
        }
    }

    private void onScrollState(boolean scroll) {
        if (onScrollChangeListener != null) {
            if (mScrolling != scroll) {
                onScrollChangeListener.scrollChange(scroll);
                mScrolling = scroll;
                Log.d(TAG, "onScrollState:" + scroll);
            }
        }
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public interface OnScrollChangeListener {
        void scrollChange(boolean isScroll);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
        calculateMaxScrollH(parent, abl);
        return super.onLayoutChild(parent, abl, layoutDirection);
    }

    private int maxScrollH = 0;

    // 计算最大的可滑动距离
    private void calculateMaxScrollH(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        maxScrollH = appBarLayout.getTotalScrollRange();
        if (coordinatorLayout.getChildAt(coordinatorLayout.getChildCount() - 1) instanceof ViewPager) {
            View childView = coordinatorLayout.getChildAt(coordinatorLayout.getChildCount() - 1);
            if (childView.getVisibility() == View.GONE) {
                int parentH = coordinatorLayout.getMeasuredHeight();
                int childScrollTotalH = appBarLayout.getTotalScrollRange();
                // scrollView没数据时，滑动AppBarLayout，不需要滑动完整个AppBarLayout。
                // 那么这个AppBarLayout滑动多少距离合适呢，
                // 合适滑动距离就是AppBarLayout的高度 + 悬浮布局高度 + recyclerView的高度 - CoordinatorLayout布局的高度，这样保证剩下的正好是个全屏。
//                View tab = child.getChildAt(child.getChildCount() - 1);
                maxScrollH = childScrollTotalH - parentH;

                if (maxScrollH < 0) {
                    maxScrollH = 0;
                }
            }
        }
    }

    @Override
    public boolean setTopAndBottomOffset(int offset) {
        // 重写此方法，判断offset是否超过了最大的可滑动距离，如果超过就不再滑动
        if (maxScrollH <= Math.abs(offset)) {
            offset = -maxScrollH;
        }
        return super.setTopAndBottomOffset(offset);
    }
}
