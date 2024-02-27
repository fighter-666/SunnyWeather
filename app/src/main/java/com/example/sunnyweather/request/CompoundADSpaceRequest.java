package com.example.sunnyweather.request;


import com.example.sunnyweather.base.Interface850;
import com.example.sunnyweather.base.Request;
import com.example.sunnyweather.response.CompoundADSpaceResponse;

public class CompoundADSpaceRequest extends Request<com.example.sunnyweather.base.CompoundADSpaceResponse> {

    public CompoundADSpaceRequest() {
        super();
        getHeaderInfos().setCode("compoundADSpace");
    }

    public void setAccessAuth(String accessAuth) {
        put("accessAuth", accessAuth);
    }

    public void setAccount(String account) {
        put("account", account);
    }

    public void setCityCode(String cityCode) {
        put("cityCode", cityCode);
    }

    public void setIsChinatelecom(String isChinatelecom) {
        put("isChinatelecom", isChinatelecom);
    }

    public void setIsPremiumEdition(String isPremiumEdition) {
        put("isPremiumEdition", isPremiumEdition);
    }

    public void setIsNewUser(String isNewUser) {
        put("isNewUser", isNewUser);
    }

    public void setNetType(String netType) {
        put("netType", netType);
    }

    public void setPhoneType(String phoneType) {
        put("phoneType", phoneType);
    }

    public void setProvinceCode(String provinceCode) {
        put("provinceCode", provinceCode);
    }

    public void setShopId(String shopId) {
        put("shopId", shopId);
    }

    public void setType(String type) {
        put("type", type);
    }

    public void setDevelopCode(String developCode) {
        put("developCode", developCode);
    }

    public com.example.sunnyweather.base.CompoundADSpaceResponse getResponse() {
        com.example.sunnyweather.base.CompoundADSpaceResponse response = new com.example.sunnyweather.base.CompoundADSpaceResponse();
        CompoundADSpaceResponse responsetemp = new CompoundADSpaceResponse();
        try {
            response = https().create(Interface850.class).compoundADSpace(this).execute().body();

            if (response != null) {

                boolean fixed = response.fixed(response.responseData);
                if (fixed && response.responseData.data != null) {
                    response.compoundADSpaceData = response.responseData.data;
                }

                //由于Aditem的跳转是由commonLinkItem控制的，GSON解析不会直接给commonLinkItem赋值，因此要单独做一次转换
//                CompoundADSpaceData.CompoundADSpaceInfoBean compoundADSpaceInfoBean;
//                AdItem adItem;
//                for(int i = 0; i < responsetemp.compoundADSpaceData.compoundADSpaceInfos.size(); i++){
//                    compoundADSpaceInfoBean = new CompoundADSpaceData.CompoundADSpaceInfoBean();
//                    compoundADSpaceInfoBean.type = responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).type;
//                    for (int j = 0; j < responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.size(); j++) {
//                        adItem = new AdItem();
//                        adItem.setId(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getId());
//                        adItem.setDetail(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getDetail());
//                        adItem.setOtherIntro(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getOtherIntro());
//                        adItem.setNews(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getNews());
//                        adItem.setOrder(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getOrder());
//                        adItem.setIntroduction(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getIntroduction());
//                        adItem.setTitle(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getTitle());
//                        adItem.setLink(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getLink());
//                        adItem.setLinkType(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getLinkType());
//                        adItem.setImageUrl(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getImageUrl());
//                        adItem.setIconUrl(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getIconUrl());
//                        adItem.setIsRead(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getIsRead());
//                        adItem.setFlag(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getFlag());
//                        adItem.setTimeStamp(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getTimeStamp());
//                        adItem.setProvinceCode(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getProvinceCode());
//                        adItem.setSceneId(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getSceneId());
//                        adItem.setAutoSwitch(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getAutoSwitch());
//                        adItem.setAutoTime(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getAutoTime());
//                        adItem.setType(responsetemp.compoundADSpaceData.compoundADSpaceInfos.get(i).advertisingSpaceInfos.get(j).getType());
//                        compoundADSpaceInfoBean.advertisingSpaceInfos.add(adItem);
//                    }
//                    response.compoundADSpaceData.compoundADSpaceInfos.add(compoundADSpaceInfoBean);
//                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response == null) {
            response = new com.example.sunnyweather.base.CompoundADSpaceResponse();
        }
        return response;
    }
}
