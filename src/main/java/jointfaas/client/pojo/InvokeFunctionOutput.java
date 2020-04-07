package jointfaas.client.pojo;

public class InvokeFunctionOutput {
    private int status;
    private byte[] response;

    public InvokeFunctionOutput(int status, byte[] response) {
        this.status = status;
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public byte[] getResponse() {
        return response;
    }

    public void setResponse(byte[] response) {
        this.response = response;
    }
}

