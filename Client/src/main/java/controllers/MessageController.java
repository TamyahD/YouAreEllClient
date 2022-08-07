package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

public class MessageController {

//    private HashSet<Message> messagesSeen;
    // why a HashSet??

    private List<Object> messageList;
    private Object[] messagesArray;
    ServerController sc = ServerController.getServerInstance();
    ObjectMapper mapper = new ObjectMapper();

    public List<Message> getMessages() throws JsonProcessingException {

//        String messagesJSONResult = null;
        if (this.messageList ==null) {
            try {
                sc.getURL("http://zipcode.rocks:8085/messages");
                HttpEntity messagesEntity = sc.httpResponse.getEntity();
//                Integer status = sc.handleResponse(sc.httpResponse);    //http response code, successful or failed connection
                this.messagesArray = mapper.readValue(EntityUtils.toString(messagesEntity),
                        new TypeReference<Object[]>() {});
                this.messageList = Arrays.asList(messagesArray);

                int count=messagesArray.length;
                System.out.println("Messages array count: " +count);


                for (Object obj : messageList) {
                    String objStr = obj.toString();
                    System.out.println(objStr);
                }
                } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
    public ArrayList<Message> getMessagesForId(Id Id) {
        return null;
    }
    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}