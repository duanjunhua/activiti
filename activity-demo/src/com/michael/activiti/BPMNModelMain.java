package com.michael.activiti;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;

public class BPMNModelMain {

        public static void main(String[] args) {
                // TODO Auto-generated method stub
                //1.Get the flow engine
                ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
                        
                //2.Get Repository Service, to save 
                RepositoryService repoService = engine.getRepositoryService();
                
                //3. create DeploymentBuilder
                DeploymentBuilder builder = repoService.createDeployment();
                
                //4. add resource to builder and deployment
                builder.addBpmnModel("BPMN Model", createBpmnModel());
                builder.deploy();
                
                //exit flow
                engine.close();
                System.exit(0);
        }
        
        private static BpmnModel createBpmnModel() {
                
                //create Bpmn object
                BpmnModel model = new BpmnModel();
                
                //create a flow define
                org.activiti.bpmn.model.Process	process = new org.activiti.bpmn.model.Process();
                model.addProcess(process);
                
                /**
                  <process id="easy_flow" name="My process" isExecutable="true">
                    <startEvent id="startevent1" name="Start"></startEvent>
                    <endEvent id="endevent1" name="End"></endEvent>
                    <userTask id="usertask1" name="apply task"></userTask>
                    <userTask id="usertask2" name="audit"></userTask>
                    <sequenceFlow id="flow1" sourceRef="startevent1" targetRef="usertask1"></sequenceFlow>
                    <sequenceFlow id="flow2" sourceRef="usertask1" targetRef="usertask2"></sequenceFlow>
                    <sequenceFlow id="flow3" sourceRef="usertask2" targetRef="endevent1"></sequenceFlow>
                  </process>
                 */
                
                //Flow definition info
                process.setId("bpmnModel");
                process.setName("BPMN Model");
                
                //Flow start event
                StartEvent startEvent = new StartEvent();
                startEvent.setId("start");
                startEvent.setName("Start");
                process.addFlowElement(startEvent);
                
                //Flow of user task
                UserTask userTask = new UserTask();
                userTask.setId("ut");
                userTask.setName("User Task");
                process.addFlowElement(userTask);
                
                //Flow of End event
                EndEvent endEvent = new EndEvent();
                endEvent.setId("end");
                endEvent.setName("End");
                process.addFlowElement(endEvent);
                
                //Sequence flow
                process.addFlowElement(new SequenceFlow("start", "ut"));
                process.addFlowElement(new SequenceFlow("ut", "end"));
                
                return model;
        }
}
