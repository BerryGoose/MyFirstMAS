package org.example.actors;

import org.example.message.Message;
import java.util.HashMap;
import java.util.Objects;
public class BaseActor{
    protected String myAddress;
    protected static int time;
    protected static HashMap<String, BaseActor> actorArea = new HashMap<String, BaseActor>();
    public BaseActor(){
        myAddress = "";
    }
    public BaseActor(String myAddress) {
        this.myAddress = myAddress;
        actorArea.put(myAddress, this);
    }
    public String getMyAddress() {
        return myAddress;
    }
    public void send(String recipientAddress, Message message){
        actorArea.get(recipientAddress).receiveMessage(myAddress, message);
    }

    public Object ask(String recipientAddress, Message message){
        return actorArea.get(recipientAddress).receiveMessage(myAddress, message);

    }
    public Object receiveMessage(String sender, Message message){
        return null;
    }

    @Override
    public String toString() {
        return "BaseActor{" +
                "myAddress='" + myAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseActor baseActor = (BaseActor) o;
        return Objects.equals(myAddress, baseActor.myAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myAddress);
    }
}
