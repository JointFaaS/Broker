package jointfaas.client.pojo;

public class CreateFunctionInput {

    private String funcName;
    private String timeout;
    private String memorySize;
    private String env;
    private String codeZip;

    public CreateFunctionInput(String funcName, String timeout, String memorySize, String env, String codeZip) {
        this.funcName = funcName;
        this.timeout = timeout;
        this.memorySize = memorySize;
        this.env = env;
        this.codeZip = codeZip;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getCodeZip() {
        return codeZip;
    }

    public void setCodeZip(String codeZip) {
        this.codeZip = codeZip;
    }
}
