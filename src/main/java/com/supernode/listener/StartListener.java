package com.supernode.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @Auther: HuangRui
 * @Date: 2021/2/7 10:39
 * @Description:
 */
public class StartListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("StartEvent running");
    }
}
