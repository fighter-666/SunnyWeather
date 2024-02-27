package com.example.sunnyweather.base;
 


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Content {

	private String attach = "test";			// 暂时无用字段
	private Map<String, Object> fieldData;	// 数据字段
	
	public Content() {
		super();
		fieldData = new HashMap<String, Object>();
	}
	
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public Map<String, Object> getFieldData() {
		return fieldData;
	}
	public void setFieldData(Map<String, Object> fieldData) {
		this.fieldData = fieldData;
	}

	public void put(String key, Object value) {
		fieldData.put(key, value);
	}
	
	private String MaptoXmlString() {
		StringBuffer xml = new StringBuffer();
		Iterator<Entry<String, Object>> iter = fieldData.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			if(val == null) {
				val = "";
				//System.out.print("键"+key+"的值为null！！！！");
				Log.e("键"+key+"的值为null！！！！");

			}
			xml.append("<").append(key).append(">");
			if(val instanceof String) {
				xml.append(val);
			} else if(val instanceof List<?>) {
				List<?> v = (List<?>)val;
				for(Object o : v){
					xml.append(o.toString());
				}
			} else {
				xml.append(val.toString());
			}
			xml.append("</").append(key).append(">");
		}
		return xml.toString();
	}

	public String toXmlString() {
		StringBuffer xml = new StringBuffer();
		String className = "Content";
		xml.append("<").append(className).append(">");
		xml.append("<Attach>").append(this.getAttach()).append("</Attach>");
		xml.append("<FieldData>").append(MaptoXmlString()).append("</FieldData>");
		xml.append("</").append(className).append(">");
		return xml.toString();
	}

	/**
	 * 提供json请求的参数写入方法
	 * @return
     */
	public JSONObject toJsonObject() throws Exception{
		JSONObject jsonObject = new JSONObject();
		JSONObject fieldDataJsonObject = new JSONObject();
		Iterator<Entry<String, Object>> iter = fieldData.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			Object val = entry.getValue();
			if(val == null) {
				val = "";
				//System.out.print("键"+key+"的值为null！！！！");
				Log.e("键"+key+"的值为null！！！！");
			}
			if(val instanceof String) {
				fieldDataJsonObject.put(key, val.toString());
			} else if(val instanceof List<?>) {
				List<?> v = (List<?>)val;
				for(Object o : v){
					fieldDataJsonObject.put(key, o.toString());
				}
			} else if(val instanceof JSONArray) {
				fieldDataJsonObject.put(key, val);
			} else {
				fieldDataJsonObject.put(key, val.toString());
			}
		}
		jsonObject.put("attach", attach);
		jsonObject.put("fieldData", fieldDataJsonObject);
		return jsonObject;
	}
}
