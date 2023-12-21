package edu.upc.dsa.models;

public class CredencialesRespuesta {
    Boolean success;
    String message;

    public CredencialesRespuesta(){
        this.success=false;
        this.message=null;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
