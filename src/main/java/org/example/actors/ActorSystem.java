package org.example.actors;

import org.example.message.Message;

import java.util.HashMap;
public class ActorSystem {
    static HashMap<String, BaseActor> actors;
    public ActorSystem(){}

    public void sendMessage(String senderAddress, String recipientAddress, Message message){
        actors.get(recipientAddress).receiveMessage(senderAddress, message);
    }
    public static String ask(String recipientAddress, Message message){
        return(actors.get(recipientAddress).receiveMessage(message));
    }
}
