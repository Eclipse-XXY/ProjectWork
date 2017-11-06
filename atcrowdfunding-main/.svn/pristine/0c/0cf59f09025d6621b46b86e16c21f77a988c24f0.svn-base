package com.xuxueya.demo;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestActivity {
	// Java当中是通过配置文件创建IOC容器
	// 但是在web应用程序当中是通过webApplicationcontext...这个类来创建IOC容器的
	ApplicationContext ioc = new ClassPathXmlApplicationContext(
			"spring/spring-*.xml");
	// 拿到流程引擎对象
	ProcessEngine processEngine = (ProcessEngine) ioc.getBean("processEngine");

	/**
	 * 拿到流程引擎对象创建23张表的操作
	 * 
	 * @param
	 * @author 徐雪娅
	 */
	@Test
	public void test() {

		ProcessEngine processEngine = (ProcessEngine) ioc
				.getBean("processEngine");
		System.out.println("processEngine=" + processEngine);
	}

	/**
	 * 通过RepositoryService得到部署流程引擎对象
	 * 
	 * @param
	 * @author 徐雪娅
	 */
	@Test
	public void deplomentTest() {
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		// 创建一个专门部署的对象
		DeploymentBuilder deployment = repositoryService.createDeployment();
		// 通过加载类路径文件反射创建加载对象的资源文件也叫配置文件
		DeploymentBuilder resource = deployment
				.addClasspathResource("MyProcessTest.bpmn");
		// 资源进行部署
		Deployment deploy = resource.deploy();

		/**
		 * 其实在写项目当中建议级联加载 ，就是一个接着调用一个效率高
		 * repositoryService.createDeployment().addClasspathResource
		 * ("MyProcess.bpmn").deploy();
		 * 
		 * @author 徐雪娅
		 */

	}

	/**
	 * 查询部署对象的信息 同一个流程部署的数量更具版本号进行确定 得到部署的最后版本
	 * 
	 * @author 徐雪娅
	 */
	@Test
	public void queryDeploment() {
		// 创建流程的（部署）定义查询
		ProcessDefinitionQuery query = processEngine.getRepositoryService()
				.createProcessDefinitionQuery();
		// 查询流程定义的数量
		long count = query.count();
		// 这种写法就是根据定义流程对象的id或者name的到一个流程对象然后拿到最新的版本在单例输出
		ProcessDefinitionQuery definitionKey = query
				.processDefinitionKey("myProcess");
		ProcessDefinition processDefinition = definitionKey.latestVersion()
				.singleResult();
		ProcessDefinitionQuery version = definitionKey.latestVersion();
		// 最后的版本对象拿到的是一个地址对象需要使用singleresult方法获取对象
		System.out.println(version);

		System.out.println("======================================");
		System.out.println(processDefinition);
		System.out.println("count--->" + count);
	}

	/**
	 * 遍历部署了多少个流程对象 每个流程对象的key name id都分别是什么
	 * 
	 * @author 徐雪娅
	 */
	@Test
	public void testDeplomentlist() {
		// 通过RepositoryService创建一个查询同一个流程有多少个部署对象他们分别对应的版本等信息
		DeploymentQuery query = processEngine.getRepositoryService()
				.createDeploymentQuery();
		List<Deployment> list = query.list();
		System.out.println(list.size());
		for (Deployment deployment : list) {

			System.out.println(deployment.getDeploymentTime());
			System.out.println(deployment.getName());
			System.out.println(deployment.getId());
			System.out.println(deployment.getTenantId());
		}
		ProcessDefinitionQuery query2 = processEngine.getRepositoryService()
				.createProcessDefinitionQuery();
		List<ProcessDefinition> list2 = query2.list();
		System.out.println(list2.size());
		for (ProcessDefinition processDefinition : list2) {
			System.out.println(processDefinition.getName());
			System.out.println(processDefinition.getResourceName());
		}
	}

	/**
	 * 以上只是讲述对流程的部署和查看遍历的过程 流程的运行过程
	 * 
	 * @author 徐雪娅
	 */
	@Test
	public void testRunProcess() {
		// 这个地方需要注意一下一个流程定义的key 或者id对应的是多个流程对象 然后取最后一个版本 在进行单例获取流程对象
		ProcessDefinitionQuery query = processEngine.getRepositoryService()
				.createProcessDefinitionQuery();
		// ProcessDefinition processDefinition =
		// query.processDefinitionKey("myProcess").latestVersion().singleResult();
		ProcessDefinitionQuery processDefinitionKey = query
				.processDefinitionKey("myProcess");
		// RuntimeService runtimeService = processEngine.getRuntimeService();
		// //通过运行时的服务对象得到一个流程的实例 而这个实例是通过流程定义的id获取具体的执行摸个流程的对象
		// ProcessInstance processInstance =
		// runtimeService.startProcessInstanceById(processDefinition.getId());
		List<ProcessDefinition> list = processDefinitionKey.list();
		List<ProcessDefinition> list2 = query.list();
		for (ProcessDefinition processDefinition : list) {
			System.out.println(processDefinition.getId());
			System.out.println(processDefinition.getName());
			System.out.println(processDefinition.getVersion());
		}
		for (ProcessDefinition processDefinition : list2) {
			System.out.println(processDefinition.getId());
			System.out.println(processDefinition.getName());
			System.out.println(processDefinition.getVersion());
		}
		System.out.println(processDefinitionKey.list() == query.list());
	}

	@Test
	public void testTark() {
		// ProcessDefinition singleResult =
		// processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		// 先得到任务服务对象再创建任务查询器然后查看委托人的任务列表得到任务集合
	TaskService taskService = processEngine.getTaskService();
	TaskQuery taskQuery = taskService.createTaskQuery();
	int size = taskQuery.list().size();
	System.out.println(size);
		List<Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee("tom").list();
		System.out.println(list);
		for (Task task : list) {
			System.out.println("委托人" + task.getAssignee());
			System.out.println("任务的名称" + task.getName());
		}
	}

	// 获取流程任务，完成任务

@Test
	public void testTask() {

		ProcessEngine processEngine = (ProcessEngine) ioc
				.getBean("processEngine");

		RepositoryService repositoryService = processEngine
				.getRepositoryService();

		// 查询流程定义

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess").latestVersion().singleResult();

		TaskService taskService = processEngine.getTaskService();

		TaskQuery taskQuery = taskService.createTaskQuery();

		List<Task> list1 = taskQuery.taskAssignee("tom").list(); // 获取zhangsan的任务列表

		List<Task> list2 = taskQuery.taskAssignee("lili").list();// 获取lisi的任务列表
int size = list1.size();
System.out.println(size);
		for (Task task : list1) {

			System.out.println("tom的任务=" + task.getName());
			taskService.complete(task.getId());
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		for (Task task : list2) {

			System.out.println("lili的任务=" + task.getName());

		}

	}

}
