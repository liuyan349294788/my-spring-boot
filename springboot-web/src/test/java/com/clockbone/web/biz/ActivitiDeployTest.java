package com.clockbone.web.biz;

import com.clockbone.web.AbstratApplicationBaseBootTest;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ActivitiDeployTest extends AbstratApplicationBaseBootTest {

    @Autowired
    private RepositoryService repositoryService;

    //部署流程定义
    @Test
    public void deployementProcessDefinition(){
        Deployment deployment = repositoryService//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .name("test")//声明流程的名称
                .addClasspathResource("dig/processes/one-task-process.bpmn")//加载资源文件，一次只能加载一个文件
                .addClasspathResource("dig/processes/one-task-process.png")//
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }
}
