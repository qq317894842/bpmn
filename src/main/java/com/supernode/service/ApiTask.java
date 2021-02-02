package com.supernode.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: HuangRui
 * @Date: 2021/1/26 15:36
 * @Description:
 */
@Service
public class ApiTask implements JavaDelegate {

    private Expression url;

    @Override
    public void execute(DelegateExecution delegateExecution) {
       String urlStr = url.getValue(delegateExecution).toString();
       //调用url
       System.out.println("api:"+urlStr);
       doGet(urlStr);


    }

    private void doGet(String url){
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                String content = EntityUtils.toString(responseEntity);
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容长度为:" + content.length());
                System.out.println("响应内容为:" + content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
