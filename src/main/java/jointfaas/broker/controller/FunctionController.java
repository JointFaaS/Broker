package jointfaas.broker.controller;

import jointfaas.broker.controller.pojo.FunctionCreationRequest;
import jointfaas.broker.controller.pojo.FunctionResponse;
import jointfaas.broker.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    @PostMapping("/function")
    public String createFunction(@RequestBody FunctionCreationRequest functionCreationRequest) {
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
        return "";
    }

    @GetMapping("/function/{id}")
    public FunctionResponse getFunction(@PathVariable String id) {
        FunctionResponse response = new FunctionResponse();

        return response;
    }
}
