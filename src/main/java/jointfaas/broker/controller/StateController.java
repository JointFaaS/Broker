package jointfaas.broker.controller;

import jointfaas.broker.backend.Backend;
import jointfaas.broker.controller.pojo.BackendGetResponse;
import jointfaas.broker.controller.pojo.BackendOTD;
import jointfaas.broker.controller.pojo.BackendPostResponse;
import jointfaas.broker.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
public class StateController {
    @Autowired
    private FunctionService functionService;

    @GetMapping("/backend")
    @ResponseBody
    public BackendGetResponse getBackends() {
        BackendGetResponse res = new BackendGetResponse();
        try {
            List<Backend> backends = functionService.getBackends();
            List<BackendOTD> backendOTDS = new ArrayList<>();
            for (Backend b : backends) {
                backendOTDS.add(new BackendOTD(b.getAddr(), b.getPrice(), b.getLatency(), b.isActive(), b.getPriority()));
            }
            res.setBackends(backendOTDS);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @PostMapping("/backend")
    @ResponseBody
    public BackendPostResponse postBackend(@RequestBody Backend backend) {
        BackendPostResponse res = new BackendPostResponse(BackendPostResponse.ResStatus.OK);
        try {
            functionService.updateBackend(backend.getAddr(), backend.getPrice(), backend.getLatency(), backend.getPriority(), backend.isActive());
        }catch (Exception e) {
            e.printStackTrace();
            res.setStatus(BackendPostResponse.ResStatus.RuntimeError);
        }
        return res;
    }
}