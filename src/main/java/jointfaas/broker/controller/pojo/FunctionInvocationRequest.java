package jointfaas.broker.controller.pojo;

public class FunctionInvocationRequest {
    private String funcName;
    private String args;

    public FunctionInvocationRequest(String funcName, String args) {
        this.funcName = funcName;
        this.args = args;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
