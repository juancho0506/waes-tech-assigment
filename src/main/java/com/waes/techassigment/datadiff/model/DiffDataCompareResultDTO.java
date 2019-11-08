package com.waes.techassigment.datadiff.model;

import java.util.ArrayList;
import java.util.List;

public class DiffDataCompareResultDTO {
    private boolean isEqualSize = false;
    private boolean isContentEqual = false;
    private String resultMessage = "";
    private List<String> diffResults = new ArrayList<>();

    public String getResultMessage() {
        return resultMessage;
    }

    public boolean isEqualSize() {
        return isEqualSize;
    }

    public void setEqualSize(boolean equalSize) {
        isEqualSize = equalSize;
    }

    public boolean isContentEqual() {
        return isContentEqual;
    }

    public void setContentEqual(boolean contentEqual) {
        isContentEqual = contentEqual;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<String> getDiffResults() {
        return diffResults;
    }

    public void setDiffResults(List<String> diffResults) {
        this.diffResults = diffResults;
    }

    public void addDiffResult(final String result){
        this.diffResults.add(result);
    }
}
