package server;

import quickfix.*;

public class Server {
    public static void main(String[] args) {
        SocketAcceptor socketAcceptor = null;
        try {
            SessionSettings executorSettings = new SessionSettings(
                    "./server.txt");
            Application application = new ServerApplication();
            FileStoreFactory fileStoreFactory = new FileStoreFactory(
                    executorSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            FileLogFactory logFactory = new FileLogFactory(executorSettings);
            socketAcceptor = new SocketAcceptor(application, fileStoreFactory,
                    executorSettings, logFactory, messageFactory);
            socketAcceptor.start();
        } catch (ConfigError configError) {
            configError.printStackTrace();
        }
    }
}
