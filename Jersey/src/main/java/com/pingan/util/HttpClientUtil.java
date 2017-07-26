package com.pingan.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.core.HttpHeaders;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * httpclient工具类
 * @author ZhongLin728
 *
 */
public class HttpClientUtil {
	public   Log log = LogFactory.getLog(this.getClass());
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
	}

    
	private static PoolingHttpClientConnectionManager cm;
	private static String EMPTY_STR = "";
	private static String UTF_8 = "UTF-8";
	private static String token = "";
	private static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.22 Safari/537.36 SE 2.X MetaSr 1.0";
	/**
	 * 通过连接池获取HttpClient
	 * 
	 * @return
	 */
	private static CloseableHttpClient getHttpClient() {
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(50);// 整个连接池最大连接数
		cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
		return HttpClients.custom().setConnectionManager(cm).build();
	}
	
	/**
	 *  
	 * @param url
	 * @return
	 */
	public static String httpGetRequest(String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, token);
		httpGet.setHeader(HttpHeaders.USER_AGENT,userAgent);
		return getResult(httpGet);
	}
	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {
		//HttpHost proxy = new HttpHost("192.168.7.1",80); 
		//RequestConfig  config = RequestConfig.custom().setProxy(proxy).build();
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);
		HttpGet httpGet = new HttpGet(ub.build());
		//httpGet.setConfig(config);
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, token);
		httpGet.setHeader(HttpHeaders.USER_AGENT,userAgent);
		return getResult(httpGet);
	}
	/**
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
			throws URISyntaxException {
		URIBuilder ub = new URIBuilder();
		ub.setPath(url);

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		ub.setParameters(pairs);

		HttpGet httpGet = new HttpGet(ub.build());
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, token);
		httpGet.setHeader(HttpHeaders.USER_AGENT,userAgent);
		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
		}
		return getResult(httpGet);
	}
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String httpPostRequest(String url) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpHeaders.AUTHORIZATION,token);
		httpPost.setHeader(HttpHeaders.USER_AGENT,userAgent);
		return getResult(httpPost);
	}
	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {
		System.out.println("请求头" + params);
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpHeaders.AUTHORIZATION,token);
		httpPost.setHeader(HttpHeaders.USER_AGENT,userAgent);
		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
		return getResult(httpPost);
	}
	/**
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(HttpHeaders.AUTHORIZATION,token);
		httpPost.setHeader(HttpHeaders.USER_AGENT,userAgent);
		for (Map.Entry<String, Object> param : headers.entrySet()) {
			httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
		}

		ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

		return getResult(httpPost);
	}

	/**
	 * post   json请求！
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String httpPostJSON(String url,JSONObject jsonParam) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION,token);
        httpPost.setHeader(HttpHeaders.USER_AGENT,userAgent);
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
        entity.setContentEncoding("UTF-8");    
        entity.setContentType("application/json");    
        httpPost.setEntity(entity);
        return getResult(httpPost);
    }
	private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
		}

		return pairs;
	}
	
	/**
	 *  局域网 设置代理 ip服务器！
	 */
	private static RequestConfig proxy(){
		HttpHost proxy = new HttpHost("192.168.7.1", 8080);  
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        return config;
	}
	/**
	 * 处理Http请求
	 * 
	 * @param request
	 * @return
	 */
	private static String getResult(HttpRequestBase request) {
		//CloseableHttpClient httpClient = HttpClients.createDefault();
		//request.setConfig(proxy());//局域网设置代理IP访问
		CloseableHttpClient httpClient = getHttpClient();
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			// response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity);
				response.close();
				// httpClient.close();
				return result;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return EMPTY_STR;
	}
	/**
	 * 格式化JSON 输出 
	 * @param jsonStr
	 * @return
	 */
	public static String format(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for(int i=0;i<jsonStr.length();i++){
            char c = jsonStr.charAt(i);
            if(level>0&&'\n'==jsonForMatStr.charAt(jsonForMatStr.length()-1)){
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c+"\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c+"\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();

    }

    private static String getLevelStr(int level){
        StringBuffer levelStr = new StringBuffer();
        for(int levelI = 0;levelI<level ; levelI++){
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
	
	/**
	 * 登录PACT 返回 token
	 */
	public static String  login(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("passwd","aaaaa888");
		jsonObject.put("userName", "YAOXINYU001");
		String httpPostJSON = "";
		try {
				httpPostJSON = httpPostJSON("http://192.168.5.25:8080/api/v1/authentication", jsonObject);
				String token = (String) JSONObject.fromObject(httpPostJSON).getJSONObject("data").get("token");
				System.out.println("static token"+token);
				return token;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 设置token
	 */
	static{
		//token = login();
	}

	
	public static String zhonglin(int i) throws Exception  {
		//findFile("com");
			Map<String, Object> m = new HashMap<>();
			m.put("peopleOrApplyVo.name","康拓扑"+UUID.randomUUID().toString()+i);
			m.put("peopleOrApplyVo.tel","12358745623");
			m.put("peopleOrApplyVo.mail","121211212121@qq.com");
			m.put("peopleOrApplyVo.jobExp","南方电网");
			m.put("peopleOrApplyVo.projectExp","景田地铁站");
			m.put("peopleOrApplyVo.education","小学毕业");
			m.put("peopleOrApplyVo.speciality","运维/实施");
			m.put("peopleOrApplyVo.jobAge","康拓扑科技有限公司");
			m.put("peopleOrApplyVo.willSite","全国");
			m.put("openId","12323233");
			m.put("jumpFlag",-10);
			m.put("jobId",null);
			String res = httpPostRequest("http://zhaopin.bill-jc.com/BJCWechatS/noJobAction!checkNoJob", m);
			return res;
	}
	
}
