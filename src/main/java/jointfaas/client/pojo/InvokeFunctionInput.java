package jointfaas.client.pojo;

public class InvokeFunctionInput {
    private String funcName;
    private String args;
    private String enableNative;

    public InvokeFunctionInput(String funcName, String args, String enableNative) {
        this.funcName = funcName;
        this.args = args;
        this.enableNative = enableNative;
    }

    public String getEnableNative() {
        return enableNative;
    }

    public void setEnableNative(String enableNative) {
        this.enableNative = enableNative;
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
