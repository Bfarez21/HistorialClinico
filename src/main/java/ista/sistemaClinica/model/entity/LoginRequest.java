package ista.sistemaClinica.model.entity;

public class LoginRequest {
    private String cedulaDoc;
    private String passwordDoc;

    public String getPasswordDoc() {
        return passwordDoc;
    }

    public void setPasswordDoc(String passwordDoc) {
        this.passwordDoc = passwordDoc;
    }

    public String getCedulaDoc() {
        return cedulaDoc;
    }

    public void setCedulaDoc(String cedulaDoc) {
        this.cedulaDoc = cedulaDoc;
    }
}
