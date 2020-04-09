package jointfaas.broker.service.impl;

import jointfaas.broker.configuration.AppConfig;
import jointfaas.broker.service.FunctionService;
import jointfaas.client.Client;
import jointfaas.client.impl.ClientWebClientImpl;
import jointfaas.client.pojo.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Base64;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class FunctionServiceImpl implements FunctionService {
    private Vector<Client> clients;

    public FunctionServiceImpl(AppConfig appConfig) throws Exception {
        this.clients = new Vector<>();
        for (String addr: appConfig.getManagers()) {
            clients.add(new ClientWebClientImpl(addr));
        }
        if (clients.isEmpty()) {
            throw new Exception("No available Manager");
        }
    }

    @Override
    public void createFunction(String funcName, String timeout, String memorySize, String env, String code) throws Exception {
        try {
            CreateFunctionInput createFunctionInput = new CreateFunctionInput(funcName, timeout, memorySize, env, code);
            CountDownLatch count = new CountDownLatch(clients.size());
            for (Client client: clients) {
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
        return clients.firstElement().getFunction(id);
    }

    @Override
    public List<Function> getFunctions() {
        return clients.firstElement().getFunctions();
    }

    @Override
    public byte[] invokeFunction(String funcName, String args, String enableNative) {
        InvokeFunctionOutput invokeFunctionOutput = clients.firstElement().invokeFunction(new InvokeFunctionInput(funcName, args, enableNative));
        if (invokeFunctionOutput.getStatus() == 200) {
            return invokeFunctionOutput.getResponse();
        } else {
            return invokeFunctionOutput.getErrMsg().getBytes();
        }
    }

    @Override
    public void deleteFunction(String funcName) throws Exception {
        try {
            DeleteFunctionInput deleteFunctionInput = new DeleteFunctionInput(funcName);
            CountDownLatch count = new CountDownLatch(clients.size());
            for (Client client: clients) {
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
    public void refreshManagerState() {

    }

    @Override
    public void setManagerPrice(String name, double price) {

    }
}
