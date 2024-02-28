package com.example.sunnyweather.helper;

import java.util.Hashtable;

/**
 * 说明：
 *
 * @作者：zhangyz
 * @创建时间：2017/8/22 9:33
 * @版本:
 * @-----修改记录-----
 * @修改人：
 * @版本：
 * @修改内容：
 */
public class RedDotHelper {
    private Hashtable<String,IRedDotListener> mMaps = new Hashtable<>();
    private static RedDotHelper instance;

    private RedDotHelper(){}

    public static RedDotHelper getInstance(){
        if (instance==null){
            synchronized (RedDotHelper.class){
                if (instance==null){
                    instance=new RedDotHelper();
                }
            }
        }
        return instance;
    }

    public void addRedDotListener(String tabName,IRedDotListener listener){
        if(listener!=null){
            mMaps.put(tabName,listener);
        }
    }

    public <T> void onRedDotListener(String tabName,T entity){
        IRedDotListener listener = mMaps.get(tabName);
        if(listener !=null){
            listener.readRedDotListener(entity);
        }
    }

    public void onRedDotListener(String tabName){
        IRedDotListener listener = mMaps.get(tabName);
        if (listener !=null){
            listener.readRedDotListener(tabName);
        }
    }
}
