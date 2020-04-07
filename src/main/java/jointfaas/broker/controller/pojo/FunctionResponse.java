package jointfaas.broker.controller.pojo;

import java.util.List;
import com.alibaba.fastjson.annotation.JSONField;
import jointfaas.client.pojo.Function;

public class FunctionResponse{

	@JSONField(name="functions")
	private List<Function> functions;

	@JSONField(name="status")
	private int status;

	public void setFunctions(List<Function> functions){
		this.functions = functions;
	}

	public List<Function> getFunctions(){
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