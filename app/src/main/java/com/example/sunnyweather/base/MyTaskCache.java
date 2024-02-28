package com.example.sunnyweather.base;

import android.content.Context;

import com.ct.base.communication2.response.bean.responseData.data.DefaultMemberConfData;
import com.ct.base.util.UtilFile;
import com.ct.base.util.UtilText;
import com.ct.net.communication.request.Request;
import com.ct.net.communication.request.RequestJson;
import com.ct.net.communication.response.GetRechargePageTabResponse;
import com.ct.net.communication.response.QueryTablebarResponse;
import com.ct.net.communication.response.model.AdItem;
import com.ct.net.communication.response.model.CompoundAdItem;
import com.ct.net.communication.response.model.QueryTablebarBean;
import com.ct.net.communication2.response.DefaultMemberConfResponse;
import com.ct.net.communication2.response.EquityCardAndBackGroundResponse;
import com.ct.net.communication2.response.GetQueryProcessingTabResponse;
import com.ct.net.communication2.response.GetSignInBottomTabResponse;
import com.ct.net.communication2.response.HotSelectionCardResponse;
import com.ct.net.communication2.response.HotWordSearchListResponse;
import com.ct.net.communication2.response.HotWordSearchV2Response;
import com.ct.net.communication2.response.IntelligentAssistantResponse;
import com.ct.net.communication2.response.MyChannelFloorResponse;
import com.ct.net.communication2.response.QryIndexTopBackGroundResponse;
import com.ct.net.communication2.response.QueryAdListResponse;
import com.ct.net.communication2.response.QueryAdvertisingSpaceResponse;
import com.ct.net.communication2.response.QueryAssistTopConfResponse;
import com.ct.net.communication2.response.QueryMessageChannelResponse;
import com.ct.net.communication2.response.QueryMyChannelAdvSpaceResponse;
import com.ct.net.communication2.response.QueryMyChannelPageHeadResponse;
import com.ct.net.communication2.response.QueryMyChannelTopResponse;
import com.ct.net.communication2.response.QueryRechargeSudokuResponse;
import com.ct.net.communication2.response.QueryTabCardAdListResponse;
import com.ct.net.communication2.response.UsageCardResponse;
import com.ct.net.communication2.response.bean.responseData.data.CompoundADSpaceData;
import com.ct.net.communication2.response.bean.responseData.data.EquityCardAndBackGroundData;
import com.ct.net.communication2.response.bean.responseData.data.GetQueryProcessingTabData;
import com.ct.net.communication2.response.bean.responseData.data.GetRechargePageTabData;
import com.ct.net.communication2.response.bean.responseData.data.GetSignInBottomTabData;
import com.ct.net.communication2.response.bean.responseData.data.HotSelectionCardData;
import com.ct.net.communication2.response.bean.responseData.data.HotWordSearchListData;
import com.ct.net.communication2.response.bean.responseData.data.HotWordSearchV2Data;
import com.ct.net.communication2.response.bean.responseData.data.IntelligentAssistantData;
import com.ct.net.communication2.response.bean.responseData.data.MyChannelFloorData;
import com.ct.net.communication2.response.bean.responseData.data.QryIndexTopBackGroundData;
import com.ct.net.communication2.response.bean.responseData.data.QueryAssistTopConfData;
import com.ct.net.communication2.response.bean.responseData.data.QueryMessageChannelData;
import com.ct.net.communication2.response.bean.responseData.data.QueryMyChannelPageHeadData;
import com.ct.net.communication2.response.bean.responseData.data.QueryMyChannelTopData;
import com.ct.net.communication2.response.bean.responseData.data.QueryRechargeSudokuData;
import com.ct.net.communication2.response.bean.responseData.data.QueryTabCardAdListData;
import com.ct.net.communication2.response.bean.responseData.data.UsageCardData;
import com.ct.net.homepage.utils.TaskService;
import com.example.sunnyweather.request.CompoundADSpaceRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口缓存管理
 *
 * 某些接口通讯任务在通讯时需要先用上一次通讯成功的结果来展示。（可以参考XML：QueryAdListTask、JSON：QueryTablebarTask）
 * 每次通讯开始前读取缓存，通讯成功后写入缓存。
 * 本类支持缓存的读取和写入。
 *
 * @author jiangwenxin
 * @createTime 2014年12月18日 上午10:47:36
 * @version
 */
public class MyTaskCache {

	private static String FILENAME = "taskcachedata";
	private Context mContext;
	private Map<String, Object> mAllData = new HashMap<String,Object>();
	private static MyTaskCache mMyTaskCache;

	private MyTaskCache(Context context){
		mContext = context;
		getAllData();
	}

	public static MyTaskCache getInstance(Context context){
		if(mMyTaskCache == null)
			mMyTaskCache = new MyTaskCache(context);
		return mMyTaskCache;
	}

	/**
	 * 缓存一个接口数据
	 *
	 * @author jiangwenxin
	 * @createTime 2014年12月17日 下午2:40:53
	 * @param request
	 * @param response 传入的response对象需要可以序列化 ，属性涉及的自定义结构都要 implements Serializable
	 */
	public void saveTask(Request request, Object response){
		saveData(request.getCacheKey(), response);
	}

	/**
	 * 缓存一个接口数据(JSON)
	 *
	 * @author jiangwenxin
	 * @createTime 2014年12月17日 下午2:40:53
	 * @param request
	 * @param response 传入的response对象需要可以序列化 ，属性涉及的自定义结构都要 implements Serializable
	 */
	public void saveTaskJson(RequestJson request, Object response){
		saveData(request.getCacheKey(), response);
	}

	/**
	 * 缓存一个接口数据(JSON)-Retrofit
	 *
	 * @author jiangwenxin
	 * @createTime 2014年12月17日 下午2:40:53
	 * @param request
	 * @param response 传入的response对象需要可以序列化 ，属性涉及的自定义结构都要 implements Serializable
	 */
	public void saveTaskJsonRetrofit(CompoundADSpaceRequest request, Object response){
		saveData(request.getCacheKey(), response);
	}

	/**
	 * 取一个接口的缓存数据
	 *
	 * @author jiangwenxin
	 * @createTime 2014年12月17日 下午2:40:57
	 * @param request
	 * @return  返回null表示取不到
	 */
	public Object getCacheTask(Request request){
		return getData(request.getCacheKey());
	}

	/**
	 * 取一个接口的缓存数据(JSON)
	 *
	 * @author jiangwenxin
	 * @createTime 2014年12月17日 下午2:40:57
	 * @param request
	 * @return  返回null表示取不到
	 */
	public Object getCacheTaskJson(RequestJson request){
		return getData(request.getCacheKey());
	}

	/**
	 * 取一个接口的缓存数据(JSON)-Retrofit
	 *
	 * @author jiangwenxin
	 * @createTime 2014年12月17日 下午2:40:57
	 * @param request
	 * @return  返回null表示取不到
	 */
	public Object getCacheTaskJsonRetrofit(com.ct.net.communication2.request.base.Request request){
		return getData(request.getCacheKey());
	}

	/**
	 * 获取所有缓存
	 *
	 * @author jiangwenxin
	 * @createTime 2014年12月17日 下午2:21:42
	 * @return
	 */
	private Map<String,Object> getAllData(){
		String PATH = mContext.getFilesDir().getAbsolutePath() + "/" + FILENAME;
		Log.v("saveNewData path = " + PATH);
        Map<String,Object> records = UtilFile.readObject(PATH);
        if (records != null) {
            mAllData = records;
        }
		return mAllData;
	}

    /**
     * 说明：新增一个接口缓存
     *
     * @作者 jiangwenxin
     * @创建时间 2014年9月25日 下午3:39:23
     * @版本
     * @------修改记录-------
     * @修改人 linwen@ffcs.cn
     * @版本 6.2.0
     * @修改内容 工作线程处理缓存
     */
	private void saveData(String key, Object value){
		if(value == null || UtilText.isEmptyOrNull(key)){
			return;
		}

		mAllData.put(key,  encodeResp(value));
//		mAllData.put(key,  value);

        TaskService.getInstance().go(new Runnable() {
            @Override
            public void run() {
                String PATH = mContext.getFilesDir().getAbsolutePath() + "/" + FILENAME;
                Log.v("saveNewData path = " + PATH);
                UtilFile.saveObject(PATH, mAllData);
            }
        });
	}

	private Object getData(String key){
		if(key!=null && !UtilText.isEmptyOrNull(key) && mAllData.containsKey(key)){
			return decodeResp(mAllData.get(key));
//			return mAllData.get(key);
		}
		return null;
	}


	/***
	 * 对缓存数据中的部分信息进行加密处理
	 * @param obj
	 * @return
	 */
	private Object encodeResp(Object obj) {
		if (obj instanceof QueryAdListResponse) {
			QueryAdListResponse adListResponse = null;
			try {
				adListResponse = (QueryAdListResponse) ((QueryAdListResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (adListResponse != null && adListResponse.getAdItems() != null) {
				for (AdItem ad : adListResponse.getAdItems()) {
					String link = ad.getLink();
					String imageUrl = ad.getImageUrl();
					String iconUrl = ad.getIconUrl();
					String decodeLink = Setting.encode(link);
					String decodeImageUrl = Setting.encode(imageUrl);
					String decodeIconUrl = Setting.encode(iconUrl);
					ad.setLink(decodeLink);
					ad.setImageUrl(decodeImageUrl);
					ad.setIconUrl(decodeIconUrl);
				}
			}
			return adListResponse;
		} else if (obj instanceof QueryTablebarResponse) {
			QueryTablebarResponse tablebarResponse = null;
			try {
				tablebarResponse = (QueryTablebarResponse) ((QueryTablebarResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (tablebarResponse != null && tablebarResponse.queryTablebarBean.tableBarInfos != null) {
				for (QueryTablebarBean.TableBarInfosBean bean : tablebarResponse.queryTablebarBean.tableBarInfos) {
					bean.clickIcon = Setting.encode(bean.clickIcon);
					bean.normalIcon = Setting.encode(bean.normalIcon);
					bean.backToTopIcon = Setting.encode(bean.backToTopIcon);
					bean.showBigIcon = Setting.encode(bean.showBigIcon);
					bean.specialTag = Setting.encode(bean.specialTag);
					bean.link = Setting.encode(bean.link);
				}
			}
			if (tablebarResponse != null && tablebarResponse.queryTablebarBean.tableBarInfosForPremiumEdition != null) {
				for (QueryTablebarBean.TableBarInfosBean bean : tablebarResponse.queryTablebarBean.tableBarInfosForPremiumEdition) {
					bean.clickIcon = Setting.encode(bean.clickIcon);
					bean.normalIcon = Setting.encode(bean.normalIcon);
					bean.backToTopIcon = Setting.encode(bean.backToTopIcon);
					bean.showBigIcon = Setting.encode(bean.showBigIcon);
					bean.specialTag = Setting.encode(bean.specialTag);
					bean.link = Setting.encode(bean.link);
				}
			}
			return tablebarResponse;
		} else if (obj instanceof QueryAdvertisingSpaceResponse) {
			QueryAdvertisingSpaceResponse queryAdvertisingSpaceResponse = null;
			try {
				queryAdvertisingSpaceResponse = (QueryAdvertisingSpaceResponse) ((QueryAdvertisingSpaceResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryAdvertisingSpaceResponse != null && queryAdvertisingSpaceResponse.queryAdvertisingSpaceData.advertisingSpaceInfos != null) {
				for (AdItem ad : queryAdvertisingSpaceResponse.queryAdvertisingSpaceData.advertisingSpaceInfos) {
					String link = ad.getLink();
					String imageUrl = ad.getImageUrl();
					String iconUrl = ad.getIconUrl();
					String decodeLink = Setting.encode(link);
					String decodeImageUrl = Setting.encode(imageUrl);
					String decodeIconUrl = Setting.encode(iconUrl);
					ad.setLink(decodeLink);
					ad.setImageUrl(decodeImageUrl);
					ad.setIconUrl(decodeIconUrl);
				}
			}
			return queryAdvertisingSpaceResponse;
		} else if (obj instanceof QueryMyChannelAdvSpaceResponse) {
			QueryMyChannelAdvSpaceResponse queryMyChannelAdvSpaceResponse = null;
			try {
				queryMyChannelAdvSpaceResponse = (QueryMyChannelAdvSpaceResponse) ((QueryMyChannelAdvSpaceResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryMyChannelAdvSpaceResponse != null && queryMyChannelAdvSpaceResponse.queryMyChannelAdvSpaceData.myChannelAdvSpaceInfos != null) {
				for (AdItem ad : queryMyChannelAdvSpaceResponse.queryMyChannelAdvSpaceData.myChannelAdvSpaceInfos) {
					String link = ad.getLink();
					String imageUrl = ad.getImageUrl();
					String iconUrl = ad.getIconUrl();
					String decodeLink = Setting.encode(link);
					String decodeImageUrl = Setting.encode(imageUrl);
					String decodeIconUrl = Setting.encode(iconUrl);
					ad.setLink(decodeLink);
					ad.setImageUrl(decodeImageUrl);
					ad.setIconUrl(decodeIconUrl);
				}
			}
			return queryMyChannelAdvSpaceResponse;
		} else if (obj instanceof IntelligentAssistantResponse) {
			IntelligentAssistantResponse intelligentAssistantResponse = null;
			try {
				intelligentAssistantResponse = (IntelligentAssistantResponse) ((IntelligentAssistantResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (intelligentAssistantResponse != null && intelligentAssistantResponse.intelligentAssistantData.advertisingInfos != null) {
				for (IntelligentAssistantData.AdvertisingInfosBean advertisingInfosBean : intelligentAssistantResponse.intelligentAssistantData.advertisingInfos) {
					advertisingInfosBean.imageUrl = Setting.encode(advertisingInfosBean.imageUrl);
					advertisingInfosBean.link = Setting.encode(advertisingInfosBean.link);
				}
			}
			if (intelligentAssistantResponse != null && intelligentAssistantResponse.intelligentAssistantData.helpInfos != null) {
				for (IntelligentAssistantData.HelpInfosBean helpInfosBean : intelligentAssistantResponse.intelligentAssistantData.helpInfos) {
					helpInfosBean.iconUrl = Setting.encode(helpInfosBean.iconUrl);
				}
			}
			return intelligentAssistantResponse;
		} else if (obj instanceof QueryRechargeSudokuResponse) {
			QueryRechargeSudokuResponse queryRechargeSudokuResponse = null;
			try {
				queryRechargeSudokuResponse = (QueryRechargeSudokuResponse) ((QueryRechargeSudokuResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryRechargeSudokuResponse != null && queryRechargeSudokuResponse.queryRechargeSudokuData.defaultList != null) {
				for (QueryRechargeSudokuData.DefaultListBean defaultListBean : queryRechargeSudokuResponse.queryRechargeSudokuData.defaultList) {
					defaultListBean.imageUrl = Setting.encode(defaultListBean.imageUrl);
					defaultListBean.link = Setting.encode(defaultListBean.link);
				}
			}
			if (queryRechargeSudokuResponse != null && queryRechargeSudokuResponse.queryRechargeSudokuData.recommendList != null) {
				for (QueryRechargeSudokuData.DefaultListBean recommendListBean : queryRechargeSudokuResponse.queryRechargeSudokuData.recommendList) {
					recommendListBean.imageUrl = Setting.encode(recommendListBean.imageUrl);
					recommendListBean.link = Setting.encode(recommendListBean.link);
				}
			}
			return queryRechargeSudokuResponse;
		} else if (obj instanceof QueryMyChannelPageHeadResponse) {
			QueryMyChannelPageHeadResponse queryMyChannelPageHeadResponse = null;
			try {
				queryMyChannelPageHeadResponse = (QueryMyChannelPageHeadResponse) ((QueryMyChannelPageHeadResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryMyChannelPageHeadResponse != null && queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.portalList != null) {
				for (QueryMyChannelPageHeadData.PortalListBean portalListBean : queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.portalList) {
					portalListBean.iconUrl = Setting.encode(portalListBean.iconUrl);
					portalListBean.link = Setting.encode(portalListBean.link);
				}
			}
			if (queryMyChannelPageHeadResponse != null && queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo != null) {
				queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo.iconUrl = Setting.encode(queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo.iconUrl);
				queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo.link = Setting.encode(queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo.link);
			}
			if (queryMyChannelPageHeadResponse != null && queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf != null) {
				queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf.backgroundPic = Setting.encode(queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf.backgroundPic);
				queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf.link = Setting.encode(queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf.link);
			}
			return queryMyChannelPageHeadResponse;
		} else if (obj instanceof GetRechargePageTabResponse) {
			GetRechargePageTabResponse getRechargePageTabResponse = null;
			try {
				getRechargePageTabResponse = (GetRechargePageTabResponse) ((GetRechargePageTabResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (getRechargePageTabResponse != null && getRechargePageTabResponse.getRechargePageTabData.firTabList != null) {
				for (GetRechargePageTabData.FirTabListBean firTabListBean : getRechargePageTabResponse.getRechargePageTabData.firTabList) {
					firTabListBean.iconUrl = Setting.encode(firTabListBean.iconUrl);
					firTabListBean.imageUrl = Setting.encode(firTabListBean.imageUrl);
					firTabListBean.link = Setting.encode(firTabListBean.link);
				}
			}
			if (getRechargePageTabResponse != null && getRechargePageTabResponse.getRechargePageTabData.noticeConf != null) {
				for (GetRechargePageTabData.NoticeConfBean noticeConfBean : getRechargePageTabResponse.getRechargePageTabData.noticeConf) {
					noticeConfBean.link = Setting.encode(noticeConfBean.link);
				}
			}
			if (getRechargePageTabResponse != null && getRechargePageTabResponse.getRechargePageTabData.sndTabConfList != null) {
				for (GetRechargePageTabData.SndTabConfListBean sndTabConfListBean : getRechargePageTabResponse.getRechargePageTabData.sndTabConfList) {
					sndTabConfListBean.link = Setting.encode(sndTabConfListBean.link);
				}
			}
			return getRechargePageTabResponse;
		} else if (obj instanceof CompoundADSpaceResponse) {
			CompoundADSpaceResponse compoundADSpaceResponse = null;
			try {
				compoundADSpaceResponse = (CompoundADSpaceResponse) ((CompoundADSpaceResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (compoundADSpaceResponse != null && compoundADSpaceResponse.compoundADSpaceData.compoundADSpaceInfos != null) {
				for (CompoundADSpaceData.CompoundADSpaceInfoBean compoundADSpaceInfoBean : compoundADSpaceResponse.compoundADSpaceData.compoundADSpaceInfos) {
					if (compoundADSpaceInfoBean.advertisingSpaceInfos != null) {
						for (AdItem ad : compoundADSpaceInfoBean.advertisingSpaceInfos) {
							String link = ad.getLink();
							String imageUrl = ad.getImageUrl();
							String iconUrl = ad.getIconUrl();
							String decodeLink = Setting.encode(link);
							String decodeImageUrl = Setting.encode(imageUrl);
							String decodeIconUrl = Setting.encode(iconUrl);
							ad.setLink(decodeLink);
							ad.setImageUrl(decodeImageUrl);
							ad.setIconUrl(decodeIconUrl);
						}
					}
				}
			}
			return compoundADSpaceResponse;
		} else if (obj instanceof HotWordSearchListResponse) {
			HotWordSearchListResponse hotWordSearchListResponse = null;
			try {
				hotWordSearchListResponse = (HotWordSearchListResponse) ((HotWordSearchListResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (hotWordSearchListResponse != null && hotWordSearchListResponse.hotWordSearchListData.hotWordSearchListInfos != null) {
				for (HotWordSearchListData.HotWordSearchListInfosBean hotWordSearchListInfosBean : hotWordSearchListResponse.hotWordSearchListData.hotWordSearchListInfos) {
					hotWordSearchListInfosBean.iconUrl = Setting.encode(hotWordSearchListInfosBean.iconUrl);

					if (hotWordSearchListInfosBean.hotWordSearchListSecInfos != null) {
						for (HotWordSearchListData.HotWordSearchListInfosBean.HotWordSearchListSecInfosBean hotWordSearchListSecInfosBean : hotWordSearchListInfosBean.hotWordSearchListSecInfos) {
							if (hotWordSearchListSecInfosBean.hotWordSearchListThrInfos != null) {
								for (HotWordSearchListData.HotWordSearchListInfosBean.HotWordSearchListSecInfosBean.HotWordSearchListThrInfosBean hotWordSearchListThrInfosBean : hotWordSearchListSecInfosBean.hotWordSearchListThrInfos) {
									hotWordSearchListThrInfosBean.iconUrl = Setting.encode(hotWordSearchListThrInfosBean.iconUrl);
									hotWordSearchListThrInfosBean.link = Setting.encode(hotWordSearchListThrInfosBean.link);
								}
							}
						}
					}
				}
			}
			return hotWordSearchListResponse;
		} else if (obj instanceof HotWordSearchV2Response) {
			HotWordSearchV2Response hotWordSearchV2Response = null;
			try {
				hotWordSearchV2Response = (HotWordSearchV2Response) ((HotWordSearchV2Response) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (hotWordSearchV2Response != null) {
				hotWordSearchV2Response.hotWordSearchV2Data.link = Setting.encode(hotWordSearchV2Response.hotWordSearchV2Data.link);

				if (hotWordSearchV2Response.hotWordSearchV2Data.hotWordSearchV2Infos != null) {
					for (HotWordSearchV2Data.HotWordSearchV2InfosBean hotWordSearchV2InfosBean : hotWordSearchV2Response.hotWordSearchV2Data.hotWordSearchV2Infos) {
						hotWordSearchV2InfosBean.link = Setting.encode(hotWordSearchV2InfosBean.link);
					}
				}
				if (hotWordSearchV2Response.hotWordSearchV2Data.recommenInfos != null) {
					for (HotWordSearchV2Data.RecommenInfosBean recommenInfosBean : hotWordSearchV2Response.hotWordSearchV2Data.recommenInfos) {
						recommenInfosBean.link = Setting.encode(recommenInfosBean.link);
						recommenInfosBean.iconUrl = Setting.encode(recommenInfosBean.iconUrl);
					}
				}
			}
			return hotWordSearchV2Response;
		} else if (obj instanceof QryIndexTopBackGroundResponse) {
			QryIndexTopBackGroundResponse qryIndexTopBackGroundResponse = null;
			try {
				qryIndexTopBackGroundResponse = (QryIndexTopBackGroundResponse) ((QryIndexTopBackGroundResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (qryIndexTopBackGroundResponse != null && qryIndexTopBackGroundResponse.qryIndexTopBackGroundData != null) {
				qryIndexTopBackGroundResponse.qryIndexTopBackGroundData.link = Setting.encode(qryIndexTopBackGroundResponse.qryIndexTopBackGroundData.link);

				if (qryIndexTopBackGroundResponse.qryIndexTopBackGroundData.backGroundLayerBeans != null) {
					for (QryIndexTopBackGroundData.BackGroundLayerBeansBean backGroundLayerBeansBean : qryIndexTopBackGroundResponse.qryIndexTopBackGroundData.backGroundLayerBeans) {
						backGroundLayerBeansBean.imageUrl = Setting.encode(backGroundLayerBeansBean.imageUrl);
					}
				}
			}
			return qryIndexTopBackGroundResponse;
		} else if (obj instanceof QueryTabCardAdListResponse) {
			QueryTabCardAdListResponse queryTabCardAdListResponse = null;
			try {
				queryTabCardAdListResponse = (QueryTabCardAdListResponse) ((QueryTabCardAdListResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryTabCardAdListResponse != null) {
				if (queryTabCardAdListResponse.queryTabCardAdListData.tabList != null) {
					for (QueryTabCardAdListData.TabListBean tabListBean : queryTabCardAdListResponse.queryTabCardAdListData.tabList) {
						if (tabListBean.floorList != null) {
							for (QueryTabCardAdListData.TabListBean.FloorListBean floorListBean : tabListBean.floorList) {
								if (floorListBean.advertisingList != null) {
									for (QueryTabCardAdListData.TabListBean.FloorListBean.AdvertisingListBean advertisingListBean : floorListBean.advertisingList) {
										advertisingListBean.link = Setting.encode(advertisingListBean.link);
										advertisingListBean.iconUrl = Setting.encode(advertisingListBean.iconUrl);
									}
								}
							}
						}
					}
				}
			}
			return queryTabCardAdListResponse;
		} else if (obj instanceof QueryAssistTopConfResponse) {
			QueryAssistTopConfResponse queryAssistTopConfResponse = null;
			try {
				queryAssistTopConfResponse = (QueryAssistTopConfResponse) ((QueryAssistTopConfResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (queryAssistTopConfResponse != null && queryAssistTopConfResponse.queryAssistTopConfData != null) {
				if (queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean != null) {
					queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.icon = Setting.encode(queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.icon);
					queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.imageUrl = Setting.encode(queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.imageUrl);
					queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.shareUrl = Setting.encode(queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.shareUrl);
					queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.titleIcon = Setting.encode(queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.titleIcon);
				}

				if (queryAssistTopConfResponse.queryAssistTopConfData.tabList != null) {
					for (QueryAssistTopConfData.TabListBean tabListBean : queryAssistTopConfResponse.queryAssistTopConfData.tabList) {
						tabListBean.normalIcon = Setting.encode(tabListBean.normalIcon);
						tabListBean.selectedIcon = Setting.encode(tabListBean.selectedIcon);
						tabListBean.link = Setting.encode(tabListBean.link);
					}
				}
			}
			return queryAssistTopConfResponse;
		} else if (obj instanceof DefaultMemberConfResponse) {
			DefaultMemberConfResponse defaultMemberConfResponse = null;
			try {
				defaultMemberConfResponse = (DefaultMemberConfResponse) ((DefaultMemberConfResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (defaultMemberConfResponse != null && defaultMemberConfResponse.defaultMemberConfData != null) {
				if (defaultMemberConfResponse.defaultMemberConfData.headPortraits != null) {
					for (DefaultMemberConfData.HeadPortraitsBean headPortraitsBean : defaultMemberConfResponse.defaultMemberConfData.headPortraits) {
						headPortraitsBean.imageUrl = Setting.encode(headPortraitsBean.imageUrl);
					}
				}
			}
			return defaultMemberConfResponse;
		} else if (obj instanceof GetQueryProcessingTabResponse) {
			GetQueryProcessingTabResponse getQueryProcessingTabResponse = null;
			try {
				getQueryProcessingTabResponse = (GetQueryProcessingTabResponse) ((GetQueryProcessingTabResponse) obj).deepCopy();
				if (getQueryProcessingTabResponse != null && getQueryProcessingTabResponse.getQueryProcessingTabData != null) {
					if (getQueryProcessingTabResponse.getQueryProcessingTabData.customerService != null) {
						getQueryProcessingTabResponse.getQueryProcessingTabData.customerService.iconUrl = Setting.encode(getQueryProcessingTabResponse.getQueryProcessingTabData.customerService.iconUrl);
						getQueryProcessingTabResponse.getQueryProcessingTabData.customerService.link = Setting.encode(getQueryProcessingTabResponse.getQueryProcessingTabData.customerService.link);
					}

					if (getQueryProcessingTabResponse.getQueryProcessingTabData.topTabInfos != null) {
						for (GetQueryProcessingTabData.TopTabInfosBean topTabInfosBean : getQueryProcessingTabResponse.getQueryProcessingTabData.topTabInfos) {
							topTabInfosBean.backGroundImgUrl = Setting.encode(topTabInfosBean.backGroundImgUrl);
							topTabInfosBean.link = Setting.encode(topTabInfosBean.link);

							if (topTabInfosBean.functionalAreaDetailInfos != null) {
								for (CompoundAdItem compoundAdItem : topTabInfosBean.functionalAreaDetailInfos) {
									compoundAdItem.iconUrl = Setting.encode(compoundAdItem.iconUrl);
									compoundAdItem.link = Setting.encode(compoundAdItem.link);
								}
							}

							if (topTabInfosBean.cardTabInfos != null) {
								for (GetQueryProcessingTabData.TopTabInfosBean.CardTabInfosBean cardTabInfosBean : topTabInfosBean.cardTabInfos) {
									if (cardTabInfosBean.cardAreaInfos != null) {
										for (GetQueryProcessingTabData.TopTabInfosBean.CardTabInfosBean.CardAreaInfosBean cardAreaInfosBean : cardTabInfosBean.cardAreaInfos) {
											cardAreaInfosBean.iconUrl = Setting.encode(cardAreaInfosBean.iconUrl);
											cardAreaInfosBean.link = Setting.encode(cardAreaInfosBean.link);

											if (cardAreaInfosBean.functionalAreaDetailInfos != null) {
												for (CompoundAdItem compoundAdItem : cardAreaInfosBean.functionalAreaDetailInfos) {
													compoundAdItem.iconUrl = Setting.encode(compoundAdItem.iconUrl);
													compoundAdItem.link = Setting.encode(compoundAdItem.link);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				return getQueryProcessingTabResponse;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else if (obj instanceof GetSignInBottomTabResponse) {
			GetSignInBottomTabResponse getSignInBottomTabResponse = null;
			try {
				getSignInBottomTabResponse = (GetSignInBottomTabResponse) ((GetSignInBottomTabResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (getSignInBottomTabResponse != null && getSignInBottomTabResponse.getSignInBottomTabData != null) {
				if (getSignInBottomTabResponse.getSignInBottomTabData.signInBottomTabInfos != null) {
					for (GetSignInBottomTabData.SignInBottomTabInfosBean signInBottomTabInfosBean : getSignInBottomTabResponse.getSignInBottomTabData.signInBottomTabInfos) {
						signInBottomTabInfosBean.link = Setting.encode(signInBottomTabInfosBean.link);
					}
				}
			}
			return getSignInBottomTabResponse;
		} else if (obj instanceof EquityCardAndBackGroundResponse) {
			EquityCardAndBackGroundResponse equityCardAndBackGroundResponse = null;
			try {
				equityCardAndBackGroundResponse = (EquityCardAndBackGroundResponse) ((EquityCardAndBackGroundResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (equityCardAndBackGroundResponse != null && equityCardAndBackGroundResponse.equityCardAndBackGroundData != null) {
				if (equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo != null) {
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo.backGroundImgUrl = Setting.encode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo.backGroundImgUrl);
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo.link = Setting.encode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo.link);
				}

				if (equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo != null) {
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo.backGroundImgUrl = Setting.encode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo.backGroundImgUrl);
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo.link = Setting.encode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo.link);
				}

				if (equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo != null) {
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo.userLevelImgUrl = Setting.encode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo.userLevelImgUrl);
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo.link = Setting.encode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo.link);
				}

				if (equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityList != null) {
					for (EquityCardAndBackGroundData.EquityListInfoBean equityListInfoBean : equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityList) {
						equityListInfoBean.iconUrl = Setting.encode(equityListInfoBean.iconUrl);
						equityListInfoBean.link = Setting.encode(equityListInfoBean.link);
					}
				}
			}
			return equityCardAndBackGroundResponse;
		} else if (obj instanceof UsageCardResponse) {
			UsageCardResponse usageCardResponse = null;
			try {
				usageCardResponse = (UsageCardResponse) ((UsageCardResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (usageCardResponse != null && usageCardResponse.usageCardData != null) {
				if (usageCardResponse.usageCardData.firstLevelTabInfos != null) {
					for (UsageCardData.FirstLevelTabInfosBean firstLevelTabInfosBean : usageCardResponse.usageCardData.firstLevelTabInfos) {
						if (firstLevelTabInfosBean.bottomAdvInfos != null) {
							for (CompoundAdItem compoundAdItem : firstLevelTabInfosBean.bottomAdvInfos) {
								compoundAdItem.iconUrl = Setting.encode(compoundAdItem.iconUrl);
								compoundAdItem.link = Setting.encode(compoundAdItem.link);
							}
						}

						if (firstLevelTabInfosBean.contentArea != null) {
							if (firstLevelTabInfosBean.contentArea.advertisingSpaceInfos != null) {
								for (CompoundAdItem compoundAdItem : firstLevelTabInfosBean.contentArea.advertisingSpaceInfos) {
									compoundAdItem.iconUrl = Setting.encode(compoundAdItem.iconUrl);
									compoundAdItem.link = Setting.encode(compoundAdItem.link);
								}
							}
						}
					}
				}
			}
			return usageCardResponse;
		} else if (obj instanceof HotSelectionCardResponse) {
			HotSelectionCardResponse hotSelectionCardResponse = null;
			try {
				hotSelectionCardResponse = (HotSelectionCardResponse) ((HotSelectionCardResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (hotSelectionCardResponse != null && hotSelectionCardResponse.hotSelectionCardData != null) {
				if (hotSelectionCardResponse.hotSelectionCardData.hotSelectionCardInfos != null) {
					for (HotSelectionCardData.HotSelectionCardInfosBean hotSelectionCardInfosBean : hotSelectionCardResponse.hotSelectionCardData.hotSelectionCardInfos) {

						hotSelectionCardInfosBean.cardBackground = Setting.encode(hotSelectionCardInfosBean.cardBackground);
						hotSelectionCardInfosBean.cardLink = Setting.encode(hotSelectionCardInfosBean.cardLink);
						hotSelectionCardInfosBean.tabIconUrl = Setting.encode(hotSelectionCardInfosBean.tabIconUrl);


						if (hotSelectionCardInfosBean.contentList != null) {
							for (HotSelectionCardData.HotSelectionCardInfosBean.ContentListBean contentListBean : hotSelectionCardInfosBean.contentList) {
								contentListBean.imageUrl = Setting.encode(contentListBean.imageUrl);
								contentListBean.link = Setting.encode(contentListBean.link);
							}
						}

					}
				}
			}
			return hotSelectionCardResponse;
		} else if (obj instanceof QueryMessageChannelResponse) {
			QueryMessageChannelResponse queryMessageChannelResponse = null;
			try {
				queryMessageChannelResponse = (QueryMessageChannelResponse) ((QueryMessageChannelResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryMessageChannelResponse != null && queryMessageChannelResponse.queryMessageChannelData != null) {
				if (queryMessageChannelResponse.queryMessageChannelData.marketingMessageTip != null) {
					queryMessageChannelResponse.queryMessageChannelData.marketingMessageTip.iconUrl = Setting.encode(queryMessageChannelResponse.queryMessageChannelData.marketingMessageTip.iconUrl);
				}

				if (queryMessageChannelResponse.queryMessageChannelData.pRANConfigureBean != null) {
					queryMessageChannelResponse.queryMessageChannelData.pRANConfigureBean.background = Setting.encode(queryMessageChannelResponse.queryMessageChannelData.pRANConfigureBean.background);
				}

				if (queryMessageChannelResponse.queryMessageChannelData.marketingMessageList != null) {
					for (QueryMessageChannelData.MarketingMessageListBean marketingMessageListBean : queryMessageChannelResponse.queryMessageChannelData.marketingMessageList) {
						marketingMessageListBean.iconUrl = Setting.encode(marketingMessageListBean.iconUrl);
						marketingMessageListBean.link = Setting.encode(marketingMessageListBean.link);
					}
				}

				if (queryMessageChannelResponse.queryMessageChannelData.serviceMessageClassifyList != null) {
					for (QueryMessageChannelData.ServiceMessageClassifyListBean serviceMessageClassifyListBean : queryMessageChannelResponse.queryMessageChannelData.serviceMessageClassifyList) {
						serviceMessageClassifyListBean.iconUrl = Setting.encode(serviceMessageClassifyListBean.iconUrl);
						serviceMessageClassifyListBean.link = Setting.encode(serviceMessageClassifyListBean.link);
					}
				}

				if (queryMessageChannelResponse.queryMessageChannelData.messageSettingList != null) {
					for (QueryMessageChannelData.MessageSettingListBean messageSettingListBean : queryMessageChannelResponse.queryMessageChannelData.messageSettingList) {
						messageSettingListBean.iconUrl = Setting.encode(messageSettingListBean.iconUrl);
					}
				}

				if (queryMessageChannelResponse.queryMessageChannelData.msgList != null) {
					for (QueryMessageChannelData.MsgListBean msgListBean : queryMessageChannelResponse.queryMessageChannelData.msgList) {
						msgListBean.background = Setting.encode(msgListBean.background);
						msgListBean.headImage = Setting.encode(msgListBean.headImage);
						msgListBean.link = Setting.encode(msgListBean.link);

						if (msgListBean.functionBean != null) {
							msgListBean.functionBean.iconUrl = Setting.encode(msgListBean.functionBean.iconUrl);
							msgListBean.functionBean.link = Setting.encode(msgListBean.functionBean.link);
						}
					}
				}

				if (queryMessageChannelResponse.queryMessageChannelData.serviceMessageList != null) {
					for (QueryMessageChannelData.MsgListBean msgListBean : queryMessageChannelResponse.queryMessageChannelData.serviceMessageList) {
						msgListBean.background = Setting.encode(msgListBean.background);
						msgListBean.headImage = Setting.encode(msgListBean.headImage);
						msgListBean.link = Setting.encode(msgListBean.link);

						if (msgListBean.functionBean != null) {
							msgListBean.functionBean.iconUrl = Setting.encode(msgListBean.functionBean.iconUrl);
							msgListBean.functionBean.link = Setting.encode(msgListBean.functionBean.link);
						}
					}
				}

			}
			return queryMessageChannelResponse;
		} else if (obj instanceof QueryMyChannelTopResponse) {
			QueryMyChannelTopResponse queryMyChannelTopResponse = null;
			try {
				queryMyChannelTopResponse = (QueryMyChannelTopResponse) ((QueryMyChannelTopResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryMyChannelTopResponse != null && queryMyChannelTopResponse.queryMyChannelTopData != null) {

				queryMyChannelTopResponse.queryMyChannelTopData.headImagePendantIcon = Setting.encode(queryMyChannelTopResponse.queryMyChannelTopData.headImagePendantIcon);

				if (queryMyChannelTopResponse.queryMyChannelTopData.topAdList != null) {
					for (CompoundAdItem compoundAdItem : queryMyChannelTopResponse.queryMyChannelTopData.topAdList) {
						compoundAdItem.iconUrl = Setting.encode(compoundAdItem.iconUrl);
						compoundAdItem.link = Setting.encode(compoundAdItem.link);
					}
				}

				if (queryMyChannelTopResponse.queryMyChannelTopData.userTagList != null) {
					for (QueryMyChannelTopData.UserTagListBean userTagListBean : queryMyChannelTopResponse.queryMyChannelTopData.userTagList) {
						userTagListBean.link = Setting.encode(userTagListBean.link);

						if (userTagListBean.iconList != null) {
							for (String iconUrl : userTagListBean.iconList) {
								iconUrl = Setting.encode(iconUrl);
							}
						}
					}
				}
			}
			return queryMyChannelTopResponse;
		} else if (obj instanceof MyChannelFloorResponse) {
			MyChannelFloorResponse myChannelFloorResponse = null;
			try {
				myChannelFloorResponse = (MyChannelFloorResponse) ((MyChannelFloorResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (myChannelFloorResponse != null && myChannelFloorResponse.myChannelFloorData != null) {
				if (myChannelFloorResponse.myChannelFloorData.myChannelFloorList != null) {
					for (MyChannelFloorData.MyChannelFloorListBean myChannelFloorListBean : myChannelFloorResponse.myChannelFloorData.myChannelFloorList) {
						myChannelFloorListBean.wapLink = Setting.encode(myChannelFloorListBean.wapLink);
					}
				}

			}
			return myChannelFloorResponse;
		} else {
			return obj;
		}
	}

	/***
	 * 解密处理
	 * @param obj
	 * @return
	 */
	private Object decodeResp(Object obj){
		if (obj instanceof QueryAdListResponse){
			QueryAdListResponse adListResponse = null;
			try {
				adListResponse = (QueryAdListResponse) ((QueryAdListResponse)obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (adListResponse != null && adListResponse.getAdItems() != null) {
				for (AdItem ad : adListResponse.getAdItems()) {
					String link = ad.getLink();
					String imageUrl = ad.getImageUrl();
					String iconUrl = ad.getIconUrl();
					String decodeLink = Setting.decode(link);
					String decodeImageUrl = Setting.decode(imageUrl);
					String decodeIconUrl = Setting.decode(iconUrl);
					ad.setLink(decodeLink);
					ad.setImageUrl(decodeImageUrl);
					ad.setIconUrl(decodeIconUrl);

				}
			}
			return  adListResponse;
		} else if (obj instanceof QueryTablebarResponse) {
			QueryTablebarResponse tablebarResponse = null;
			try {
				tablebarResponse = (QueryTablebarResponse) ((QueryTablebarResponse)obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (tablebarResponse != null && tablebarResponse.queryTablebarBean.tableBarInfos != null) {
				for (QueryTablebarBean.TableBarInfosBean bean : tablebarResponse.queryTablebarBean.tableBarInfos) {
					bean.clickIcon = Setting.decode(bean.clickIcon);
					bean.normalIcon = Setting.decode(bean.normalIcon);
					bean.backToTopIcon = Setting.decode(bean.backToTopIcon);
					bean.showBigIcon = Setting.decode(bean.showBigIcon);
					bean.specialTag = Setting.decode(bean.specialTag);
					bean.link = Setting.decode(bean.link);
				}
			}
			if (tablebarResponse != null && tablebarResponse.queryTablebarBean.tableBarInfosForPremiumEdition != null) {
				for (QueryTablebarBean.TableBarInfosBean bean : tablebarResponse.queryTablebarBean.tableBarInfosForPremiumEdition) {
					bean.clickIcon = Setting.decode(bean.clickIcon);
					bean.normalIcon = Setting.decode(bean.normalIcon);
					bean.backToTopIcon = Setting.decode(bean.backToTopIcon);
					bean.showBigIcon = Setting.decode(bean.showBigIcon);
					bean.specialTag = Setting.decode(bean.specialTag);
					bean.link = Setting.decode(bean.link);
				}
			}
			return tablebarResponse;
		} else if (obj instanceof QueryAdvertisingSpaceResponse) {
			QueryAdvertisingSpaceResponse queryAdvertisingSpaceResponse = null;
			try {
				queryAdvertisingSpaceResponse = (QueryAdvertisingSpaceResponse) ((QueryAdvertisingSpaceResponse)obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryAdvertisingSpaceResponse != null && queryAdvertisingSpaceResponse.queryAdvertisingSpaceData.advertisingSpaceInfos != null) {
				for (AdItem ad : queryAdvertisingSpaceResponse.queryAdvertisingSpaceData.advertisingSpaceInfos) {
					String link = ad.getLink();
					String imageUrl = ad.getImageUrl();
					String iconUrl = ad.getIconUrl();
					String decodeLink = Setting.decode(link);
					String decodeImageUrl = Setting.decode(imageUrl);
					String decodeIconUrl = Setting.decode(iconUrl);
					ad.setLink(decodeLink);
					ad.setImageUrl(decodeImageUrl);
					ad.setIconUrl(decodeIconUrl);
				}
			}
			return queryAdvertisingSpaceResponse;
		} else if (obj instanceof QueryMyChannelAdvSpaceResponse) {
			QueryMyChannelAdvSpaceResponse queryMyChannelAdvSpaceResponse = null;
			try {
				queryMyChannelAdvSpaceResponse = (QueryMyChannelAdvSpaceResponse) ((QueryMyChannelAdvSpaceResponse)obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryMyChannelAdvSpaceResponse != null && queryMyChannelAdvSpaceResponse.queryMyChannelAdvSpaceData.myChannelAdvSpaceInfos != null) {
				for (AdItem ad : queryMyChannelAdvSpaceResponse.queryMyChannelAdvSpaceData.myChannelAdvSpaceInfos) {
					String link = ad.getLink();
					String imageUrl = ad.getImageUrl();
					String iconUrl = ad.getIconUrl();
					String decodeLink = Setting.decode(link);
					String decodeImageUrl = Setting.decode(imageUrl);
					String decodeIconUrl = Setting.decode(iconUrl);
					ad.setLink(decodeLink);
					ad.setImageUrl(decodeImageUrl);
					ad.setIconUrl(decodeIconUrl);
				}
			}
			return queryMyChannelAdvSpaceResponse;
		} else if (obj instanceof IntelligentAssistantResponse) {
			IntelligentAssistantResponse intelligentAssistantResponse = null;
			try {
				intelligentAssistantResponse = (IntelligentAssistantResponse) ((IntelligentAssistantResponse)obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (intelligentAssistantResponse != null && intelligentAssistantResponse.intelligentAssistantData.advertisingInfos != null) {
				for (IntelligentAssistantData.AdvertisingInfosBean advertisingInfosBean : intelligentAssistantResponse.intelligentAssistantData.advertisingInfos) {
					advertisingInfosBean.imageUrl = Setting.decode(advertisingInfosBean.imageUrl);
					advertisingInfosBean.link = Setting.decode(advertisingInfosBean.link);
				}
			}
			if (intelligentAssistantResponse != null && intelligentAssistantResponse.intelligentAssistantData.helpInfos != null) {
				for (IntelligentAssistantData.HelpInfosBean helpInfosBean : intelligentAssistantResponse.intelligentAssistantData.helpInfos) {
					helpInfosBean.iconUrl = Setting.decode(helpInfosBean.iconUrl);
				}
			}
			return intelligentAssistantResponse;
		} else if (obj instanceof QueryRechargeSudokuResponse) {
			QueryRechargeSudokuResponse queryRechargeSudokuResponse = null;
			try {
				queryRechargeSudokuResponse = (QueryRechargeSudokuResponse) ((QueryRechargeSudokuResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryRechargeSudokuResponse != null && queryRechargeSudokuResponse.queryRechargeSudokuData.defaultList != null) {
				for (QueryRechargeSudokuData.DefaultListBean defaultListBean : queryRechargeSudokuResponse.queryRechargeSudokuData.defaultList) {
					defaultListBean.imageUrl = Setting.decode(defaultListBean.imageUrl);
					defaultListBean.link = Setting.decode(defaultListBean.link);
				}
			}
			if (queryRechargeSudokuResponse != null && queryRechargeSudokuResponse.queryRechargeSudokuData.recommendList != null) {
				for (QueryRechargeSudokuData.DefaultListBean recommendListBean : queryRechargeSudokuResponse.queryRechargeSudokuData.recommendList) {
					recommendListBean.imageUrl = Setting.decode(recommendListBean.imageUrl);
					recommendListBean.link = Setting.decode(recommendListBean.link);
				}
			}
			return queryRechargeSudokuResponse;
		} else if (obj instanceof QueryMyChannelPageHeadResponse) {
			QueryMyChannelPageHeadResponse queryMyChannelPageHeadResponse = null;
			try {
				queryMyChannelPageHeadResponse = (QueryMyChannelPageHeadResponse) ((QueryMyChannelPageHeadResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryMyChannelPageHeadResponse != null && queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.portalList != null) {
				for (QueryMyChannelPageHeadData.PortalListBean portalListBean : queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.portalList) {
					portalListBean.iconUrl = Setting.decode(portalListBean.iconUrl);
					portalListBean.link = Setting.decode(portalListBean.link);
				}
			}
			if (queryMyChannelPageHeadResponse != null && queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo != null) {
				queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo.iconUrl = Setting.decode(queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo.iconUrl);
				queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo.link = Setting.decode(queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.equityInfo.link);
			}
			if (queryMyChannelPageHeadResponse != null && queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf != null) {
				queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf.backgroundPic = Setting.decode(queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf.backgroundPic);
				queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf.link = Setting.decode(queryMyChannelPageHeadResponse.queryMyChannelPageHeadData.backgroundPicConf.link);
			}
			return queryMyChannelPageHeadResponse;
		} else if (obj instanceof GetRechargePageTabResponse) {
			GetRechargePageTabResponse getRechargePageTabResponse = null;
			try {
				getRechargePageTabResponse = (GetRechargePageTabResponse) ((GetRechargePageTabResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (getRechargePageTabResponse != null && getRechargePageTabResponse.getRechargePageTabData.firTabList != null) {
				for (GetRechargePageTabData.FirTabListBean firTabListBean : getRechargePageTabResponse.getRechargePageTabData.firTabList) {
					firTabListBean.iconUrl = Setting.decode(firTabListBean.iconUrl);
					firTabListBean.imageUrl = Setting.decode(firTabListBean.imageUrl);
					firTabListBean.link = Setting.decode(firTabListBean.link);
				}
			}
			if (getRechargePageTabResponse != null && getRechargePageTabResponse.getRechargePageTabData.noticeConf != null) {
				for (GetRechargePageTabData.NoticeConfBean noticeConfBean : getRechargePageTabResponse.getRechargePageTabData.noticeConf) {
					noticeConfBean.link = Setting.decode(noticeConfBean.link);
				}
			}
			if (getRechargePageTabResponse != null && getRechargePageTabResponse.getRechargePageTabData.sndTabConfList != null) {
				for (GetRechargePageTabData.SndTabConfListBean sndTabConfListBean : getRechargePageTabResponse.getRechargePageTabData.sndTabConfList) {
					sndTabConfListBean.link = Setting.decode(sndTabConfListBean.link);
				}
			}
			return getRechargePageTabResponse;
		} else if (obj instanceof CompoundADSpaceResponse) {
			CompoundADSpaceResponse compoundADSpaceResponse = null;
			try {
				compoundADSpaceResponse = (CompoundADSpaceResponse) ((CompoundADSpaceResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (compoundADSpaceResponse != null && compoundADSpaceResponse.compoundADSpaceData.compoundADSpaceInfos != null) {
				for (CompoundADSpaceData.CompoundADSpaceInfoBean compoundADSpaceInfoBean : compoundADSpaceResponse.compoundADSpaceData.compoundADSpaceInfos) {
					if (compoundADSpaceInfoBean.advertisingSpaceInfos != null) {
						for (AdItem ad : compoundADSpaceInfoBean.advertisingSpaceInfos) {
							String link = ad.getLink();
							String imageUrl = ad.getImageUrl();
							String iconUrl = ad.getIconUrl();
							String decodeLink = Setting.decode(link);
							String decodeImageUrl = Setting.decode(imageUrl);
							String decodeIconUrl = Setting.decode(iconUrl);
							ad.setLink(decodeLink);
							ad.setImageUrl(decodeImageUrl);
							ad.setIconUrl(decodeIconUrl);
						}
					}
				}
			}
			return compoundADSpaceResponse;
		} else if (obj instanceof HotWordSearchListResponse) {
			HotWordSearchListResponse hotWordSearchListResponse = null;
			try {
				hotWordSearchListResponse = (HotWordSearchListResponse) ((HotWordSearchListResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (hotWordSearchListResponse != null && hotWordSearchListResponse.hotWordSearchListData.hotWordSearchListInfos != null) {
				for (HotWordSearchListData.HotWordSearchListInfosBean hotWordSearchListInfosBean : hotWordSearchListResponse.hotWordSearchListData.hotWordSearchListInfos) {
					hotWordSearchListInfosBean.iconUrl = Setting.decode(hotWordSearchListInfosBean.iconUrl);

					if (hotWordSearchListInfosBean.hotWordSearchListSecInfos != null) {
						for (HotWordSearchListData.HotWordSearchListInfosBean.HotWordSearchListSecInfosBean hotWordSearchListSecInfosBean : hotWordSearchListInfosBean.hotWordSearchListSecInfos) {
							if (hotWordSearchListSecInfosBean.hotWordSearchListThrInfos != null) {
								for (HotWordSearchListData.HotWordSearchListInfosBean.HotWordSearchListSecInfosBean.HotWordSearchListThrInfosBean hotWordSearchListThrInfosBean : hotWordSearchListSecInfosBean.hotWordSearchListThrInfos) {
									hotWordSearchListThrInfosBean.iconUrl = Setting.decode(hotWordSearchListThrInfosBean.iconUrl);
									hotWordSearchListThrInfosBean.link = Setting.decode(hotWordSearchListThrInfosBean.link);
								}
							}
						}
					}
				}
			}
			return hotWordSearchListResponse;
		} else if (obj instanceof HotWordSearchV2Response) {
			HotWordSearchV2Response hotWordSearchV2Response = null;
			try {
				hotWordSearchV2Response = (HotWordSearchV2Response) ((HotWordSearchV2Response) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (hotWordSearchV2Response != null) {
				hotWordSearchV2Response.hotWordSearchV2Data.link = Setting.decode(hotWordSearchV2Response.hotWordSearchV2Data.link);

				if (hotWordSearchV2Response.hotWordSearchV2Data.hotWordSearchV2Infos != null) {
					for (HotWordSearchV2Data.HotWordSearchV2InfosBean hotWordSearchV2InfosBean : hotWordSearchV2Response.hotWordSearchV2Data.hotWordSearchV2Infos) {
						hotWordSearchV2InfosBean.link = Setting.decode(hotWordSearchV2InfosBean.link);
					}
				}
				if (hotWordSearchV2Response.hotWordSearchV2Data.recommenInfos != null) {
					for (HotWordSearchV2Data.RecommenInfosBean recommenInfosBean : hotWordSearchV2Response.hotWordSearchV2Data.recommenInfos) {
						recommenInfosBean.link = Setting.decode(recommenInfosBean.link);
						recommenInfosBean.iconUrl = Setting.decode(recommenInfosBean.iconUrl);
					}
				}
			}
			return hotWordSearchV2Response;
		} else if (obj instanceof QryIndexTopBackGroundResponse) {
			QryIndexTopBackGroundResponse qryIndexTopBackGroundResponse = null;
			try {
				qryIndexTopBackGroundResponse = (QryIndexTopBackGroundResponse) ((QryIndexTopBackGroundResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (qryIndexTopBackGroundResponse != null && qryIndexTopBackGroundResponse.qryIndexTopBackGroundData != null) {
				qryIndexTopBackGroundResponse.qryIndexTopBackGroundData.link = Setting.decode(qryIndexTopBackGroundResponse.qryIndexTopBackGroundData.link);

				if (qryIndexTopBackGroundResponse.qryIndexTopBackGroundData.backGroundLayerBeans != null) {
					for (QryIndexTopBackGroundData.BackGroundLayerBeansBean backGroundLayerBeansBean : qryIndexTopBackGroundResponse.qryIndexTopBackGroundData.backGroundLayerBeans) {
						backGroundLayerBeansBean.imageUrl = Setting.decode(backGroundLayerBeansBean.imageUrl);
					}
				}
			}
			return qryIndexTopBackGroundResponse;
		} else if (obj instanceof QueryTabCardAdListResponse) {
			QueryTabCardAdListResponse queryTabCardAdListResponse = null;
			try {
				queryTabCardAdListResponse = (QueryTabCardAdListResponse) ((QueryTabCardAdListResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryTabCardAdListResponse != null) {
				if (queryTabCardAdListResponse.queryTabCardAdListData.tabList != null) {
					for (QueryTabCardAdListData.TabListBean tabListBean : queryTabCardAdListResponse.queryTabCardAdListData.tabList) {
						if (tabListBean.floorList != null) {
							for (QueryTabCardAdListData.TabListBean.FloorListBean floorListBean : tabListBean.floorList) {
								if (floorListBean.advertisingList != null) {
									for (QueryTabCardAdListData.TabListBean.FloorListBean.AdvertisingListBean advertisingListBean : floorListBean.advertisingList) {
										advertisingListBean.link = Setting.decode(advertisingListBean.link);
										advertisingListBean.iconUrl = Setting.decode(advertisingListBean.iconUrl);
									}
								}
							}
						}
					}
				}
			}
			return queryTabCardAdListResponse;
		} else if (obj instanceof QueryAssistTopConfResponse) {
			QueryAssistTopConfResponse queryAssistTopConfResponse = null;
			try {
				queryAssistTopConfResponse = (QueryAssistTopConfResponse) ((QueryAssistTopConfResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (queryAssistTopConfResponse != null && queryAssistTopConfResponse.queryAssistTopConfData != null) {
				if (queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean != null) {
					queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.icon = Setting.decode(queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.icon);
					queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.imageUrl = Setting.decode(queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.imageUrl);
					queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.shareUrl = Setting.decode(queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.shareUrl);
					queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.titleIcon = Setting.decode(queryAssistTopConfResponse.queryAssistTopConfData.clientSharingDataBean.titleIcon);
				}

				if (queryAssistTopConfResponse.queryAssistTopConfData.tabList != null) {
					for (QueryAssistTopConfData.TabListBean tabListBean : queryAssistTopConfResponse.queryAssistTopConfData.tabList) {
						tabListBean.normalIcon = Setting.decode(tabListBean.normalIcon);
						tabListBean.selectedIcon = Setting.decode(tabListBean.selectedIcon);
						tabListBean.link = Setting.decode(tabListBean.link);
					}
				}
			}
			return queryAssistTopConfResponse;
		} else if (obj instanceof DefaultMemberConfResponse) {
			DefaultMemberConfResponse defaultMemberConfResponse = null;
			try {
				defaultMemberConfResponse = (DefaultMemberConfResponse) ((DefaultMemberConfResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (defaultMemberConfResponse != null && defaultMemberConfResponse.defaultMemberConfData != null) {
				if (defaultMemberConfResponse.defaultMemberConfData.headPortraits != null) {
					for (DefaultMemberConfData.HeadPortraitsBean headPortraitsBean : defaultMemberConfResponse.defaultMemberConfData.headPortraits) {
						headPortraitsBean.imageUrl = Setting.decode(headPortraitsBean.imageUrl);
					}
				}
			}
			return defaultMemberConfResponse;
		} else if (obj instanceof GetQueryProcessingTabResponse) {
			GetQueryProcessingTabResponse getQueryProcessingTabResponse = null;
			try {
				getQueryProcessingTabResponse = (GetQueryProcessingTabResponse) ((GetQueryProcessingTabResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (getQueryProcessingTabResponse != null && getQueryProcessingTabResponse.getQueryProcessingTabData != null) {
				if (getQueryProcessingTabResponse.getQueryProcessingTabData.customerService != null) {
					getQueryProcessingTabResponse.getQueryProcessingTabData.customerService.iconUrl = Setting.decode(getQueryProcessingTabResponse.getQueryProcessingTabData.customerService.iconUrl);
					getQueryProcessingTabResponse.getQueryProcessingTabData.customerService.link = Setting.decode(getQueryProcessingTabResponse.getQueryProcessingTabData.customerService.link);
				}

				if (getQueryProcessingTabResponse.getQueryProcessingTabData.topTabInfos != null) {
					for (GetQueryProcessingTabData.TopTabInfosBean topTabInfosBean : getQueryProcessingTabResponse.getQueryProcessingTabData.topTabInfos) {
						topTabInfosBean.backGroundImgUrl = Setting.decode(topTabInfosBean.backGroundImgUrl);
						topTabInfosBean.link = Setting.decode(topTabInfosBean.link);

						if (topTabInfosBean.functionalAreaDetailInfos != null) {
							for (CompoundAdItem compoundAdItem : topTabInfosBean.functionalAreaDetailInfos) {
								compoundAdItem.iconUrl = Setting.decode(compoundAdItem.iconUrl);
								compoundAdItem.link = Setting.decode(compoundAdItem.link);
							}
						}

						if (topTabInfosBean.cardTabInfos != null) {
							for (GetQueryProcessingTabData.TopTabInfosBean.CardTabInfosBean cardTabInfosBean : topTabInfosBean.cardTabInfos) {
								if (cardTabInfosBean.cardAreaInfos != null) {
									for (GetQueryProcessingTabData.TopTabInfosBean.CardTabInfosBean.CardAreaInfosBean cardAreaInfosBean : cardTabInfosBean.cardAreaInfos) {
										cardAreaInfosBean.iconUrl = Setting.decode(cardAreaInfosBean.iconUrl);
										cardAreaInfosBean.link = Setting.decode(cardAreaInfosBean.link);

										if (cardAreaInfosBean.functionalAreaDetailInfos != null) {
											for (CompoundAdItem compoundAdItem : cardAreaInfosBean.functionalAreaDetailInfos) {
												compoundAdItem.iconUrl = Setting.decode(compoundAdItem.iconUrl);
												compoundAdItem.link = Setting.decode(compoundAdItem.link);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			return getQueryProcessingTabResponse;
		} else if (obj instanceof GetSignInBottomTabResponse) {
			GetSignInBottomTabResponse getSignInBottomTabResponse = null;
			try {
				getSignInBottomTabResponse = (GetSignInBottomTabResponse) ((GetSignInBottomTabResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (getSignInBottomTabResponse != null && getSignInBottomTabResponse.getSignInBottomTabData != null) {
				if (getSignInBottomTabResponse.getSignInBottomTabData.signInBottomTabInfos != null) {
					for (GetSignInBottomTabData.SignInBottomTabInfosBean signInBottomTabInfosBean : getSignInBottomTabResponse.getSignInBottomTabData.signInBottomTabInfos) {
						signInBottomTabInfosBean.link = Setting.decode(signInBottomTabInfosBean.link);
					}
				}
			}
			return getSignInBottomTabResponse;
		} else if (obj instanceof EquityCardAndBackGroundResponse) {
			EquityCardAndBackGroundResponse equityCardAndBackGroundResponse = null;
			try {
				equityCardAndBackGroundResponse = (EquityCardAndBackGroundResponse) ((EquityCardAndBackGroundResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (equityCardAndBackGroundResponse != null && equityCardAndBackGroundResponse.equityCardAndBackGroundData != null) {
				if (equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo != null) {
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo.backGroundImgUrl = Setting.decode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo.backGroundImgUrl);
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo.link = Setting.decode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.backGroundImageInfo.link);
				}

				if (equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo != null) {
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo.backGroundImgUrl = Setting.decode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo.backGroundImgUrl);
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo.link = Setting.decode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityCardInfo.link);
				}

				if (equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo != null) {
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo.userLevelImgUrl = Setting.decode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo.userLevelImgUrl);
					equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo.link = Setting.decode(equityCardAndBackGroundResponse.equityCardAndBackGroundData.userStarLevelIconInfo.link);
				}
				if (equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityList != null) {
					for (EquityCardAndBackGroundData.EquityListInfoBean equityListInfoBean : equityCardAndBackGroundResponse.equityCardAndBackGroundData.equityList) {
						equityListInfoBean.iconUrl = Setting.decode(equityListInfoBean.iconUrl);
						equityListInfoBean.link = Setting.decode(equityListInfoBean.link);
					}
				}
			}
			return equityCardAndBackGroundResponse;
		} else if (obj instanceof UsageCardResponse) {
			UsageCardResponse usageCardResponse = null;
			try {
				usageCardResponse = (UsageCardResponse) ((UsageCardResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (usageCardResponse != null && usageCardResponse.usageCardData != null) {
				if (usageCardResponse.usageCardData.firstLevelTabInfos != null) {
					for (UsageCardData.FirstLevelTabInfosBean firstLevelTabInfosBean : usageCardResponse.usageCardData.firstLevelTabInfos) {
						if (firstLevelTabInfosBean.bottomAdvInfos != null) {
							for (CompoundAdItem compoundAdItem : firstLevelTabInfosBean.bottomAdvInfos) {
								compoundAdItem.iconUrl = Setting.decode(compoundAdItem.iconUrl);
								compoundAdItem.link = Setting.decode(compoundAdItem.link);
							}
						}

						if (firstLevelTabInfosBean.contentArea != null) {
							if (firstLevelTabInfosBean.contentArea.advertisingSpaceInfos != null) {
								for (CompoundAdItem compoundAdItem : firstLevelTabInfosBean.contentArea.advertisingSpaceInfos) {
									compoundAdItem.iconUrl = Setting.decode(compoundAdItem.iconUrl);
									compoundAdItem.link = Setting.decode(compoundAdItem.link);
								}
							}
						}
					}
				}
			}
			return usageCardResponse;
		} else if (obj instanceof HotSelectionCardResponse) {
			HotSelectionCardResponse hotSelectionCardResponse = null;
			try {
				hotSelectionCardResponse = (HotSelectionCardResponse) ((HotSelectionCardResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (hotSelectionCardResponse != null && hotSelectionCardResponse.hotSelectionCardData != null) {
				if (hotSelectionCardResponse.hotSelectionCardData.hotSelectionCardInfos != null) {
					for (HotSelectionCardData.HotSelectionCardInfosBean hotSelectionCardInfosBean : hotSelectionCardResponse.hotSelectionCardData.hotSelectionCardInfos) {

						hotSelectionCardInfosBean.cardBackground = Setting.decode(hotSelectionCardInfosBean.cardBackground);
						hotSelectionCardInfosBean.cardLink = Setting.decode(hotSelectionCardInfosBean.cardLink);
						hotSelectionCardInfosBean.tabIconUrl = Setting.decode(hotSelectionCardInfosBean.tabIconUrl);


						if (hotSelectionCardInfosBean.contentList != null) {
							for (HotSelectionCardData.HotSelectionCardInfosBean.ContentListBean contentListBean : hotSelectionCardInfosBean.contentList) {
								contentListBean.imageUrl = Setting.decode(contentListBean.imageUrl);
								contentListBean.link = Setting.decode(contentListBean.link);
							}
						}

					}
				}
			}
			return hotSelectionCardResponse;
		} else if (obj instanceof QueryMessageChannelResponse) {
			QueryMessageChannelResponse queryMessageChannelResponse = null;
			try {
				queryMessageChannelResponse = (QueryMessageChannelResponse) ((QueryMessageChannelResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryMessageChannelResponse != null && queryMessageChannelResponse.queryMessageChannelData != null) {
				if (queryMessageChannelResponse.queryMessageChannelData.marketingMessageTip != null) {
					queryMessageChannelResponse.queryMessageChannelData.marketingMessageTip.iconUrl = Setting.decode(queryMessageChannelResponse.queryMessageChannelData.marketingMessageTip.iconUrl);
				}

				if (queryMessageChannelResponse.queryMessageChannelData.pRANConfigureBean != null) {
					queryMessageChannelResponse.queryMessageChannelData.pRANConfigureBean.background = Setting.decode(queryMessageChannelResponse.queryMessageChannelData.pRANConfigureBean.background);
				}

				if (queryMessageChannelResponse.queryMessageChannelData.marketingMessageList != null) {
					for (QueryMessageChannelData.MarketingMessageListBean marketingMessageListBean : queryMessageChannelResponse.queryMessageChannelData.marketingMessageList) {
						marketingMessageListBean.iconUrl = Setting.decode(marketingMessageListBean.iconUrl);
						marketingMessageListBean.link = Setting.decode(marketingMessageListBean.link);
					}
				}

				if (queryMessageChannelResponse.queryMessageChannelData.serviceMessageClassifyList != null) {
					for (QueryMessageChannelData.ServiceMessageClassifyListBean serviceMessageClassifyListBean : queryMessageChannelResponse.queryMessageChannelData.serviceMessageClassifyList) {
						serviceMessageClassifyListBean.iconUrl = Setting.decode(serviceMessageClassifyListBean.iconUrl);
						serviceMessageClassifyListBean.link = Setting.decode(serviceMessageClassifyListBean.link);
					}
				}

				if (queryMessageChannelResponse.queryMessageChannelData.messageSettingList != null) {
					for (QueryMessageChannelData.MessageSettingListBean messageSettingListBean : queryMessageChannelResponse.queryMessageChannelData.messageSettingList) {
						messageSettingListBean.iconUrl = Setting.decode(messageSettingListBean.iconUrl);
					}
				}

				if (queryMessageChannelResponse.queryMessageChannelData.msgList != null) {
					for (QueryMessageChannelData.MsgListBean msgListBean : queryMessageChannelResponse.queryMessageChannelData.msgList) {
						msgListBean.background = Setting.decode(msgListBean.background);
						msgListBean.headImage = Setting.decode(msgListBean.headImage);
						msgListBean.link = Setting.decode(msgListBean.link);

						if (msgListBean.functionBean != null) {
							msgListBean.functionBean.iconUrl = Setting.decode(msgListBean.functionBean.iconUrl);
							msgListBean.functionBean.link = Setting.decode(msgListBean.functionBean.link);
						}
					}
				}

				if (queryMessageChannelResponse.queryMessageChannelData.serviceMessageList != null) {
					for (QueryMessageChannelData.MsgListBean msgListBean : queryMessageChannelResponse.queryMessageChannelData.serviceMessageList) {
						msgListBean.background = Setting.decode(msgListBean.background);
						msgListBean.headImage = Setting.decode(msgListBean.headImage);
						msgListBean.link = Setting.decode(msgListBean.link);

						if (msgListBean.functionBean != null) {
							msgListBean.functionBean.iconUrl = Setting.decode(msgListBean.functionBean.iconUrl);
							msgListBean.functionBean.link = Setting.decode(msgListBean.functionBean.link);
						}
					}
				}

			}
			return queryMessageChannelResponse;
		} else if (obj instanceof QueryMyChannelTopResponse) {
			QueryMyChannelTopResponse queryMyChannelTopResponse = null;
			try {
				queryMyChannelTopResponse = (QueryMyChannelTopResponse) ((QueryMyChannelTopResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (queryMyChannelTopResponse != null && queryMyChannelTopResponse.queryMyChannelTopData != null) {

				queryMyChannelTopResponse.queryMyChannelTopData.headImagePendantIcon = Setting.decode(queryMyChannelTopResponse.queryMyChannelTopData.headImagePendantIcon);

				if (queryMyChannelTopResponse.queryMyChannelTopData.topAdList != null) {
					for (CompoundAdItem compoundAdItem : queryMyChannelTopResponse.queryMyChannelTopData.topAdList) {
						compoundAdItem.iconUrl = Setting.decode(compoundAdItem.iconUrl);
						compoundAdItem.link = Setting.decode(compoundAdItem.link);
					}
				}

				if (queryMyChannelTopResponse.queryMyChannelTopData.userTagList != null) {
					for (QueryMyChannelTopData.UserTagListBean userTagListBean : queryMyChannelTopResponse.queryMyChannelTopData.userTagList) {
						userTagListBean.link = Setting.decode(userTagListBean.link);

						if (userTagListBean.iconList != null) {
							for (String iconUrl : userTagListBean.iconList) {
								iconUrl = Setting.decode(iconUrl);
							}
						}
					}
				}
			}
			return queryMyChannelTopResponse;
		} else if (obj instanceof MyChannelFloorResponse) {
			MyChannelFloorResponse myChannelFloorResponse = null;
			try {
				myChannelFloorResponse = (MyChannelFloorResponse) ((MyChannelFloorResponse) obj).deepCopy();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (myChannelFloorResponse != null && myChannelFloorResponse.myChannelFloorData != null) {
				if (myChannelFloorResponse.myChannelFloorData.myChannelFloorList != null) {
					for (MyChannelFloorData.MyChannelFloorListBean myChannelFloorListBean : myChannelFloorResponse.myChannelFloorData.myChannelFloorList) {
						myChannelFloorListBean.wapLink = Setting.decode(myChannelFloorListBean.wapLink);
					}
				}

			}
			return myChannelFloorResponse;
		} else {
			return obj;
		}
	}

}
