package com.waes.techassigment.main.com.waes.techassigment.datadiff.model;

import java.io.Serializable;

public class DiffDataSaveResponseV1 implements Serializable {

    private static final long serialVersionUID = 7736777610990422516L;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
