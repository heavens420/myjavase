package com.zlx.flowable;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * flowable 使用教程 01
 */
public class Demo1 {
    public static void main(String[] args) {
//        deployProcess();
//        queryProcess();
//        runProcess();
        queryTaskByUser();

    }

    /**
     * 创建数据库及流程引擎
     */
    public static ProcessEngineConfiguration crateTables() {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://192.168.222.96:3312/flowable-learn?serverTimezone=UTC&nullCatalogMeansCurrent=true")
                .setJdbcUsername("root")
                .setJdbcPassword("ROOT#")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();
        return cfg;
    }

    /**
     * 部署流程
     */
    public static RepositoryService deployProcess() {
        // 配置数据库相关信息 获取 ProcessEngineConfiguration
        // 获取流程引擎对象
        ProcessEngine processEngine = crateTables().buildProcessEngine();
        // 部署流程 获取RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()// 创建Deployment对象
                .addClasspathResource("holiday-request.bpmn20.xml") // 添加流程部署文件
                .name("请求流程") // 设置部署流程的名称
                .deploy(); // 执行部署操作
        System.out.println("deployment.getId() = " + deployment.getId());
        System.out.println("deployment.getName() = " + deployment.getName());
        return repositoryService;
    }

    /**
     * 查询流程定义
     */
    public static void queryProcess() {
        // 部署流程 获取RepositoryService对象
        RepositoryService repositoryService = deployProcess();
        // 获取流程定义对象
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId("2501")
                .singleResult();
        System.out.println("processDefinition.getId() = " + processDefinition.getId());
        System.out.println("processDefinition.getName() = " + processDefinition.getName());
        System.out.println("processDefinition.getDeploymentId() = " + processDefinition.getDeploymentId());
        System.out.println("processDefinition.getDescription() = " + processDefinition.getDescription());
    }

    /**
     * 启动流程
     */
    public static void runProcess() {
        // 获取流程引擎对象
        ProcessEngine processEngine = crateTables().buildProcessEngine();
        // 启动流程实例通过 RuntimeService 对象
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 构建流程变量
        Map<String,Object> variables = new HashMap<>();
        variables.put("employee","张三") ;// 谁申请请假
        variables.put("nrOfHolidays",3); // 请几天假
        variables.put("description","工作累了，想出去玩玩"); // 请假的原因
        // 启动流程实例，第一个参数是流程定义的id
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("holidayRequest", variables);// 启动流程实例
        // 输出相关的流程实例信息
        System.out.println("流程定义的ID：" + processInstance.getProcessDefinitionId());
        System.out.println("流程实例的ID：" + processInstance.getId());
        System.out.println("当前活动的ID：" + processInstance.getActivityId());
    }

    /**
     * 查询lisi的待处理流程
     */
    public static void queryTaskByUser() {
        // 获取流程引擎对象
        ProcessEngine processEngine = crateTables().buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("holidayRequest")
                .taskAssignee("lisi")
                .list();
        for (Task task : list) {
            System.out.println("task.getProcessDefinitionId() = " + task.getProcessDefinitionId());
            System.out.println("task.getId() = " + task.getId());
            System.out.println("task.getAssignee() = " + task.getAssignee());
            System.out.println("task.getName() = " + task.getName());
        }
    }
}
