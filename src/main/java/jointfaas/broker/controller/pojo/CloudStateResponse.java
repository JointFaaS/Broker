package jointfaas.broker.controller.pojo;

import java.util.List;

public class CloudStateResponse {
    public class CloudState {
        Integer unifiedPrice;   //  MB/s
        Integer unifiedResource; // 128MB/3s function num

        public Integer getUnifiedPrice() {
            return unifiedPrice;
        }

        public void setUnifiedPrice(Integer unifiedPrice) {
            this.unifiedPrice = unifiedPrice;
        }

        public Integer getUnifiedResource() {
            return unifiedResource;
        }

        public void setUnifiedResource(Integer unifiedResource) {
            this.unifiedResource = unifiedResource;
        }
    }

    private List<CloudState> cloudStates;

    public List<CloudState> getCloudStates() {
        return cloudStates;
    }

    public void setCloudStates(List<CloudState> cloudStates) {
        this.cloudStates = cloudStates;
    }
}