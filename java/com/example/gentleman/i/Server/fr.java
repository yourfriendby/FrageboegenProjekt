package com.example.gentleman.i.Server;



import java.io.Serializable;


public class fr implements Serializable{
    private int data_id;
    private int ans_num;
    private String question;;
    private String type;
    private String []answer;

    public int getAns_num() {
        return ans_num;
    }

    public void setAns_num(int ans_num) {
        this.ans_num = ans_num;
    }

    public fr(int data_id, int ans_num, String question, String type, String []answer) {
        this.question=question;
        this.type=type;
        this.data_id=data_id;
        this.ans_num=ans_num;

        this.answer=answer;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }
}

