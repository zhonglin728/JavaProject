package com.pingan.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupHttp {
	
	public static void main(String[] args) throws IOException {
		
		
	}
	
	
	public static  void  proxy(){
		System.setProperty("http.maxRedirects", "50");
		System.getProperties().setProperty("proxySet", "true");
		// 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
		String ip = "192.168.7.1";
		System.getProperties().setProperty("http.proxyHost", ip);
		System.getProperties().setProperty("http.proxyPort", "8080");
	} 
	
	public static void login() throws IOException{
		for (int i = 4441; i < 10000; i++) {
			proxy();
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", "草泥马傻狗"+i);
			map.put("pass", "我的QQ717543275欢迎骚扰！");
			map.put("pifu", "1");
			map.put("Submit", "登录");
			Document document = Jsoup.connect("http://qq.lolcnq.top/?ok=26769")
					.data(map)
					.header(HttpHeaders.ACCEPT, "*/*")
					.header(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate")
					.header(HttpHeaders.ACCEPT_LANGUAGE,"zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
					.header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
					.header(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
					.timeout(5000)
					.post();
			System.out.println("------------------"+i);
			System.out.println(document.body());
		}
	}

}
