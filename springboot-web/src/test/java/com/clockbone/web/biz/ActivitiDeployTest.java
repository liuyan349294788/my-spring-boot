package com.clockbone.web.biz;

import com.clockbone.web.AbstratApplicationBaseBootTest;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ActivitiDeployTest extends AbstratApplicationBaseBootTest {

    @Autowired
    private RepositoryService repositoryService;

    //deploy custom
    @Test
    public void deployementProcessDefinition(){
        Deployment deployment = repositoryService
                .createDeployment()//create deploy object
                .name("test")// define deployment object name
                .addClasspathResource("dig/processes/one-task-process.bpmn")//load source bpmn file
                .addClasspathResource("dig/processes/one-task-process.png")//
                .deploy();//fish deploy
        System.out.println("deploy ID："+deployment.getId());//1
        System.out.println("deploy 时间："+deployment.getDeploymentTime());
    }

    @Test
    public void deploymentFinancialTest(){
        Deployment deployment = repositoryService
                .createDeployment()//create deploy object
                .name("test")// define deployment object name
                .addClasspathResource("dig/processes/FinancialReportProcess.bpmn20.xml")//load source bpmn file
                .addClasspathResource("dig/processes/FinancialReportProcess.bpmn20.png")//
                .deploy();//fish deploy
        System.out.println("deploy ID："+deployment.getId());//1
        System.out.println("deploy 时间："+deployment.getDeploymentTime());
    }
}
