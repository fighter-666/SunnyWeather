package com.example.sunnyweather.base;


import com.example.sunnyweather.common.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilData {

    /**
     * 设置刷新状态
     * @param refreshType
     */
    public static void setRefreshState(String refreshType) {
        List<String> parts = new ArrayList<>();
        if (refreshType.contains(",")){
            parts = Arrays.asList(refreshType.split(","));
        }else{
            parts.add(refreshType);
        }

        for (int i = 0; i < parts.size(); i++) {
            switch (parts.get(i)) {
                case Constants.REFRESH_TYPE.mainFunctionalZone: {
                    Variable.mRefreshData.mainFunctionalZone = true;
                    break;
                }
                case Constants.REFRESH_TYPE.qryBroadbandADSpace: {
                    Variable.mRefreshData.qryBroadbandADSpace = true;
                    break;
                }
                case Constants.REFRESH_TYPE.qryTopBackGround: {
                    Variable.mRefreshData.qryTopBackGround = true;
                    break;
                }
                case Constants.REFRESH_TYPE.compoundADSpaceQuick:
                case Constants.REFRESH_TYPE.compoundADSpaceFocus: {
                    Variable.mRefreshData.compoundADSpaceQuickFocus = true;
                    break;
                }
                case Constants.REFRESH_TYPE.bottomTieADSpace: {
                    Variable.mRefreshData.bottomTieADSpace = true;
                    break;
                }
                case Constants.REFRESH_TYPE.queryAdveSpac: {
                    Variable.mRefreshData.queryAdveSpac = true;
                    break;
                }
                default:
                    break;
            }
        }
    }
}
