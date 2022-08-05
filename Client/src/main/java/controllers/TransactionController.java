package controllers;

import models.Id;

import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;
    MessageController messageController;
    IdController idController;


    public TransactionController(MessageController m, IdController i) {
        this.messageController=m;
        this.idController=i;

    }
//    public List<Id> getIds() {
//
//    }
    public String postId(String idtoRegister, String githubName) {
        Id tid = new Id(idtoRegister, githubName);
        tid = idCtrl.postId(tid);
        return ("Id registered.");
    }

    public String makecall(String s, String get, String s1) {
        return null;
    }
}
