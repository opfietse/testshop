package nl.minbzk.rig.demo.testshop.rest.model;

import java.io.Serializable;

public class CustomErrorType {
    private final int status;
    private final String message;

    public CustomErrorType(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
