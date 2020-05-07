package client;

import quickfix.*;
import quickfix.Message;
import quickfix.fix44.*;
import quickfix.fix44.MessageCracker;

public class ClientApplication extends MessageCracker implements Application {
    @Override
    public void fromAdmin(Message arg0, SessionID arg1) throws FieldNotFound,
            IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println("Successfully called fromAdmin for sessionId : "
                + arg0);
    }

    @Override
    public void fromApp(Message arg0, SessionID arg1) throws FieldNotFound,
            IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        System.out.println("Successfully called fromApp for sessionId : "
                + arg0);
    }

    @Override
    public void onCreate(SessionID arg0) {
        System.out.println("Successfully called onCreate for sessionId : "
                + arg0);
    }

    @Override
    public void onLogon(SessionID arg0) {
        System.out.println("Successfully logged on for sessionId : " + arg0);

    }

    @Override
    public void onLogout(SessionID arg0) {
        System.out.println("Successfully logged out for sessionId : " + arg0);
    }


    @Override
    public void toAdmin(Message message, SessionID sessionId) {
        System.out.println("Inside toAdmin");
    }

    @Override
    public void toApp(Message arg0, SessionID arg1) throws DoNotSend {
        System.out.println("Message : " + arg0 + " for sessionid : " + arg1);
    }

    public void sendOrder(SessionID id, String message)  {
        Message msg = null;
        try {
            msg = MessageUtils.parse(Session.lookupSession(id), message);
            Session.sendToTarget(msg, id);
        } catch (InvalidMessage invalidMessage) {
            invalidMessage.printStackTrace();
        }
        catch (SessionNotFound sessionNotFound) {
            sessionNotFound.printStackTrace();
        }

    }
}
