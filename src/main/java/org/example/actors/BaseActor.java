package org.example.actors;

import org.example.message.Message;

import java.util.Objects;

public class BaseActor extends ActorSystem {
    final String myAddress;
    public BaseActor(String myAddress) {
        this.myAddress = myAddress;
    }

    public String getMyAddress() {
        return myAddress;
    }
    public void send(String recipientAddress, Message message){
        super.sendMessage(myAddress, recipientAddress, message);
    }
    public void receiveMessage(String sender, Message message){

    }
    public String receiveMessage(Message message){
        return "";
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
