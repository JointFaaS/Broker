package jointfaas.client.pojo;

public class InvokeFunctionInput {
    private String funcName;
    private String args;

    public InvokeFunctionInput(String funcName, String args) {
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
