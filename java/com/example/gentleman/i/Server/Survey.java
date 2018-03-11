package com.example.gentleman.i.Server;

import org.litepal.crud.DataSupport;

public class Survey extends DataSupport{
    private int id;
    private int ans_num;
    private String quesion;
    private String type;


    public int getAns_num() {
        return ans_num;
    }

    public void setAns_num(int ans_num) {
        this.ans_num = ans_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuesion() {
        return quesion;
    }

    public void setQuesion(String quesion) {
        this.quesion = quesion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
