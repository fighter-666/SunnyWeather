package com.example.sunnyweather.base;



public class SingleEReportData {
    private volatile static EReportData instance;

    private SingleEReportData() {}

    public static EReportData getInstance() {
        if (instance == null) {
            synchronized (SingleEReportData.class) {
                if (instance == null) {
                    instance = new EReportData();
                }
            }
        }
        return instance;
    }
}

