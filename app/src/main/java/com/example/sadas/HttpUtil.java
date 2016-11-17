package com.example.sadas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtil {
	public static final String BASE_URL = "http://10.0.2.2:8080/jsontest/servlet/ProductServlet";
	public static final String IMG_URL = "http://10.0.2.2:8080/jsontest/upload/";
	public static HttpClient httpClient = new DefaultHttpClient();
	
	// post�������ʷ�����������json�ַ���
	public static String getRequest(String url){
		String result = null;
		HttpGet httpGet = new HttpGet(url); 
		
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return result;
}
	 // �ַ���ת�ɼ�������
		public static void resultString2List(List<Map<String ,Object>> list, String str,String title) {
			try {
				JSONObject jsonObject = new JSONObject(str);
				JSONArray jsonArray = jsonObject.getJSONArray(title);
				
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject2 = jsonArray.getJSONObject(i);
					Map<String ,Object> map = new HashMap<String, Object>();
					Iterator<String> iterator = jsonObject2.keys();
					while (iterator.hasNext()) {
						String key = iterator.next();
						Object value = jsonObject2.get(key);
						map.put(key, value);
					}
					
					list.add(map);
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	        // post�������ʷ����������ؼ�������
		public static List<Map<String,Object>> getRequest2List(String url,String title){
			
			List<Map<String,Object>> list = new ArrayList<Map<String ,Object>>();
			
			resultString2List(list, url, title);
		
			return list;
			
		}
		
		// get�������ʷ�����������json�ַ���
		public static String postRequest(String url, Map<String,String> rawParams) throws Exception{
			
			HttpPost post = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			for (String key:rawParams.keySet()) {
				params.add(new BasicNameValuePair(key, rawParams.get(key)));
				
			}
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			
			HttpResponse httpResponse = httpClient.execute(post);
			
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				String result = EntityUtils.toString(httpResponse.getEntity());
				
	                        return result;
			}
			
			return null;
		}

	        //post���ʵķ��������ɼ�����װ���ԡ�����
	}
