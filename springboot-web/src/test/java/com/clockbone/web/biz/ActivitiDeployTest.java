package com.clockbone.web.biz;

import com.clockbone.web.AbstratApplicationBaseBootTest;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActivitiDeployTest extends AbstratApplicationBaseBootTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

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

    /**
     * deploy test
     * 部署完成后,会在部署表 和 流程表分别生成2条数据  re_act_deployment re_act_procdef
     */
    @Test
    public void deploymentFinancialTest(){
        Deployment deployment = repositoryService
                .createDeployment()//create deploy object
                .name("myprocess")// define deployment object name
                .addClasspathResource("dig/processes/myprocess.bpmn")//load source bpmn file
                .addClasspathResource("dig/processes/myprocess.png")//
                .deploy();//fish deploy
        System.out.println("deploy ID："+deployment.getId());//1
        System.out.println("deploy 时间："+deployment.getDeploymentTime());
        //insert inot act_re_deployment and act_re_procdef

    }

    /**
     * 查询流程 部署信息 select from act_re_deployment by key
     */
    @Test
    public void ProcessDefinitionQueryTest(){

        //查询部署信息
        DeploymentQuery query = repositoryService.createDeploymentQuery();
        // 根据流程定义的key来过滤
        query.processDefinitionKey("myprocess");

        // 分页查询（伪代码）
        //query.listPage("从哪开始查", "查几条");
        List<Deployment> list = query.list();
        for (Deployment pd : list) {
            System.out.println(pd.getId() + "    " + pd.getName() + "    " );
        }

        //查询流程信息
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        // 根据流程定义的key来过滤
        processDefinitionQuery.processDefinitionKey("myprocess");
        // 添加排序条件
        processDefinitionQuery.orderByProcessDefinitionVersion().desc();
        // 分页查询（伪代码）
        //query.listPage("从哪开始查", "查几条");
        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.list();
        for (ProcessDefinition pd : processDefinitionList) {
            System.out.println(pd.getId() + "    " + pd.getName() + "    " + pd.getVersion());
        }

    }


    /**
     * start process by id
     */
    @Test
    public void startMyProcessTest(){
        //根据key开始流程，这个是就是bpmn.xml定义的id,无论有几个版本都会用最新的那个版本
        //runtimeService.startProcessInstanceByKey("myProcess_1");
        //根据key开始流程，这个是就是bpmn.xml定义的id,无论有几个版本都会用最新的那个版本
        ProcessInstance processInstance =runtimeService.startProcessInstanceById("myProcess_1:1:12504");
        System.out.println("processInstance id"+processInstance.getId());
        //insert into act_ru_execution and act_ru_task
    }


    /**
     * 删除流程, 比如:开始了请假流程,后来又想取消请假流程,则需要删除这条
     */
    @Test
    public void processDefinitionDeleteTest(){
        String processInstanceId = "17501"; // 流程实例id
        String deleteReason = "不请假了"; // 删除原因，任君写
        runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
        //delete act_ru_execution and act_ru_task

    }

    /**
     * query task from act_ru_task
     */
    @Test
    public void queryTaskTest(){
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> tasks = taskQuery.list();
        //查询到task
        for (Task task : tasks) {
            System.out.println(task.getId() + "\t" + task.getName() + "\t" + task.getAssignee());
        }
        //根据Id, 完成task

    }

    public void completeTaskTest(){
        Task task = taskService.createTaskQuery().processInstanceId("15001").singleResult();
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("busStatus", true);
        taskService.complete(task.getId());
    }


    /*public void testHappyPath() {

        // Create test applicant
        Applicant applicant = new Applicant("John Doe", "john@activiti.org", "12344");
        applicantRepository.save(applicant);

        // Start process instance
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applicant", applicant);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("hireProcessWithJpa", variables);

        // First, the 'phone interview' should be active
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .taskCandidateGroup("dev-managers")
                .singleResult();
        Assert.assertEquals("Telephone interview", task.getName());

        // Completing the phone interview with success should trigger two new tasks
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("telephoneInterviewOutcome", true);
        taskService.complete(task.getId(), taskVariables);

        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .orderByTaskName().asc()
                .list();
        Assert.assertEquals(2, tasks.size());
        Assert.assertEquals("Financial negotiation", tasks.get(0).getName());
        Assert.assertEquals("Tech interview", tasks.get(1).getName());

        // Completing both should wrap up the subprocess, send out the 'welcome mail' and end the process instance
        taskVariables = new HashMap<String, Object>();
        taskVariables.put("techOk", true);
        taskService.complete(tasks.get(0).getId(), taskVariables);

        taskVariables = new HashMap<String, Object>();
        taskVariables.put("financialOk", true);
        taskService.complete(tasks.get(1).getId(), taskVariables);

        // Verify email
        Assert.assertEquals(1, wiser.getMessages().size());

        // Verify process completed
        Assert.assertEquals(1, historyService.createHistoricProcessInstanceQuery().finished().count());

    }*/






}
