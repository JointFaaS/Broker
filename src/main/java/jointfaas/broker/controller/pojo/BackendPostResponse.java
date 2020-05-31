package jointfaas.broker.controller.pojo;

public class BackendPostResponse {
    public enum ResStatus {
        OK,
        InvalidAddr,
        InvalidPrice,
        InvalidPriority,
        RuntimeError
    }

    public BackendPostResponse(ResStatus status) {
        this.status = status;
    }

    private ResStatus status;

    public ResStatus getStatus() {
        return status;
    }

    public void setStatus(ResStatus status) {
        this.status = status;
    }
}
