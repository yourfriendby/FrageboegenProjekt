package com.example.gentleman.i.Server;


import org.litepal.crud.DataSupport;

public class Answers extends DataSupport{
    private int id;
    private int data_id;
    private String answer;

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
