package jointfaas.broker.controller.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class FunctionCreationRequest {

	@JSONField(name="funcName")
	private String funcName;

	@JSONField(name="memorySize")
	private String memorySize;

	@JSONField(name="codeZip")
	private String codeZip;

	@JSONField(name="env")
	private String env;

	@JSONField(name="timeout")
	private String timeout;

	public void setFuncName(String funcName){
		this.funcName = funcName;
	}

	public String getFuncName(){
		return funcName;
	}

	public void setMemorySize(String memorySize){
		this.memorySize = memorySize;
	}

	public String getMemorySize(){
		return memorySize;
	}

	public void setCodeZip(String codeZip){
		this.codeZip = codeZip;
	}

	public String getCodeZip(){
		return codeZip;
	}

	public void setEnv(String env){
		this.env = env;
	}

	public String getEnv(){
		return env;
	}

	public void setTimeout(String timeout){
		this.timeout = timeout;
	}

	public String getTimeout(){
		return timeout;
	}

	@Override
 	public String toString(){
		return 
			"FunctionCreationRequest{" +
			"funcName = '" + funcName + '\'' + 
			",memorySize = '" + memorySize + '\'' + 
			",codeZip = '" + codeZip + '\'' + 
			",env = '" + env + '\'' + 
			",timeout = '" + timeout + '\'' + 
			"}";
		}
}