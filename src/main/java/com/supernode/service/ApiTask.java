package com.supernode.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

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
    }
}
