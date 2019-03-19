package com.michael.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public class BpmnDemo {

        public static void main(String[] args) {
                // TODO Auto-generated method stub
                
                //1.Get the flow engine
                ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
                
                //2.Get Repository Service, to save 
                RepositoryService repoService = engine.getRepositoryService();
                
                //3.Get Runtime Service
                RuntimeService runService = engine.getRuntimeService();
                
                //4.Get TaskService to handle take
                TaskService taskService = engine.getTaskService();
                
                //Deploy Service, Can deploy multiple file at once
                repoService.createDeployment().addClasspathResource("easy.bpmn").deploy();

        //start flow process, return flow instance
        ProcessInstance instance = runService.startProcessInstanceByKey("easy");
        while(true) {
                //query the task by taskService
                Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
                if(null == task) break;
                
                System.out.println("Current task: " + task.getAssignee() + " : " + task.getName());
                //Completed the task
                taskService.complete(task.getId());
        } 
        
        //Close engine
        engine.close();
        
        //exit flow
        System.exit(0);
        }
}
