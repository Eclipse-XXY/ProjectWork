package com.xuxueya.demo;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestActiviti {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring-*.xml");

	
	//测试启动流程实例
	@Test
	public void test4() {
		ProcessEngine processEngine = (ProcessEngine)ioc.getBean("processEngine");
		System.out.println("processEngine="+processEngine);
		
		//存储服务对象,可以进行流程部署等操作
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		TaskService taskService = processEngine.getTaskService();
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		List<Task> list1 = taskQuery.taskAssignee("zhangsan").list();
		List<Task> list2 = taskQuery.taskAssignee("lisi").list();
		System.out.println("zhangsan任务数量="+list1.size());
		for (Task task : list1) {
			System.out.println("zhangsan任务信息="+task.getName());
			taskService.complete(task.getId());		//完成任务	
		}
		System.out.println("---------------------------------");
		System.out.println("lisi任务数量="+list2.size());
		for (Task task : list2) {
			System.out.println("lisi任务信息="+task.getName());
		}
		
		System.out.println("==================================");
		
		
		taskQuery = taskService.createTaskQuery();
		
		list1 = taskQuery.taskAssignee("zhangsan").list();
		list2 = taskQuery.taskAssignee("lisi").list();
		System.out.println("zhangsan任务数量="+list1.size());
		for (Task task : list1) {
			System.out.println("zhangsan任务信息="+task.getName());	
		}
		System.out.println("---------------------------------");
		System.out.println("lisi任务数量="+list2.size());
		for (Task task : list2) {
			System.out.println("lisi任务信息="+task.getName());
		}
		
		
		System.out.println("==================================");
		
		
		taskQuery = taskService.createTaskQuery();
		
		list1 = taskQuery.taskAssignee("zhangsan").list();
		list2 = taskQuery.taskAssignee("lisi").list();
		System.out.println("zhangsan任务数量="+list1.size());
		for (Task task : list1) {
			System.out.println("zhangsan任务信息="+task.getName());	
		}
		System.out.println("---------------------------------");
		System.out.println("lisi任务数量="+list2.size());
		for (Task task : list2) {
			taskService.complete(task.getId());
			System.out.println("lisi任务信息="+task.getName());
		}
	}
	
	
	//测试启动流程实例
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
	
	
	//测试查询部署流程
	@Test
	public void test2() {
		ProcessEngine processEngine = (ProcessEngine)ioc.getBean("processEngine");
		System.out.println("processEngine="+processEngine);
		//存储服务对象,可以进行流程部署等操作
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		List<ProcessDefinition> list = processDefinitionQuery.list();
		for (ProcessDefinition processDefinition : list) {
			System.out.println(processDefinition.getName());
			System.out.println(processDefinition.getKey());
			System.out.println(processDefinition.getVersion());
		}
		
		long count = processDefinitionQuery.count();
		System.out.println("count="+count);
		
		/*ProcessDefinition singleResult = processDefinitionQuery.latestVersion().singleResult();
		System.out.println(singleResult.getName());
		System.out.println(singleResult.getKey());
		System.out.println(singleResult.getVersion());*/
		System.out.println("--------------***************--------------------");
	
		ProcessDefinitionQuery query = processDefinitionQuery.orderByProcessDefinitionVersion().desc();
		List<ProcessDefinition> list2 = query.list();
		for (ProcessDefinition processDefinition : list2) {
			System.out.println(processDefinition.getName());
			System.out.println(processDefinition.getKey());
			System.out.println(processDefinition.getVersion());
		}
		
		System.out.println("--------------%%%%%%%%%%%%%--------------------");
		ProcessDefinition singleResult2 = processDefinitionQuery.processDefinitionKey("myProcess").latestVersion().singleResult();
		System.out.println(singleResult2.getName());
		System.out.println(singleResult2.getKey());
		System.out.println(singleResult2.getVersion());
		
	}
	//测试部署
	@Test
	public void test1() {
		ProcessEngine processEngine = (ProcessEngine)ioc.getBean("processEngine");
		System.out.println("processEngine="+processEngine);
		//存储服务对象,可以进行流程部署等操作
		RepositoryService repositoryService = processEngine.getRepositoryService();
		
		//流程部署.
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("变量.bpmn").deploy();
	}
	@Test
	public void testCreateTable23() {
		ProcessEngine processEngine = (ProcessEngine)ioc.getBean("processEngine");
		System.out.println("processEngine="+processEngine);
		//processEngine=org.activiti.engine.impl.ProcessEngineImpl@19074c78
		
		HistoryService historyService = processEngine.getHistoryService();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();
		TaskService taskService = processEngine.getTaskService();
		
		System.out.println("historyService="+historyService);
		System.out.println("repositoryService="+repositoryService);
		System.out.println("runtimeService="+runtimeService);
		System.out.println("taskService="+taskService);
		
		/*historyService=org.activiti.engine.impl.HistoryServiceImpl@3112fe3e
		repositoryService=org.activiti.engine.impl.RepositoryServiceImpl@55f1b19d
		runtimeService=org.activiti.engine.impl.RuntimeServiceImpl@6ec3e67f
		taskService=org.activiti.engine.impl.TaskServiceImpl@7f51d4a8*/
		
		String name = processEngine.getName();
		System.out.println("name="+name); //default
	}
/**
 * 查看历史记录
 * @param
 * @author 徐雪娅
 */
	@Test
	public void testCheckHistory() {
	ProcessEngine processEngine = (ProcessEngine) ioc.getBean("processEngine");
		HistoryService historyService = processEngine.getHistoryService();
HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
	HistoricProcessInstance historicProcessInstance = query.processInstanceId("102").finished().singleResult();
	System.out.println(historicProcessInstance);
	}
	/**
	 * 测试以组的形式分配任务
	 * @param
	 * @author 徐雪娅
	 */
	@Test
	public void testAssignTaskByTeam() {
		ProcessEngine processEngine = (ProcessEngine) ioc.getBean("processEngine");
	TaskService taskService = processEngine.getTaskService();
	//得到任务组的所有任务
	 List<Task> list = taskService.createTaskQuery().taskCandidateGroup("TL").list();
	 for (Task task : list) {
	System.out.println("这个组的全部任务"+task.getName());
}
List<Task> list2 = taskService.createTaskQuery().taskAssignee("tom").list();
	System.out.println("tom没有领取任务前的任务数"+list2.size());
	for (Task task : list) {
		taskService.claim(task.getId(), "tom");
	}
 list2 = taskService.createTaskQuery().taskAssignee("tom").list();
	System.out.println("tom领取任务后"+list2.size());
	}

}
