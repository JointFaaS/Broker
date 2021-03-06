package jointfaas.broker.controller;

import jointfaas.broker.controller.pojo.*;
import jointfaas.broker.service.FunctionService;
import jointfaas.client.pojo.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
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
    public void invokeFunction(@RequestBody FunctionInvocationRequest functionInvocationRequest, HttpServletResponse response) {
        try {
            response.getOutputStream().write(
                    functionService.invokeFunction(
                            functionInvocationRequest.getFuncName(),
                            functionInvocationRequest.getArgs(),
                            functionInvocationRequest.getEnableNative()));
        }catch (Exception e) {
            e.printStackTrace();
        }
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

    @GetMapping("/functionCode/{id}")
    @ResponseBody
    public FunctionCodeResponse getFunctionCode(@PathVariable String id) {
        FunctionCodeResponse response = new FunctionCodeResponse();
        response.setCodeZipLink(functionService.getFunctionCode(id));
        response.setStatus(200);
        return response;
    }
}
