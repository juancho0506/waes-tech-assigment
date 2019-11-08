package com.waes.techassigment.datadiff.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiffDataResponseV1 implements Serializable {

    private static final long serialVersionUID = -4220155926856182077L;

    private String message;

    private List<String> differences = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDifferences() {
        return differences;
    }

    public void setDifferences(List<String> differences) {
        this.differences = differences;
    }
}
