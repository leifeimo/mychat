package com.prcmind.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.alibaba.fastjson.JSON;
public class RestUtil {
    public static final MediaType T_JSON = MediaType.parse("application/json; charset=utf-8");

    public static String getFromURL(String url, int readTime) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).readTimeout(readTime, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postJSON(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).build();
        RequestBody body = RequestBody.create(T_JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public static void main(String[] args) throws IOException {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("scoreNo", "002");
		param.put("access_token", "01c47875-d39e-421e-b2b2-b0e65cb53f13");
		String json = JSON.toJSONString(param);
		String result = RestUtil.postJSON("https://api.prcmind.cn:1600/enterpriseMchat/getMchatQuestionnaireResponse", json);
		System.out.println(result);
	}
}
