package jointfaas.broker.controller;

import jointfaas.broker.controller.pojo.FunctionCreationRequest;
import jointfaas.broker.controller.pojo.FunctionCreationResponse;
import jointfaas.broker.controller.pojo.FunctionInvocationRequest;
import jointfaas.broker.controller.pojo.FunctionResponse;
import jointfaas.broker.service.FunctionService;
import jointfaas.client.pojo.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    @PostMapping("/function")
    @ResponseBody
    public FunctionCreationResponse createFunction(@RequestBody FunctionCreationRequest functionCreationRequest) {
        try {
            functionService.createFunction(
                    functionCreationRequest.getFuncName(),
                    functionCreationRequest.getTimeout(),
                    functionCreationRequest.getMemorySize(),
                    functionCreationRequest.getEnv(),
                    functionCreationRequest.getCodeZip());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new FunctionCreationResponse();
    }

    @PostMapping("/invoke")
    public byte[] invokeFunction(@RequestBody FunctionInvocationRequest functionInvocationRequest) {
        try {
            return functionService.invokeFunction(functionInvocationRequest.getFuncName(), functionInvocationRequest.getArgs());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @DeleteMapping("/function/{id}")
    @ResponseBody
    public String delFunction(@PathVariable String id) {
        try {
            functionService.deleteFunction(id);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("/function/{id}")
    @ResponseBody
    public FunctionResponse getFunction(@PathVariable String id) {
        FunctionResponse response = new FunctionResponse();
        List<Function> functions = new ArrayList<>();
        functions.add(functionService.getFunction(id));
        response.setFunctions(functions);
        return response;
    }

    @GetMapping("/function")
    @ResponseBody
    public FunctionResponse getFunction() {
        FunctionResponse response = new FunctionResponse();
        response.setFunctions(functionService.getFunctions());
        return response;
    }
}
