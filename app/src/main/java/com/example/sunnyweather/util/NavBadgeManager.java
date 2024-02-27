package com.example.sunnyweather.util;

import com.ct.base.MyApplication;
import com.ct.base.common.Log;
import com.ct.base.common.Variable;
import com.ct.client.homepage.model.NavBadge;
import com.ct.net.homepage.utils.Action1;
import com.ct.net.homepage.utils.CtBadgeHelper;
import com.ct.net.homepage.utils.TaskService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * 说明：底部导航栏badge显示管理类
 *
 * @作者 linwen@ffcs.cn
 * @创建时间 2017/8/3 15:55
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class NavBadgeManager extends CtBadgeHelper<NavBadge> {

    /**
     * 说明：badge显示顺序定义
     *
     * @作者 linwen@ffcs.cn
     * @创建时间 2017/8/3 17:51
     * @版本
     * @------修改记录-------
     * @修改人
     * @版本
     * @修改内容
     */
    public interface Ori {
        /**
         * 说明：tabbar上一次最多显示2个频道，优先级为
         *
         * 消息中心数字>消息红点>我的红点>查询办理红点>生活红点
         *
         * @作者 linwen@ffcs.cn
         * @创建时间 2017/8/3 17:39
         * @版本
         * @------修改记录-------
         * @修改人
         * @版本
         * @修改内容
         */
        int LIFE_1 = 4;  //生活频道的红点
        int SERVICE_1 = 3; //查询办理频道红点
        int MESSAGE_1 = 0; //消息红点
        int MINE_1 = 1;  //我的频道红点
        int MINE_2 = 2;  //我的频道的普通红点

        //因为首页导航tab的内容可能会变，tab id不变，
        // 所以不要直接使用id作为key，在这重新定义作为key

        // 消息
        int MESSAGE = 9527;
        // 服务(改名为查询办理)
        int SERVICE = 9528;
        // 生活
        int LIFE = 9529;
        // 我
        int MINE = 9530;

        int NON = -256;  // 不显示红点
        int NAN = -128;  // 显示红点(不可拖拽)
    }

    private static NavBadgeManager instance;

    private Future result;

    private int shotcutCount = 0;

    private NavBadgeManager() {

    }

    private static synchronized void initSync() {
        if (instance == null) {
            instance = new NavBadgeManager();
        }
    }

    public static NavBadgeManager getInstance() {
        if (instance == null) {
            initSync();
        }
        return instance;
    }

    public void resetShortcut() {
        if (Variable.isBadgesEnabled && shotcutCount == 0) {

            ShortcutBadger.applyCount(MyApplication.mContext, shotcutCount);
        }
    }

    @Override
    public void commit(NavBadge data) {
        if (!Variable.isBadgesEnabled) {
            return;
        }
//        Log.i("ctcdev","commit: count:"+mData.size()+" "+data.toString());
        synchronized (mData) {
            NavBadge t;
            boolean isExist = false;
            for (int i = mData.size(); --i > -1; ) {
                t = mData.get(i);
                if (t.getOri() == data.getOri() &&
                        t.getKey() == data.getKey()) {
                    mData.set(i, data);
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                mData.add(data);
            }
        }
        updateView();
    }

    @Override
    public void updateView() {
//        Log.w("ctcdev","updateView: ");
        if (result != null && !result.isDone()) {
//            Log.e("ctcdev","updateView: running");
            return;
        }
        result = TaskService.getInstance().go(mUpdating, true);
    }

    private Action1 mUpdating = new Action1<List<NavBadge>>() {

        @Override
        public List<NavBadge> proceed() {
            synchronized (mData) {
                Collections.sort(mData, new Order());
                return mData;
            }
        }

        @Override
        public void back(List<NavBadge> ret) {
            int size = ret.size();
            if (ret != null && size > 0) {
                int left = 3;//tabbar上一次最多显示 3 个badges
                boolean isValid;
                NavBadge t,t2;
                shotcutCount = 0;
                for (int i = 0; i < size; i++) {
                    isValid = true;
                    t = ret.get(i);
                    for (int j = 0; j < i; j++) {
                         t2 = ret.get(j);
                        if (t.getKey() == t2.getKey() &&
                                t2.getNum() != Ori.NON) {
                            isValid = false;
                            break;
                        }
                    }

//                    Log.i("ctcdev","back: "+left+" "+t.toString());
                    if (!isValid) {
                        //同一个位置有两种以上的badge时，
                        // 若优先级高的先显示了，次级的就不处理
                        continue;
                    }
                    if (left > 0) {
                        t.show();
                        if (t.getNum() != Ori.NON) {
                            shotcutCount += t.getNum() == Ori.NAN ? 1 : t.getNum();
                            left--;
                        }
                    } else {
                        t.hide();
                    }
                }

                ShortcutBadger.applyCount(MyApplication.mContext, shotcutCount);

                Log.i("hssShortcutBadger", "shotcutCount:" + shotcutCount);

            }
        }
    };

    @Override
    public void clearAll() {
        shotcutCount = 0;
        super.clearAll();
    }

    private static class Order implements Comparator<NavBadge> {

        @Override
        public int compare(NavBadge lhs, NavBadge rhs) {
            return (""+lhs.getOri()).compareTo(""+rhs.getOri());
        }
    }

}
