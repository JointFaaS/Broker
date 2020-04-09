package jointfaas.broker.controller.pojo;

public class FunctionCodeResponse {
    private String codeZipLink;
    private int status;

    public FunctionCodeResponse() {
    }

    public FunctionCodeResponse(String codeZipLink, int status) {
        this.codeZipLink = codeZipLink;
        this.status = status;
    }

    public String getCodeZipLink() {
        return codeZipLink;
    }

    public void setCodeZipLink(String codeZipLink) {
        this.codeZipLink = codeZipLink;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
