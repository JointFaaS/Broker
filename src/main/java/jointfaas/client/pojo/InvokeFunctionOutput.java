package jointfaas.client.pojo;

public class InvokeFunctionOutput {
    private int status;
    private byte[] response;
    private String errMsg;

    public InvokeFunctionOutput(int status, String errMsg) {
        this.status = status;
        this.errMsg = errMsg;
    }

    public InvokeFunctionOutput(int status, byte[] response) {
        this.status = status;
        this.response = response;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
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

