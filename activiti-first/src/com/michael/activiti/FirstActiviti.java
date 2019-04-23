package com.michael.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class FirstActiviti {

	public static void main(String[] args) {
		//创建流程引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		//获取流程存储服务组件
		RepositoryService repositoryService = engine.getRepositoryService();
		//获取运行时服务组件
		RuntimeService runService = engine.getRuntimeService();
		//获取流程任务组件
		TaskService taskService = engine.getTaskService();
		
		//部署流程文件
		repositoryService.createDeployment().addClasspathResource("bpmn/first.bpmn").deploy();
		
		//启动流程任务,firstProcess对应流程文件的process id
		runService.startProcessInstanceByKey("firstProcess");
		
		//查询第一个任务,对于多个任务使用list()代替singleResult()
		Task task = taskService.createTaskQuery().singleResult();
		System.out.println("第一个任务，任务名为：" + task.getName());
		
		//完成第一个任务
		taskService.complete(task.getId());
		
		//查询第二个任务
		task = taskService.createTaskQuery().singleResult();
		System.out.println("第二个任务，任务名为：" + task.getName());
		
		//完成第二个任务
		taskService.complete(task.getId());
		
		//再次查询任务
		task = taskService.createTaskQuery().singleResult();
		System.out.println("当前任务为：" + task);
		
		//退出
		System.exit(0);
	}
}
