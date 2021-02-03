package com.supernode.utils;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: HuangRui
 * @Date: 2021/2/3 16:15
 * @Description:
 */
public class BPMNUtils {
    public static void main(String[] args) {
        //实例化BpmnModel对象
        BpmnModel bpmnModel = new BpmnModel();
//开始节点的属性
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start1shareniu");
        startEvent.setName("start1shareniu");
//普通的UserTask节点
        UserTask userTask = new UserTask();
        userTask.setId("userTask1shareniu");
        userTask.setName("userTask1shareniu");
//结束节点属性
        EndEvent endEvent = new EndEvent();
        endEvent.setId("endEventshareniu");
        endEvent.setName("endEventshareniu");
//连线信息
        List<SequenceFlow> sequenceFlows = new ArrayList<SequenceFlow>();
        List<SequenceFlow> toEnd = new ArrayList<SequenceFlow>();
        SequenceFlow s1 = new SequenceFlow();
        s1.setId("starttouserTask");
        s1.setName("starttouserTask");
        s1.setSourceRef("start1shareniu");
        s1.setTargetRef("userTask1shareniu");
        sequenceFlows.add(s1);
        SequenceFlow s2 = new SequenceFlow();
        s2.setId("userTasktoend");
        s2.setName("userTasktoend");
        s2.setSourceRef("userTask1shareniu");
        s2.setTargetRef("endEventshareniu");
        toEnd.add(s2);
        startEvent.setOutgoingFlows(sequenceFlows);
        userTask.setOutgoingFlows(toEnd);
        userTask.setIncomingFlows(sequenceFlows);
        endEvent.setIncomingFlows(toEnd);
//Process对象
        Process process = new Process();
        process.setId("process1");
        process.addFlowElement(startEvent);
        process.addFlowElement(s1);
        process.addFlowElement(userTask);
        process.addFlowElement(s2);
        process.addFlowElement(endEvent);
        bpmnModel.addProcess(process);


        //bpmnModel 转换为标准的bpmn xml文件
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
        String bytes = new String(convertToXML);
        System.out.println(bytes);
        try {
            File file = new File("E:\\IdeaProjects\\bpmn\\src\\main\\resources\\processes\\test.bpmn");
            System.out.println(file.getPath());
            BPMNUtils.saveFile(file.getPath(),convertToXML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 将字节流转换成文件
     *
     * @param filename
     * @param data
     * @throws Exception
     */
    public static void saveFile(String filename, byte[] data) throws Exception {
        if (data != null) {
            File file = new File(filename);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data, 0, data.length);
            fos.flush();
            fos.close();
        }
    }
}

