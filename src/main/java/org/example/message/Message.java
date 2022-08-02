package org.example.message;

public class Message {
    private final MsgType type;
    private final String content;

    public Message(MsgType type, String content){
        this.type = type;
        this.content = content;
    }
    public Message(MsgType type){
        this.type = type;
        this.content = "";
    }

    public MsgType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
