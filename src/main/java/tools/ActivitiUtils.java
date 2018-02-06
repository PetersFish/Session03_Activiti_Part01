package tools;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;

import java.io.InputStream;

public class ActivitiUtils {

	//获得processEngine最基本方法
	public static ProcessEngine getEngine(){
		return ProcessEngines.getDefaultProcessEngine();
	}

	//获得processEngine:通过ProcessEngineConfiguration
	public static ProcessEngine getEngineByConfigurationInMem(){
		return ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration().buildProcessEngine();
	}

	//获得processEngine:通过建立一个输入流
	public static ProcessEngine getEngineByInputStream(){
		InputStream inputStream = ActivitiUtils.class.getClassLoader().getResourceAsStream("activiti.cfg.xml");
		return ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(inputStream).buildProcessEngine();
	}

	public static void main(String[] args) {

		//System.out.println(getEngine());

		//System.out.println(getEngineByInputStream());

		System.out.println(getEngineByConfigurationInMem());

	}
}
