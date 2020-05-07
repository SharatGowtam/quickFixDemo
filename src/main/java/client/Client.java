package client;

import quickfix.*;
import quickfix.field.HeartBtInt;
import quickfix.field.ResetSeqNumFlag;
import quickfix.fix44.Logon;
import utils.FileParser;

import javax.swing.*;
import java.util.List;

public class Client {
    private static Client client;
    private Initiator initiator = null;
    private JFrame frame = null;

    public Client(String[] args) {
        SocketInitiator socketInitiator = null;
        try {
            SessionSettings sessionSettings = new SessionSettings("./client.txt");
            ClientApplication application = new ClientApplication();
            FileStoreFactory fileStoreFactory = new FileStoreFactory(sessionSettings);
            FileLogFactory logFactory = new FileLogFactory(sessionSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            socketInitiator = new SocketInitiator(application,
                    fileStoreFactory, sessionSettings, logFactory,
                    messageFactory);
            socketInitiator.start();
            SessionID sessionId = socketInitiator.getSessions().get(0);
            sendLogonRequest(sessionId);


           // List<String> list = FileParser.parseFile("C:\\Users\\speri02\\Desktop\\sample messages.txt");
            frame = new ReplayFrame(sessionId, application);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (ConfigError e) {
            e.printStackTrace();
        } catch (SessionNotFound e) {
            e.printStackTrace();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private static void sendLogonRequest(SessionID sessionId)
            throws SessionNotFound {
        Logon logon = new Logon();
        Message.Header header = logon.getHeader();
        logon.set(new HeartBtInt(30));
        logon.set(new ResetSeqNumFlag(true));
        boolean sent = Session.sendToTarget(logon, sessionId);
        System.out.println("Logon Message Sent : " + sent);
    }

    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        client = new Client(args);

    }
}
