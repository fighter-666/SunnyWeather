package com.example.sunnyweather.widget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseTask extends AsyncTask<String, Integer, Boolean> {
    protected Context mContext;
    private MyProgressDialogE mProgressDialog;
    private String mProgressMsg = "玩命查询中,请稍候...";
    private boolean mIsProgressVisiable = false;
    protected OnTaskFinished mOnTaskFinished;
    protected OnTaskStart mOnTaskStart;
    protected OnCancel mOnCancel = null;
    private static volatile ExecutorService threadPoll = null;

    public BaseTask(Context context) {
        mContext = context;

//        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void setProgressVisiable(boolean enable) {
        mIsProgressVisiable = enable;
    }

    public void setProgressMsg(String msg) {
        mProgressMsg = msg;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mIsProgressVisiable && mProgressDialog == null && mContext instanceof Activity) {
            mProgressDialog = new MyProgressDialogE(mContext);
        }
        try {
            if (mIsProgressVisiable && mProgressDialog != null) {
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        cancel(true);
                        if (mOnCancel != null) {
                            mOnCancel.onClick();
                        }
                    }
                });
                mProgressDialog.setOnCloseButtonCallback(new OnMyDialogCallback() {
                    @Override
                    public void onCallback() {
                        cancel(true);
                        if (mOnCancel != null) {
                            mOnCancel.onClick();
                        }
                    }
                });
                mProgressDialog.setCancelable(true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setMessage(mProgressMsg);
                mProgressDialog.show();

                Log.i("ProgressDialog", "Method called by: " + this.getClass().getSimpleName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Boolean doInBackground(String... params) {
        return true;
    }

    @Override
    protected void onPostExecute(Boolean rslt) {
        try {
            if (mProgressDialog != null) mProgressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnTaskFinished(OnTaskFinished onTaskFinished) {
        mOnTaskFinished = onTaskFinished;
    }

    public void setOnTaskStart(OnTaskStart onTaskStart) {
        mOnTaskStart = onTaskStart;
    }

    public void setOnCancel(OnCancel onCancel) {
        mOnCancel = onCancel;
    }


    /**
     * 自定义线程池
     */
    public void execute() {
        // 创建指定线程数量的线程池，并发数上限就是指定的线程数。但新任务产生，没有空闲的线程，就只能等待。
//        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
//        executeOnExecutor(newFixedThreadPool);
        // 默认线程池，并发线程数跟设备的cpu数量是有关的，因此不同的设备上可能看到的结果不完全一致
//        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        executeOnExecutor(getPoll());
    }

    private ExecutorService getPoll(){
        if (threadPoll == null){
            synchronized (BaseTask.class){
                if (threadPoll == null){
                    threadPoll = Executors.newFixedThreadPool(10);
                }
            }
        }
        return threadPoll;
    }

}
