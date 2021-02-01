package com.supernode.service;

import org.activiti.engine.delegate.DelegateExecution;
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

    @Override
    public void execute(DelegateExecution delegateExecution) {
        Map<String, Object> variables = delegateExecution.getVariables();
        Object api = delegateExecution.getVariable("api");
        String taskId =  delegateExecution.getCurrentFlowElement().getId();
        System.out.println("api:"+api.toString()+",taskId:"+taskId);
    }
}
