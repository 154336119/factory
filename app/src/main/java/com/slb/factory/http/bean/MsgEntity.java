package com.slb.factory.http.bean;

/**
 * 描述：
 * Created by Lee
 * on 2016/12/26.
 */
public class MsgEntity {

    /**
     * id : 1
     * title : 系统通知
     * content : 41a99fcbcceb16e67c39d43bb943e14d80141308bd4e59077b14fbf99cc9f0e1
     * create_time : 2019-05-30 06:12:46
     */

    private int id;
    private String title;
    private String content;
    private String create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
