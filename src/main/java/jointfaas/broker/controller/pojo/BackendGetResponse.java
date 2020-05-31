package jointfaas.broker.controller.pojo;

import java.util.List;

public class BackendGetResponse {
    private List<BackendOTD> backends;

    public List<BackendOTD> getBackends() {
        return backends;
    }

    public void setBackends(List<BackendOTD> backends) {
        this.backends = backends;
    }
}
