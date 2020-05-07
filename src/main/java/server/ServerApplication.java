package server;

import quickfix.*;
import quickfix.fix44.MessageCracker;
import quickfix.fix44.NewOrderSingle;

public class ServerApplication extends MessageCracker implements Application {
    @Override
    public void onCreate(SessionID sessionID) {

    }

    @Override
    public void onLogon(SessionID sessionID) {

    }

    @Override
    public void onLogout(SessionID sessionID) {

    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {

    }

    @Override
    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {

    }

    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {

    }

    @Override
    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        crack(message, sessionID);
    }

    @Override
    public void onMessage(NewOrderSingle message, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        System.out.println("message received " + message.getSymbol() + ", " + message.getOrderQty());
        super.onMessage(message, sessionID);
    }
}
