package org.example.message;
public class Message {
    private final MsgType type;
    private final Object content;

    public Message(MsgType type, Object content){
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
    public Object getContent() {
        return content;
    }
}
