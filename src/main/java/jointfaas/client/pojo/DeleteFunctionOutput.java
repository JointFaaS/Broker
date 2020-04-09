package jointfaas.client.pojo;

public class DeleteFunctionOutput {
    private String response;

    public DeleteFunctionOutput() {
    }

    public DeleteFunctionOutput(String res) {
        this.response = res;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
