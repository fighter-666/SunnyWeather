package com.example.sunnyweather.base;

import java.util.HashMap;
import java.util.Map;

public class ErrorCode {

	private static Map<String, String> errorMap = new HashMap<String, String>();
	
	private static void initMap(){
		if(errorMap != null && errorMap.size() == 0){
			errorMap.put("1","服务器发生异常。");
			errorMap.put("2","请求参数错误或消息体解析失败。");
			errorMap.put("3","操作失败");
			errorMap.put("101","用户名密码不匹配");
			errorMap.put("102","用户名已存在");
			errorMap.put("103","旧密码不正确");
			errorMap.put("104","无效激活码(不存在或已失效);");
			errorMap.put("105","用户名与邮箱不匹配");
			errorMap.put("106","第3方帐号已被绑定");
			errorMap.put("107","用户名不存在");
			errorMap.put("108","合约未找到");
			errorMap.put("109","库存未找到");
			errorMap.put("110","地区编号不存在");
			errorMap.put("111","错误的地区编码");
			errorMap.put("112","设置邮箱状态为已验证失败");
			errorMap.put("113","销售品ID无效");
			errorMap.put("114","收货地址ID不存在");
			errorMap.put("115","订单不存在");
			errorMap.put("116","寄存购物车失败");
			errorMap.put("117","用户名与手机号码不匹配");
			errorMap.put("118","手机号码格式不正确");
			errorMap.put("120","第3方帐号不存在");
			errorMap.put("121","指定帐号不存在");
			errorMap.put("122","手机号不存在");
			errorMap.put("123","充值卡充值失败");
			errorMap.put("124","不能寄存不同省份商品");
			errorMap.put("126","银行卡直充手机号码状态异常");
			errorMap.put("127","[充值卡]话费充值/流量充值系统无法定位充值类型");
			errorMap.put("128","系统检测到您当天充值失败次数过多,当天已禁止改操作");
			errorMap.put("129","获取银行直充金额失败");
			errorMap.put("130","充值卡库存不足");
			errorMap.put("131","无效数据");
			errorMap.put("132","撤消失败,非可取消订单状态");
			errorMap.put("133","保存失败");
			errorMap.put("134","充值卡预订信息保存失败");
			errorMap.put("135","号码已经预占");
			errorMap.put("136","号码不存在");
			errorMap.put("137","库存不足");
			errorMap.put("138","团购库存不足");
			errorMap.put("139","商户号不能为空");
			errorMap.put("140","无效的地区编码类型");
			errorMap.put("141","订购增值业务失败");
			errorMap.put("142","退订增值业务失败");
			errorMap.put("143","关联绑定失败");
			errorMap.put("144","密码修改失败");
			errorMap.put("145","密码重置失败");
			errorMap.put("146","获取基本资料失败");
			errorMap.put("147","获取我的产品失败");
			errorMap.put("148","快速购买订单查询失败");
			errorMap.put("149","验证码错误");
			errorMap.put("150","订单已经被绑定");
			errorMap.put("151","未找到相关记录");
			errorMap.put("152","获取增值业务列表失败");
			errorMap.put("153","欠费查询失败");
			errorMap.put("154","缺少必要参数");
			errorMap.put("155","无效的旧号码买合约机短信验证码");
			errorMap.put("156","换货销售品不存在或数量异常");
			errorMap.put("157","卡密充值操作过于频繁，请稍后重试");
			errorMap.put("160","团购已过期");
			errorMap.put("161","该用户购买数量已超过允许数量");
			errorMap.put("162","订单下发全部资源实占失败");
			errorMap.put("163","终端预占失败");
			errorMap.put("164","赠品不可购买");
			errorMap.put("165","支付流水号获取失败,订单不支持该操作");
			errorMap.put("170","未查询到物流信息");
			errorMap.put("171","无法确认订单AB类型");
			errorMap.put("172","无效的商户账号");
			errorMap.put("173","云销售品无对应的套餐数据");
			errorMap.put("182","充值卡购买失败,销售品类型错误");
			errorMap.put("188","订单提交终端或号码预占失败");
			errorMap.put("189","订单提交客户资料下发失败");
			errorMap.put("191","退款失败，退款金额大于订单金额");
			errorMap.put("200","第3方系统业务失败");
			errorMap.put("209","支付反馈失败,该订单状态不是未支付状态");
			errorMap.put("210","支付反馈失败,该订单状态不是已支付状态");
			errorMap.put("211","交易状态为失败，业务层不做处理");
			errorMap.put("300","支付反馈请求参数错误，请渠道自查");
			errorMap.put("301","支付反馈订单号不存在，请渠道自查");
			errorMap.put("302","支付反馈处理异常，需再次发起");
			errorMap.put("303","保存支付反馈信息失败，需再次发起");
			errorMap.put("304","订单业务处理失败，需人工核查");
			errorMap.put("305","订单状态支付中设置失败");
			errorMap.put("306","无效的支付流水号");
			errorMap.put("307","");
			errorMap.put("308","");
			errorMap.put("309","");
			errorMap.put("310","");
			errorMap.put("311","");
			errorMap.put("5002","订单不存在");
			errorMap.put("5003","订单未支付");
			errorMap.put("5004","订单金额不正确");
			errorMap.put("5005","结算失败");
			errorMap.put("5006","商户不存在");
			errorMap.put("5007","MD5摘要不匹配,该信息已被篡改");
			errorMap.put("5008","此订单已结算");
			errorMap.put("5009","此商户未开通手动结算确认功能");
			errorMap.put("11001","商户未注册");
			errorMap.put("11002","商户已注册，但未审批");
			errorMap.put("11003","商户已注册，已审批，但未开通");
			errorMap.put("11004","商户已注销");
			errorMap.put("11005","商户原自助服务用户密码错误");
			errorMap.put("11006","商户已注册，但未开通此业务");
			errorMap.put("11007","商户已注册，但未开通此接入方式");
			errorMap.put("11101","订单正在交易中，不允许再次交易");
			errorMap.put("11102","订单交易成功，不允许再次交易");
			errorMap.put("11103","订单交易结果未知，不允许再次交易");
			errorMap.put("11104","订单交易记录不存在或状态不为请求中");
			errorMap.put("11105","订单请求记录不存在或状态不为请求中");
			errorMap.put("11109","退款流水号重复");
			errorMap.put("11119","退款金额超出原始订单金额");
			errorMap.put("12002","请求记录已存在");
			errorMap.put("12003","结果状态不正确");
			errorMap.put("12004","该交易存在，但日志状态已经不是请求状态");
			errorMap.put("12011","银行方扣款失败");
			errorMap.put("12012","银行方扣款超时");
			errorMap.put("12013","卡或帐户类型不存在");
			errorMap.put("12014","卡或帐户密码不正确，但记录密码输错次数失败");
			errorMap.put("13001","银行未注册");
			errorMap.put("13002","银行已注册，但未审批");
			errorMap.put("13003","银行已注册，已审批，但未开通");
			errorMap.put("13004","银行已注销");
			errorMap.put("13005","银行已注册，但未开通此业务");
			errorMap.put("13006","银行交易记录不存在或状态不为请求中");
			errorMap.put("19999","操作失败");
			errorMap.put("X901","系统错误:");
			errorMap.put("X902","参数非空字段");
			errorMap.put("X903","参数类型异常");
			errorMap.put("X301","服务器拒绝");
			errorMap.put("X306","手机号码异常");
			errorMap.put("X307","非法用户");
			errorMap.put("X308","渠道号和店铺 ID 不能对应");
			errorMap.put("X309","暂时没有此业务");
			errorMap.put("X310","收藏中已经存在该商品");
			errorMap.put("X311","购买类型错误");
			errorMap.put("X312","该UserId无效");
			errorMap.put("X417","请输入正确的电信号码");
			errorMap.put("X704","数据查询失败");
			errorMap.put("X101","头节点校验错误");
			errorMap.put("X102","接口名称、渠道标识或者渠道密码为空");
			errorMap.put("X104","校验 token 失败");
			errorMap.put("X106","校验渠道密码失败");
			errorMap.put("X107","节点（ XX ）为空");
			errorMap.put("X109","请求报文格式错误");
			errorMap.put("X201","token 过期");
		}
	}
	
	public static String get(String key){
		initMap();
		if(errorMap.containsKey(key)){
			return errorMap.get(key);
		}
		else {
			return "";
		}
	}
}
