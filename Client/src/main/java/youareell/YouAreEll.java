package youareell;

import controllers.*;

public class YouAreEll {

    TransactionController transactionController;
    MessageController messageController;
    IdController idController;

    public YouAreEll (TransactionController t) {
        this.transactionController = t;
    }
//    public YouAreEll (MessageController mc, IdController idc) {
//        this.messageController=mc;
//        this.idController=idc;
//    }

    public static void main(String[] args) {
        // hmm: is this Dependency Injection?
        YouAreEll urlhandler = new YouAreEll(
            new TransactionController(new MessageController(), new IdController()));
//        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
//        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String get_ids() {
        return transactionController.makecall("/ids", "GET", "");
    }

    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }

    private String MakeURLCall(String s, String get, String s1) {
        return null;
    }


}
