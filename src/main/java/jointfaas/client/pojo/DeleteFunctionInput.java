package jointfaas.client.pojo;

public class DeleteFunctionInput {
    private String funcName;

    public DeleteFunctionInput(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }
}
