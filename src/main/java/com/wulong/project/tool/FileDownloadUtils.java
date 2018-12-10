package com.wulong.project.tool;


import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:wulong
 * @Date:2018/11/7 9:46
 * @mail:491925129@qq.com
 */
public class FileDownloadUtils {

	/**
	 * 程序中访问http数据接口
	 */
	public static String getURLContent(String ip) {
		/** 网络的url地址 */
		URL url = null;
		/** http连接 */
		HttpURLConnection httpConn = null;
		/**//** 输入流 */
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
			in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String str = null;
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception ex) {
			Map<String,Object> result = new HashMap<>();
			result.put("code","1");
			return JSON.toJSONString(result);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
			}
		}
		String result = sb.toString();
		return result;
	}
}
