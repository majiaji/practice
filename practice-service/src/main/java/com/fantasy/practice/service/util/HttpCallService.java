package com.fantasy.practice.service.util;

import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by jiaji on 2017/6/20.
 */
public class HttpCallService {

    private HttpClient httpClient;

    private static void buildHeader(HttpUriRequest request, Map<String, String> headerParam) {
        if (headerParam == null) return;
        for (Map.Entry<String, String> entry : headerParam.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
    }

    public String doCall(String url, CallParam callParam, String method) {
        String result;
        try {
            if (method.equalsIgnoreCase("get")) {
                result = doGet(url, callParam);
            } else {
                result = doPost(url, callParam);
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    @PostConstruct
    public void init() {
        //连接池配置
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        //最大并发连接数
        connectionManager.setMaxTotal(1000);
        //每个url支持的并发连接数
        connectionManager.setDefaultMaxPerRoute(1000);
        //超时
        RequestConfig requestConfig = RequestConfig.custom()
                //从池里取链接
                .setConnectionRequestTimeout(1000)
                //进行连接
                .setConnectTimeout(5000)
                //socket读
                .setSocketTimeout(5000).build();
        //代理配置
//        HttpHost httpHost = new HttpHost(capApiConfig.httpProxyHost, capApiConfig.httpProxyPort);
        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
//                .setProxy(httpHost)
                .build();
    }

    private String doGet(String url, CallParam callParam) throws IOException {
        if (!url.endsWith("?")) {
            url += "?";
        }
        if (callParam != null && callParam.getBodyParam() != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : callParam.getBodyParam().entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            url = url + sb.toString();
//            url = url + URLEncoder.encode(sb.toString(), "utf-8");
        }
        HttpGet httpGet = new HttpGet(url);
        if (callParam != null) {
            buildHeader(httpGet, callParam.getHeaderParam());
        }
        HttpResponse httpResponse = httpClient.execute(httpGet);
        return EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf-8"));
    }

    private String doPost(String url, CallParam callParam) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (callParam != null) {
            buildHeader(httpPost, callParam.getHeaderParam());
            if (callParam.getBodyParam() != null) {
                List<NameValuePair> nameValuePairList = Lists.newLinkedList();
                for (Map.Entry<String, String> entry : callParam.getBodyParam().entrySet()) {
                    NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                    nameValuePairList.add(nameValuePair);
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, Charset.forName("utf-8")));
            }
            if (callParam.getStringEntity() != null) {
                httpPost.setEntity(callParam.getStringEntity());
                httpPost.addHeader("Content-Type", "text/xml");
            }
        }
        HttpResponse httpResponse = httpClient.execute(httpPost);
        return EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf-8"));
    }


    public static class CallParam {
        StringEntity stringEntity;
        private Map<String, String> headerParam;
        private Map<String, String> bodyParam;

        public StringEntity getStringEntity() {
            return stringEntity;
        }

        public void setStringEntity(StringEntity stringEntity) {
            this.stringEntity = stringEntity;
        }

        public Map<String, String> getHeaderParam() {
            return headerParam;
        }

        public void setHeaderParam(Map<String, String> headerParam) {
            this.headerParam = headerParam;
        }

        public Map<String, String> getBodyParam() {
            return bodyParam;
        }

        public void setBodyParam(Map<String, String> bodyParam) {
            this.bodyParam = bodyParam;
        }

        @Override
        public String toString() {
            return "CallParam{" +
                    "stringEntity=" + stringEntity +
                    ", headerParam=" + headerParam +
                    ", bodyParam=" + bodyParam +
                    '}';
        }
    }

}