package jointfaas.broker.controller.pojo;

import java.util.List;
import com.alibaba.fastjson.annotation.JSONField;

public class FunctionResponse{

	@JSONField(name="functions")
	private List<FunctionsItem> functions;

	@JSONField(name="status")
	private int status;

	public void setFunctions(List<FunctionsItem> functions){
		this.functions = functions;
	}

	public List<FunctionsItem> getFunctions(){
		return functions;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"FunctionResponse{" + 
			"functions = '" + functions + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}