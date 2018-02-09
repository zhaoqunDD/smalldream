package util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * java发送http请求
 *2018年2月8日-下午9:08:28
 * @author 赵群
 */
public class JavaToHttp {

	/**
	 * java发送http请求
	 * @param url
	 */
	public static void sendHttp(String url){
		DefaultHttpClient httpclient = new DefaultHttpClient();

	    HttpGet httpget = new HttpGet(url);

	    HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
