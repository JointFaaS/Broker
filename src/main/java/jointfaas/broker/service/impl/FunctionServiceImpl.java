package jointfaas.broker.service.impl;

import jointfaas.broker.configuration.AppConfig;
import jointfaas.broker.service.FunctionService;
import jointfaas.broker.service.pojo.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    private AppConfig appConfig;

    @Override
    public void createFunction(String funcName, int timeout, int memorySize, String env, String code) {

    }

    @Override
    public Function getFunction(String id) {
        return null;
    }

    @Override
    public List<Function> getFunctions() {
        return null;
    }

    @Override
    public ByteBuffer invokeFunction(String funcName, String args) {
        return null;
    }

    @Override
    public void deleteFunction(String funcName) {

    }

    @Override
    public void refreshManagerState() {

    }

    @Override
    public void setManagerPrice(String name, double price) {

    }
}
