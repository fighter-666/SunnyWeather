package com.example.sunnyweather.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.sunnyweather.R;
import com.example.sunnyweather.util.UtilGlide;


/**
 * 通用加载框
 * @作者 张熠
 * @创建时间  2015-8-7 下午4:39:43
 *
 * @修改人
 * @修改时间
 * @修改内容
 */
public class MyProgressDialogE extends ProgressDialog {

	private static final String TAG = "MyProgressDialogE";
	private Context mContext;

	private Animation operatingAnim;
	private ImageView imgClose, ivLoading;
	private OnMyDialogCallback onCloseButtonCallback;

	public MyProgressDialogE(Context context) {
		super(context, R.style.mydialog_style_transparent);
		setmContext(context);
	}

	public MyProgressDialogE(Context context, int theme) {
		super(context, theme);
		setmContext(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");
		setContentView(R.layout.my_progressdialog_e);
		imgClose = findViewById(R.id.img_close);
		ivLoading = findViewById(R.id.ivLoading);

		UtilGlide.showGifLoopCount(R.drawable.ic_loading_logo, ivLoading,-1);

		imgClose.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (onCloseButtonCallback != null) {
					onCloseButtonCallback.onCallback();
				}
				dismiss();
			}
		});

		operatingAnim = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
		operatingAnim.setInterpolator(new LinearInterpolator());
	}

	@Override
	public void show() {
		super.show();
	}

	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	@Override
	public void setMessage(CharSequence message) {
		super.setMessage(message);
	}
	
	/**
	 * 设置 关闭按钮回调监听事件
	 * @作者 
	 * @创建时间  2015-8-7 下午4:40:13
	 * @param onCloseButtonCallback
	 *
	 * @修改人
	 * @修改时间
	 * @修改内容
	 */
	public void setOnCloseButtonCallback(OnMyDialogCallback onCloseButtonCallback) {
		this.onCloseButtonCallback = onCloseButtonCallback;
	}

}
