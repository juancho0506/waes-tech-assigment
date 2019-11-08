package com.waes.techassigment.main.com.waes.techassigment.datadiff.model;

import java.io.Serializable;

public class DiffDataResponseV1 implements Serializable {

    private static final long serialVersionUID = -4220155926856182077L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
