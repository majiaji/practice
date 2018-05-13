package com.fantasy.practice.web;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("httpCallService")
public class HttpCallService {
    static Logger logger = LoggerFactory.getLogger(HttpCallService.class);

    public static void buildHeader(HttpUriRequest request, Map<String, String> headerParam) {
        if (headerParam == null) {
            return;
        }
        for (Map.Entry<String, String> entry : headerParam.entrySet()) {
            request.setHeader(entry.getKey(), entry.getValue());
        }
    }

    public static HttpEntity buildParameter(Map<String, String> paramters) {
        HttpEntity httpEntity = null;
        if (paramters != null && !paramters.isEmpty()) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>(paramters.size());
            for (Map.Entry<String, String> entry : paramters.entrySet()) {
                NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
                nameValuePairList.add(nameValuePair);
            }
            httpEntity = new UrlEncodedFormEntity(nameValuePairList, Charset.forName("utf-8"));
        }
        return httpEntity;
    }


    public static String doCall(String url, CallParam callParam, String method, String desc) {
        String traceId = "123";
        long startTime = System.currentTimeMillis();
        logger.info("[http调用请求] Desc:{} url:{} callParam:{}  traceId:{}", desc, url, callParam, traceId);
        String result = null;
        try {
            if (method.equalsIgnoreCase("get")) {
                result = doGet(url, callParam);
            } else {
                result = doPost(url, callParam);
            }
            logger.info("[http调用结果] {} traceId:{}", result, traceId);
            return result;
        } catch (Exception e) {

        } finally {
            logger.info("Http Request Costs:" + (System.currentTimeMillis() - startTime) + "ms");
        }
        return "null";
    }

    private static String doGet(String url, CallParam callParam) throws Exception {
        // 连接池配置
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 最大并发连接数
        connectionManager.setMaxTotal(1000);
        // 每个url支持的并发连接数
        connectionManager.setDefaultMaxPerRoute(1000);
        // 超时
        RequestConfig requestConfig = RequestConfig.custom()
                // 从池里取链接
                .setConnectionRequestTimeout(1000)
                // 进行连接
                .setConnectTimeout(5000)
                // socket读
                .setSocketTimeout(5000).build();
        // 代理配置
        HttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager)
                .build();

        HttpEntity paramEntity = null;
        if (callParam != null) {
            paramEntity = buildParameter(callParam.getParams());
        }
        if (paramEntity != null) {
            url = url.contains("?") ? url + "&" + EntityUtils.toString(paramEntity)
                    : url + "?" + EntityUtils.toString(paramEntity);
        }
        logger.info("调用Url: {}", url);
        HttpGet httpGet = new HttpGet(url);
        if (callParam != null) {
            buildHeader(httpGet, callParam.getHeaders());
        }
        if (requestConfig != null) {
            httpGet.setConfig(requestConfig);
        }
        HttpResponse httpResponse = httpClient.execute(httpGet);
        handleResponse(httpResponse, url);
        return EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf-8"));
    }


    private static String doPost(String url, CallParam callParam) throws Exception {
        // 连接池配置
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 最大并发连接数
        connectionManager.setMaxTotal(1000);
        // 每个url支持的并发连接数
        connectionManager.setDefaultMaxPerRoute(1000);
        // 超时
        RequestConfig requestConfig = RequestConfig.custom()
                // 从池里取链接
                .setConnectionRequestTimeout(1000)
                // 进行连接
                .setConnectTimeout(5000)
                // socket读
                .setSocketTimeout(5000).build();
        // 代理配置
        HttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager)
                .build();

        HttpEntity paramEntity = null;
        HttpEntity body = null;
        if (callParam != null) {
            paramEntity = buildParameter(callParam.getParams());
            body = callParam.getBody();
        }
        if (paramEntity != null && body != null) {
            url = url.contains("?") ? url + "&" + EntityUtils.toString(paramEntity)
                    : url + "?" + EntityUtils.toString(paramEntity);
        }
        logger.info("调用Url: {}", url);
        HttpPost httpPost = new HttpPost(url);
        if (callParam != null) {
            buildHeader(httpPost, callParam.getHeaders());
        }
        if (body != null) {
            httpPost.setEntity(body);
        } else if (paramEntity != null) {
            httpPost.setEntity(paramEntity);
        }
        if (requestConfig != null) {
            httpPost.setConfig(requestConfig);
        }
        HttpResponse httpResponse = httpClient.execute(httpPost);
        handleResponse(httpResponse, url);
        return EntityUtils.toString(httpResponse.getEntity(), Charset.forName("utf-8"));
    }

    private static void handleResponse(HttpResponse httpResponse, String url) throws Exception {
        logger.info("http响应码为: " + httpResponse.getStatusLine().toString());
        if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            logger.warn("http响应异常, response line: {}, url: {}", httpResponse.getStatusLine(), url);
            throw new Exception("响应异常");
        }
    }

    public static void main(String[] args) {
        CallParam callParam = new CallParam();
        callParam.setBody(new StringEntity("{}", ContentType.APPLICATION_JSON));
        String resp = doCall("https://f1-capup.test.webank.com:443/api/s/up/uploadImage", callParam, "post", "测试");
        System.out.println(resp);

    }

    public static class CallParam {
        private HttpEntity body;
        private Map<String, String> headers;
        private Map<String, String> params;

        @Override
        public String toString() {
            return "CallParam{" + "body=" + body + ", headers=" + headers + ", params="
                    + params + '}';
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }

        public Map<String, String> getParams() {
            return params;
        }

        public void setParams(Map<String, String> params) {
            this.params = params;
        }

        public HttpEntity getBody() {
            return body;
        }

        public void setBody(HttpEntity body) {
            this.body = body;
        }

    }

}
