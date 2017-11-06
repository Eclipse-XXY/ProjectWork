package com.xuxueya.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring-*.xml");
	/**
	 * 测试启动流程实例
	 * @param
	 * @author 徐雪娅
	 */
	@Test
	public void test3() {
		ProcessEngine processEngine = (ProcessEngine)ioc.getBean("processEngine");
		System.out.println("processEngine="+processEngine);
		
		//存储服务对象,可以进行流程部署等操作
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		ProcessDefinitionQuery definitionQuery = processDefinitionQuery.processDefinitionKey("myProcess");
		ProcessDefinition singleResult = definitionQuery.latestVersion().singleResult();
		ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionKey("myProcess").latestVersion().singleResult();
	System.out.println(singleResult);
		System.out.println(processDefinition.getName());
		System.out.println(processDefinition.getKey());
		System.out.println(processDefinition.getVersion());
		
		//启动流程实例
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
		System.out.println("processInstance="+processInstance);
		
	}
	/**
	 * 测试建表和部署
	 * @param
	 * @author 徐雪娅
	 */
	@Test
	public void test1() {
		ProcessEngine processEngine = (ProcessEngine)ioc.getBean("processEngine");
		System.out.println("processEngine="+processEngine);
		//存储服务对象,可以进行流程部署等操作
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		//流程部署.
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("MyProcess7.bpmn").deploy();
	}
	/**
	 * 测试变量的使用方法
	 * @param
	 * @author 徐雪娅
	 */
	@Test
	public void testVariables() {
		ProcessEngine processEngine = (ProcessEngine) ioc.getBean("processEngine");
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess").latestVersion().singleResult();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	Map<String, Object> variables=new HashMap<String, Object>();
	variables.put("TL", "李媛");
	variables.put("PM", "徐雪娅");
    runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
	}
	/**
	 * 测试以组的形式分配任务以及完成的过程
	 * @param
	 * @author 徐雪娅
	 */
	@Test
	public void testAssignTaskByTeam() {
		ProcessEngine processEngine = (ProcessEngine) ioc.getBean("processEngine");
	TaskService taskService = processEngine.getTaskService();


List<Task> list2 = taskService.createTaskQuery().taskAssignee("李媛").list();
	System.out.println("李媛领取任务前的任务数"+list2.size());
	for (Task task : list2) {
		taskService.claim(task.getId(), "李媛");
	}
	taskService.complete("206");
	}
/**
 * 测试排他网关的简单案例
 * @param
 * @author 徐雪娅
 */
	@Test
	public void testPaiTa() {
		ProcessEngine processEngine = (ProcessEngine) ioc.getBean("processEngine");
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess").latestVersion().singleResult();
	RuntimeService runtimeService = processEngine.getRuntimeService();
	Map<String, Object> variables=new HashMap<String, Object>();
//	variables.put("days", "3");
//	//variables.put("days", "5");
//    runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
List<Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee("张三").list();
for (Task task : list) {
	processEngine.getTaskService().complete(task.getId());
}

	}
}
