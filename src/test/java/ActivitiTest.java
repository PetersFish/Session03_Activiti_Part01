import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class ActivitiTest {

	//初始化ProcessEngine
	@Rule
	public ActivitiRule rule = new ActivitiRule();

	//测试部署
	@Test
	public void testDepoly(){
		RepositoryService repositoryService = rule.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("process01.bpmn").name("审批流程").deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		System.out.println(deployment.getName());
		System.out.println(deployment.getCategory());
	}

	//通过id获得部署信息
	@Test
	public void testGetDefination(){
		RepositoryService repositoryService = rule.getRepositoryService();
		Deployment deployment = repositoryService.createDeploymentQuery().deploymentId("2501").singleResult();
		System.out.println(deployment.getId()+":"+deployment.getName()+":"+deployment.getDeploymentTime());
	}

	//通过defination key获得部署信息
	@Test
	public void testGetDefinationByDefKey(){
		RepositoryService repositoryService = rule.getRepositoryService();
		List<Deployment> deployments = repositoryService.createDeploymentQuery().processDefinitionKey("myProcess_1").listPage(0, 1);
		for (Deployment deployment : deployments) {
			System.out.println(deployment.getId()+":"+deployment.getName()+":"+deployment.getDeploymentTime());
		}
	}

	//启动流程
	@Test
	public void testStartProcess(){

		RuntimeService runtimeService = rule.getRuntimeService();

		ProcessInstance processInstance = runtimeService.startProcessInstanceById("myProcess_1:1:2504");

		System.out.println("id----->"+processInstance.getId());

		System.out.println("ProcessDefinitionId----->"+processInstance.getProcessDefinitionId());

		System.out.println("key----->"+processInstance.getProcessDefinitionKey());

		System.out.println("name----->"+processInstance.getName());
	}

	//查找个人任务
	@Test
	public void testGetPersonalTask(){
		TaskService taskService = rule.getTaskService();
		List<Task> list = taskService.createTaskQuery().processDefinitionId("myProcess_1:1:2504").list();
		for (Task task : list) {
			System.out.println("Id------>"+task.getId());
			System.out.println("Name------>"+task.getName());
			System.out.println("Assignee------>"+task.getAssignee());
			System.out.println("ProcessInstanceId------>"+task.getProcessInstanceId());
			if (task.getId().equals("7504")) {
				taskService.complete(task.getId());
			}
		}
	}

}
