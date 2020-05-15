package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
    private String username;
    private String msg;
    private String type;
    private int onlineCount = 0;

    public Message (){

    }

    public Message (String username, String message){
        this.username = username;
        this.msg = message;
    }

    public Message (String username, String type, int count){
        this.username = username;
        this.type = type;
        this.onlineCount = count;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMsg(String text) {
        this.msg = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOnlineCount(int count) {
        this.onlineCount = count;
    }

    public String getUsername() {
        return this.username;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getType() {
        return this.type;
    }

    public int getOnlineCount(){
        return this.onlineCount;
    }
}
