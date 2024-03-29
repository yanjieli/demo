package com.example.demo.mq.kafka;

import java.util.Date;

public class Message {

    private long id;
    private String msg;
    private Date sendTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", msg=" + msg + ", sendTime=" + sendTime + "]";
    }

}
