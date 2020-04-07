package jointfaas.broker.service.impl;

import jointfaas.broker.configuration.AppConfig;
import jointfaas.broker.service.FunctionService;
import jointfaas.client.Client;
import jointfaas.client.impl.ClientWebClientImpl;
import jointfaas.client.pojo.CreateFunctionInput;
import jointfaas.client.pojo.DeleteFunctionInput;
import jointfaas.client.pojo.Function;
import jointfaas.client.pojo.InvokeFunctionInput;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class FunctionServiceImpl implements FunctionService {
    private final AppConfig appConfig;

    private Vector<Client> clients;
    public FunctionServiceImpl(AppConfig appConfig) throws Exception {
        this.appConfig = appConfig;
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
        }catch (InterruptedException ie) {

        }catch (WebClientResponseException e) {
            System.out.println(e.getResponseBodyAsString());
            throw e;
            // TODO
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
    public byte[] invokeFunction(String funcName, String args) {
        return clients.firstElement().invokeFunction(new InvokeFunctionInput(funcName, args)).getResponse();
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
        }catch (InterruptedException ie) {

        }
    }

    @Override
    public void refreshManagerState() {

    }

    @Override
    public void setManagerPrice(String name, double price) {

    }
}
