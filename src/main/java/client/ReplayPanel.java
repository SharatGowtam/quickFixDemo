package client;

import quickfix.SessionID;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReplayPanel extends JPanel implements ActionListener {
    JTextArea text;
    SessionID id;
    List<String> lines;
    ClientApplication app;
    public ReplayPanel(SessionID id,List<String>lines, ClientApplication application){
        this.id = id;
        this.app = application;
        this.lines = lines;
        setLayout(new GridBagLayout());
        createComponents();
    }

    private void createComponents() {
        String message = "";
        for(String msg : lines){
            if(message ==""){
                message = msg;
            }else{
                message = message + "\n" + msg;
            }

        }
        System.out.println("Message is " + message);
        text = new JTextArea(message);

        add(text);

        JButton submit = new JButton("submit");
        submit.addActionListener(this);
        add(submit);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
       String fixMessages = text.getText();
       String msgs[] = fixMessages.split("\\n");
       for(int i=0;i<msgs.length;i++){
           app.sendOrder(id, msgs[i]);
       }


    }
}
