package com.waes.techassigment.main.com.waes.techassigment.datadiff.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diff_data")
public class DiffData {

    @Id
    @Column
    private int id;

    @Column
    private String dataLeft;

    @Column
    private String dataRight;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataLeft() {
        return dataLeft;
    }

    public void setDataLeft(String dataLeft) {
        this.dataLeft = dataLeft;
    }

    public String getDataRight() {
        return dataRight;
    }

    public void setDataRight(String dataRight) {
        this.dataRight = dataRight;
    }
}
