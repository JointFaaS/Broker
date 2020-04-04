package jointfaas.broker.service;

import jointfaas.broker.service.pojo.Function;

import java.nio.ByteBuffer;
import java.util.List;

public interface FunctionService {
    void createFunction(String funcName, int timeout, int memorySize, String env, String code);

    Function getFunction(String id);

    List<Function> getFunctions();

    ByteBuffer invokeFunction(String funcName, String args);

    void deleteFunction(String funcName);

    void refreshManagerState();

    void setManagerPrice(String name, double price);
}
