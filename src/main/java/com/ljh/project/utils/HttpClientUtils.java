package com.ljh.project.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Slf4j
public class HttpClientUtils {

	@Getter
	private static String pngPath;

//	@Value("${web.png-path}")
//	public void setPngPath(String pngPathCFG){
// 		pngPath = pngPathCFG;
//	}

	private static RequestConfig requestConfig = null;
	static{
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
    }

	/***
	 * get请求
	 * @param url
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		JSONObject jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		request.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = client.execute(request);
			//请求成功响应
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String str = EntityUtils.toString(entity, "utf-8");
				jsonResult = JSONObject.parseObject(str);
			}else {
				log.error("get请求提交失败:" + url);
			}
		} catch (Exception e) {
			log.error("get请求提交失败:" + url,e);
		}finally {
			request.releaseConnection();
		}
		return jsonResult;
	}
	/**
	 * post请求 传入字符串参数
	 * @param url
	 * @param paramStr
	 * @return
	 */
	public static JSONObject httpPost(String url,String paramStr) {
		JSONObject jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost(url);
		request.setConfig(requestConfig);
		try {
			if(paramStr!=null) {
				StringEntity entity = new StringEntity(paramStr, "utf-8");
				entity.setContentEncoding("utf-8");
				entity.setContentType("application/x-www-form-urlencoded");
				request.setEntity(entity);
			}
			CloseableHttpResponse response = client.execute(request);
			//请求成功
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				String str = EntityUtils.toString(request.getEntity(), "utf-8");
				jsonResult = JSONObject.parseObject(str);
			}
		} catch (Exception e) {
			log.error("post请求失败"+url, e);
		}finally {
			request.releaseConnection();
		}
		return jsonResult;
	}
	/***
	 * post请求传json参数
	 * @param url
	 * @param jsonObjectParsm
	 * @return
	 */
	public static JSONObject httpPost(String url,JSONObject jsonObjectParsm) {
		JSONObject jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost(url);
		request.setConfig(requestConfig);
		try {
			if(jsonObjectParsm!=null) {
				StringEntity entity = new StringEntity(jsonObjectParsm.toString(), "utf-8");
				entity.setContentEncoding("utf-8");
				entity.setContentType("application/json");
				request.setEntity(entity);
			}
			CloseableHttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				String str = EntityUtils.toString(response.getEntity(), "utf-8");
				jsonResult = JSONObject.parseObject(str);
			}
		} catch (Exception e) {
			log.error("post请求失败"+url, e);
		}finally {
			request.releaseConnection();
		}
		return jsonResult;
	}

	public static String httpPostCode(String url,JSONObject jsonObjectParsm) {
		JSONObject jsonResult = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost(url);
		request.setConfig(requestConfig);
		OutputStream outputStream = null;
		InputStream inputStream=null;
		String imagePath="";
		try {
			if(jsonObjectParsm!=null) {
				StringEntity entity = new StringEntity(jsonObjectParsm.toString(), "utf-8");
				entity.setContentEncoding("utf-8");
				entity.setContentType("application/json");
				entity.setContentType("image/png");
				request.setEntity(entity);
			}
			CloseableHttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				inputStream = response.getEntity().getContent();
				imagePath=System.currentTimeMillis()+"code.jpg";
				File file = new File(pngPath + imagePath);
				log.info("img  " + pngPath + imagePath);
				//File file = new File("d:/resource/"+imagePath);
				  if (!file.exists()){
				   file.createNewFile();
				  }
				outputStream = new FileOutputStream(file);
				int len = 0;
				byte[] buf = new byte[1024];
				while ((len = inputStream.read(buf, 0, 1024)) != -1) {
					outputStream.write(buf, 0, len);
				}
				outputStream.flush();
			}
		} catch (Exception e) {
			log.error("post请求失败"+url, e);
		}finally {
			request.releaseConnection();
			if(inputStream != null){
				   try {
				    inputStream.close();
				   } catch (IOException e) {
				    e.printStackTrace();
				   }
				  }
				  if(outputStream != null){
				   try {
				    outputStream.close();
				   } catch (IOException e) {
				    e.printStackTrace();
				   }
				  }
		}
		return imagePath;
	}


	/**
	 * post请求携带文件
	 * @param httpUrl
	 * @param file
	 * @return
	 */
	public static JSONObject httpPayload(String httpUrl, MultipartFile file){
		// 接口返回的结果
		JSONObject result = null;
		try{
			// 换行符
			final String newLine = "\r\n";
			//定义数据分隔线
			final String boundary= "========7d4a6d158c9";
			final String boundaryPrefix = "--";
			URL url = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");

			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1");
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			StringBuilder sb = new StringBuilder();
			sb.append(boundaryPrefix);
			sb.append(boundary);
			sb.append(newLine);
			// 文件参数,photo参数名可以随意修改
			sb.append("Content-Disposition: form-data;name=\"image\";filename=\"" + "https://api.weixin.qq.com" + "\"" + newLine);
			sb.append("Content-Type:application/octet-stream;charset=UTF-8");
			// 参数头设置完以后需要两个换行，然后才是参数内容
			sb.append(newLine);
			sb.append(newLine);
			// 将参数头的数据写入到输出流中
			out.write(sb.toString().getBytes());

			// 读取文件数据
			out.write(file.getBytes());
			// 最后添加换行
			out.write(newLine.getBytes());

			// 定义最后数据分隔线，即--加上BOUNDARY再加上--。
			byte[] end_data = (newLine + boundaryPrefix + boundary + boundaryPrefix + newLine).getBytes();
			// 写上结尾标识
			out.write(end_data);
			out.flush();
			out.close();
			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((result = JSON.parseObject(reader.readLine())) != null) {
				return result;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			log.error("http携带文件post请求error",e.getMessage());
		}
		return result;
	}


}
