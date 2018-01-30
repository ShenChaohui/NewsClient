package com.geniuses.newsclient.entity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Sch on 2018/1/29.
 */
@Table(name = "ChatMsgModel")
public class ChatMsgModel {
    @Column(name = "ID",isId = true,autoGen = true)
    private int id;
    @Column(name = "TYPE")
    private int type;
    @Column(name = "MSG")
    private String msg;

    public ChatMsgModel(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public ChatMsgModel() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
