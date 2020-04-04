package jointfaas.broker.controller.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class FunctionsItem{

	@JSONField(name="funcName")
	private String funcName;

	@JSONField(name="memorySize")
	private int memorySize;

	@JSONField(name="env")
	private String env;

	@JSONField(name="timeout")
	private int timeout;

	public void setFuncName(String funcName){
		this.funcName = funcName;
	}

	public String getFuncName(){
		return funcName;
	}

	public void setMemorySize(int memorySize){
		this.memorySize = memorySize;
	}

	public int getMemorySize(){
		return memorySize;
	}

	public void setEnv(String env){
		this.env = env;
	}

	public String getEnv(){
		return env;
	}

	public void setTimeout(int timeout){
		this.timeout = timeout;
	}

	public int getTimeout(){
		return timeout;
	}

	@Override
 	public String toString(){
		return 
			"FunctionsItem{" + 
			"funcName = '" + funcName + '\'' + 
			",memorySize = '" + memorySize + '\'' + 
			",env = '" + env + '\'' + 
			",timeout = '" + timeout + '\'' + 
			"}";
		}
}