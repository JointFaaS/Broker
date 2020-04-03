package jointfaas.broker.controller;

import jointfaas.broker.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    @PostMapping("/function")
    synchronized public String userSolve(@RequestBody String functionCreation) throws IOException {
        return "";
    }
}
