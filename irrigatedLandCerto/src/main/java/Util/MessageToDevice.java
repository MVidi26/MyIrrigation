package Util;

import java.io.Serializable;

public class MessageToDevice implements Serializable {

    private int codigo;
    private String message;

    public MessageToDevice() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
