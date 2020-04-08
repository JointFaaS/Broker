package jointfaas.broker.service;

import jointfaas.client.pojo.Function;

import java.nio.ByteBuffer;
import java.util.List;

public interface FunctionService {
    void createFunction(String funcName, String timeout, String memorySize, String env, String code) throws Exception;

    Function getFunction(String id);

    List<Function> getFunctions();

    byte[] invokeFunction(String funcName, String args, String enableNative);

    void deleteFunction(String funcName) throws Exception;

    void refreshManagerState();

    void setManagerPrice(String name, double price);
}
