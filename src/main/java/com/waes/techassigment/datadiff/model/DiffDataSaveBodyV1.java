package com.waes.techassigment.datadiff.model;

import java.io.Serializable;

public class DiffDataSaveBodyV1 implements Serializable {

    private static final long serialVersionUID = -3574210867177423391L;

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
