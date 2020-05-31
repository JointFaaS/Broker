package jointfaas.broker.controller.pojo;

public class BackendOTD {
    private String addr;
    private Long latency;
    private Long price;
    private boolean active;
    private Long priority;

    public BackendOTD(String addr) {
        this.addr = addr;
        this.latency = 50L;
        this.price = 50L;
        this.active = true;
        this.priority = 1L;
    }

    public BackendOTD(String addr, Long price, Long latency, boolean active, Long priority) {
        this.addr = addr;
        this.latency = latency;
        this.price = price;
        this.active = active;
        this.priority = priority;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Long getLatency() {
        return latency;
    }

    public void setLatency(Long latency) {
        this.latency = latency;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}