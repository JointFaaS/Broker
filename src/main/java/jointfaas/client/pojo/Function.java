package jointfaas.client.pojo;

import java.util.Map;

public class Function {
    private String functionName;
    private String description;
    private String runtime;
    private String handler;
    private int timeout;
    private long memorySize;
    private long codeSize;
    private String codeChecksum;
    private Map<String, String>  environmentVariables;
    private String createdTime;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public long getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(long memorySize) {
        this.memorySize = memorySize;
    }

    public long getCodeSize() {
        return codeSize;
    }

    public void setCodeSize(long codeSize) {
        this.codeSize = codeSize;
    }

    public String getCodeChecksum() {
        return codeChecksum;
    }

    public void setCodeChecksum(String codeChecksum) {
        this.codeChecksum = codeChecksum;
    }

    public Map<String, String> getEnvironmentVariables() {
        return environmentVariables;
    }

    public void setEnvironmentVariables(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
