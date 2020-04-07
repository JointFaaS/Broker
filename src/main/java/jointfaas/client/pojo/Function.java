package jointfaas.client.pojo;

public class Function {
    private String funcName;

    private int memorySize;

    private String env;

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
