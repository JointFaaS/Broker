package jointfaas.broker.service.impl;

import jointfaas.broker.configuration.AppConfig;
import jointfaas.broker.backend.Backend;
import jointfaas.broker.service.FunctionService;
import jointfaas.client.Client;
import jointfaas.client.pojo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class FunctionServiceImpl implements FunctionService {
    private List<Backend> backends;

    public FunctionServiceImpl(AppConfig appConfig) throws Exception {
        this.backends = new ArrayList<>();

        for (String addr: appConfig.getManagers()) {
            backends.add(new Backend(addr));
        }
        if (backends.isEmpty()) {
            throw new Exception("No available Manager");
        }
    }


    @Override
    public void createFunction(String funcName, String timeout, String memorySize, String env, String code) throws Exception {
        try {
            CreateFunctionInput createFunctionInput = new CreateFunctionInput(funcName, timeout, memorySize, env, code);
            CountDownLatch count = new CountDownLatch(backends.size());
            for (Client client: backends) {
                new Thread(()-> {
                    client.createFunction(createFunctionInput);
                    count.countDown();
                }).start();
            }
            if (!count.await(10, TimeUnit.SECONDS)) {
                throw new Exception();
                // TODO: make exception typical
            }
        }catch (InterruptedException e) {

        }
    }

    @Override
    public Function getFunction(String id) {
        return backends.get(0).getFunction(id);
    }

    @Override
    public List<Function> getFunctions() {
        return backends.get(0).getFunctions();
    }

    @Override
    public byte[] invokeFunction(String funcName, String args, String enableNative) {
        InvokeFunctionOutput invokeFunctionOutput = backends.get(0).invokeFunction(new InvokeFunctionInput(funcName, args, enableNative));
        if (invokeFunctionOutput.getStatus() == 200) {
            return invokeFunctionOutput.getResponse();
        } else {
            return invokeFunctionOutput.getErrMsg().getBytes();
        }
    }

    // BackendOTD is an extra layer between web and standard hcloud client.
    // it is used to offer price and distance emulate.
    // But in future, hcloud Manager will support pricing and performance monitoring.
    // And then hcloud Client will take over the backend's responsibility.
    // offer the real price and distance.
    @Override
    public void updateBackend(String addr, Long price, Long latency, Long priority, boolean active) {
        for (Backend b: backends) {
            if (addr.equals(b.getAddr())) {
                b.setActive(active);
                b.setLatency(latency);
                b.setPrice(price);
                b.setPriority(priority);
            }
        }
        reorderBackend();
    }

    private void reorderBackend() {
        backends.sort((Backend a, Backend b)->{
            if (a.getPriority() < b.getPriority()){
                return -1;
            }else if (a.getPriority().equals(b.getPriority())) {
                return 0;
            }else{
                return 1;
            }
        });
    }

    @Override
    public List<Backend> getBackends() {
        return backends;
    }

    @Override
    public void deleteFunction(String funcName) throws Exception {
        try {
            DeleteFunctionInput deleteFunctionInput = new DeleteFunctionInput(funcName);
            CountDownLatch count = new CountDownLatch(backends.size());
            for (Client client: backends) {
                new Thread(()-> {
                    client.deleteFunction(deleteFunctionInput);
                    count.countDown();
                }).start();
            }
            if (!count.await(10, TimeUnit.SECONDS)) {
                throw new Exception();
                // TODO: make exception typical
            }
        }catch (InterruptedException e) {

        }
    }



    @Override
    public String getFunctionCode(String id) {
        return null;
    }
}
